package view.Bank;

import view.DAO.bankAccountDao;
import view.DAO.bankBillDao;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;


public class bankFunction {
    private bankAccountDao bADao;
    private bankBillDao bBDao;

    /**
     * 充值
     * @param id 用户一卡通ID
     * @param money 充值金额
     * @param f 密码输入框
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    public boolean recharge(String id, int money,JTextField f){
        bankAccount thisAccount=bADao.findBankAccountById(id);
        //若卡正常，则可以消费
        try{
            //若密码正确且卡正常
            if(bADao.isLoss(id)&&paymentPwdJudge(id,f)){
                thisAccount.setBalance(thisAccount.getBalance()+money);
                System.out.println("充值成功，余额￥"+Double.toString(thisAccount.getBalance()));
                    //这里最好换成在GUI页面输出
            } else if (!bADao.isLoss(id)) {
                System.out.println("卡已挂失");
            } else if (!paymentPwdJudge(id,f)) {
                System.out.println("密码错误，请重新输入");
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改密码
     * @param id 用户一卡通ID
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    public boolean changePwd(String id, String oldPwd, String newPwd) throws NullPointerException{
        try{
            bankAccount thisAccount=bADao.findBankAccountById(id);
            if(thisAccount.getPaymentPwd().equals(oldPwd)){
                thisAccount.setPaymentPwd(newPwd);
                System.out.println("修改成功");
                return true;
            }else{
                System.out.println("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询账单
     * @param query 查询关键字
     * @return 匹配查询关键字的账单对象，若用户自己查找，关键字为订单号或商品说明；若管理员查找,关键字为订单号或师生一卡通ID
     */
    public String[][] billSearch(String query,String ID){
        bankBill[] bills=bBDao.findBillsByQuery(query,ID);
        return convertBillsToStrings(bills);
    }

    /**
     * 查找并返回数据库中一卡通号为id、账单描述（类型）为query、且时间在startTime和endTime之间的所有账单信息
     *
     * @param id       需查询账户的一卡通号
     * @param startTime 查询时间段的开始时间
     * @param endTime   查询时间段的结束时间
     * @param query    查询关键字，即账单描述
     * @return bankBill类数组allbills，代表数据库中所有的账单
     */
    public String[][] billForSometime(String id,Date startTime, Date endTime,String query){
        return convertBillsToStrings(bBDao.findBillsForSometime(id,startTime,endTime,query));
    }

    /**
     * 挂失/解挂
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    public boolean changeLoss(String id) throws NullPointerException{
        try{
            bankAccount thisAccount=bADao.findBankAccountById(id);
            if(bADao.isLoss(id)){//若正常
                thisAccount.setLoss(false);//挂失
            }else{//若已挂失
                thisAccount.setLoss(true);//解挂
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 消费
     * @param id 用户一卡通ID
     * @param money 消费金额
     * @param f 密码输入框
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    public boolean bankConsume(String id, int money,JTextField f) throws IOException {
        bankAccount thisAccount=bADao.findBankAccountById(id);
        //若卡正常，则可以消费
        try{
            //若密码正确且卡正常
            if(bADao.isLoss(id)&&paymentPwdJudge(id,f)){
                if(thisAccount.getBalance()-money>=0){//余额够
                    //扣款
                    thisAccount.setBalance(thisAccount.getBalance()-money);
                    System.out.println("支付成功，余额￥"+Double.toString(thisAccount.getBalance()));
                    //这里最好换成在GUI页面输出
                }else{
                    System.out.println("余额不足！请充值");
                }
            } else if (!bADao.isLoss(id)) {
                System.out.println("卡已挂失");
            } else if (!paymentPwdJudge(id,f)) {
                System.out.println("密码错误，请重新输入");
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断输入的付款密码和指定账户的密码是否匹配。
     *
     * @param id 账户ID
     * @param f  密码输入框
     * @return 如果密码匹配则返回true，否则返回false
     */
    public boolean paymentPwdJudge(String id, JTextField f) {
        // 根据账户ID查找对应的银行账户
        bankAccount thisAccount = bADao.findBankAccountById(id);

        // 获取密码输入框中的文本
        String enteredPassword = f.getText();

        // 如果账户存在且密码匹配，则返回true，否则返回false
        if (thisAccount != null && thisAccount.getPaymentPwd().equals(enteredPassword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将 bankBill 对象数组转换为二维字符串数组。
     *
     * @param bills bankBill 对象数组，需要进行转换的数组
     * @return 包含 bankBill 对象属性的二维字符串数组
     */
    public String[][] convertBillsToStrings(bankBill[] bills) {
        String[][] billStrings = new String[bills.length][7];

        for (int i = 0; i < bills.length; i++) {
            bankBill bill = bills[i];

            billStrings[i][0] = bill.getDescription();
            billStrings[i][1] = bill.getNums();
            billStrings[i][2] = bill.getCardId();
            billStrings[i][3] = bill.getUserId();
            billStrings[i][4] = bill.getTime().toString();
            billStrings[i][5] = Boolean.toString(bill.isType());
            billStrings[i][6] = Double.toString(bill.getAmount());
        }

        return billStrings;
    }
}
