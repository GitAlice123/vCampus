package view.Bank;

import view.DAO.bankAccountDao;
import view.DAO.bankBillDao;
import view.Global.GlobalData;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;


public class bankFunction {
    private bankAccountDao bADao=new bankAccountDao();
    private bankBillDao bBDao=new bankBillDao();

    BankTeacherStudentUI bankTeacherStudentView;

    /**
     * 充值
     * @param id 用户一卡通ID
     * @param money 充值金额
     * @param pwd
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    public boolean recharge(String id, double money,String pwd){
        bankAccount thisAccount=bADao.findBankAccountById(id);
        //若卡正常，则可以消费
        try{
            //若密码正确且卡正常
            if(bADao.isLoss(id)&&paymentPwdJudge(id,pwd)){
                System.out.println(Boolean.toString(bADao.isLoss(id)));
                bADao.recharge(id, money);

                //重新获取数据库最新数据然后显示余额弹窗
                thisAccount=bADao.findBankAccountById(GlobalData.getUID());
                System.out.println("充值成功，余额￥"+Double.toString(thisAccount.getBalance()));
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "充值成功，余额￥"+Double.toString(thisAccount.getBalance()));
                // TODO 修改弹窗问题
                //JOptionPane.showMessageDialog(null, "充值成功，余额￥"+Double.toString(thisAccount.getBalance()), "result", JOptionPane.INFORMATION_MESSAGE);
            } else if (!bADao.isLoss(id)) {
                System.out.println("卡已挂失");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "卡已挂失");
            } else if (!paymentPwdJudge(id,pwd)) {
                System.out.println("密码错误，请重新输入");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "密码错误，请重新输入");
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
     * @param newNewPwd 确认新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    public boolean changePwd(String id, String oldPwd, String newPwd,String newNewPwd) throws NullPointerException{
        try{
            bankAccount thisAccount=bADao.findBankAccountById(id);
            if(!newPwd.equals(newNewPwd)){
                System.out.println("新密码两次输入不同");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "新密码两次输入不同");
                return false;
            }
            if(thisAccount.getPaymentPwd().equals(oldPwd)){
                bADao.changePwd(id, oldPwd, newPwd);
                System.out.println("修改成功");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "修改成功");
                return true;
            }else{
                System.out.println("原密码输入错误");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "原密码输入错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    /**
//     * 查询账单
//     * @param query 查询关键字
//     * @return 匹配查询关键字的账单对象，若用户自己查找，关键字为订单号或商品说明；若管理员查找,关键字为订单号或师生一卡通ID
//     */
//    public String[][] billSearch(String query,String ID){
//        bankBill[] bills=bBDao.findBillsByQuery(query,ID);
//        return convertBillsToStrings(bills);
//    }

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
     * 用id查询银行账户信息。管理员用
     *
     * @return 包含所有银行账户的 bankAccount[] 数组，如果没有找到任何账户则返回 null。
     */
    public String[][] findBankAccounts(String id){
        if(id==null) {
            return convertAccountsToStrings(bADao.findfindBankAccounts());
        } else if (id.equals("")) {
            return convertAccountsToStrings(bADao.findfindBankAccounts());
        } else{
            bankAccount[] bankA=new bankAccount[1];
            bankA[0]=bADao.findBankAccountById(id);
            return convertAccountsToStrings(bankA);
        }

    }

    /**
     * 挂失/解挂
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    public boolean changeLoss(String id,String pwd) throws NullPointerException{
        if(pwd==null){
            bADao.changeLoss(id);
            System.out.println("管理员改变挂失状态成功");
        }
        try{
            bankAccount thisAccount=bADao.findBankAccountById(id);
            if(!thisAccount.getPaymentPwd().equals(pwd)){
                System.out.println("密码错误");
                ////JOptionPane.showMessageDialog(bankTeacherStudentView, "密码错误");
                // TODO 执行弹窗的时候会卡死
                return false;
            }
            if(bADao.isLoss(id)){//若正常
                System.out.println("正常，现在在挂失");
                bADao.changeLoss(id);
                System.out.println("挂失成功");
                ////JOptionPane.showMessageDialog(bankTeacherStudentView, "成功挂失");
                return true;
            }else{//若已挂失
                bADao.changeLoss(id);
                System.out.println("解挂成功");
                ////JOptionPane.showMessageDialog(bankTeacherStudentView, "成功解挂");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 消费
     * @param id 用户一卡通ID
     * @param bill 消费账单信息
     * @param pwd 密码
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    public boolean bankConsume(String id, bankBill bill,String pwd) throws IOException {
        bankAccount thisAccount=bADao.findBankAccountById(id);
        //若卡正常，则可以消费
        try{
            //若密码正确且卡正常
            if(bADao.isLoss(id)&&paymentPwdJudge(id,pwd)){
                if(thisAccount.getBalance()- bill.getAmount()>=0){//余额够
                    //扣款
                    bADao.bankConsume(id,bill.getAmount());//调用DAO类扣钱
                    System.out.println("支付成功，余额￥"+Double.toString(thisAccount.getBalance()));

                    //向数据库添加订单信息
                    AddBankBill(bill);
                    //JOptionPane.showMessageDialog(bankTeacherStudentView, "支付成功，余额￥"+Double.toString(thisAccount.getBalance()));
                }else{
                    System.out.println("余额不足！请充值");
                    //JOptionPane.showMessageDialog(bankTeacherStudentView, "余额不足！请充值");
                }
            } else if (!bADao.isLoss(id)) {
                System.out.println("卡已挂失");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "卡已挂失");
            } else if (!paymentPwdJudge(id,pwd)) {
                System.out.println("密码错误，请重新输入");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "密码错误，请重新输入");
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过一卡通号查找对应的account
     * */
    public bankAccount findBankAccountById(String id){
        return bADao.findBankAccountById(id);
    }

    /**
     * 向数据库中新增账单信息
     *
     * @param bankbill  增加的账单信息
     * @return 是否新增账单成功
     */
    public boolean AddBankBill(bankBill bankbill){
        return bBDao.AddBankBill(bankbill);
    }



    /**
     * 判断输入的付款密码和指定账户的密码是否匹配。
     *
     * @param id 账户ID
     * @param enteredPassword  用户输入的密码
     * @return 如果密码匹配则返回true，否则返回false
     */
    public boolean paymentPwdJudge(String id, String enteredPassword) {
        // 根据账户ID查找对应的银行账户
        bankAccount thisAccount = bADao.findBankAccountById(id);

        // 如果账户存在且密码匹配，则返回true，否则返回false
        if (thisAccount != null && thisAccount.getPaymentPwd().equals(enteredPassword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将 bankBill 对象数组转换为二维字符串数组,只用于学生老师界面的账单展示
     *
     * @param bills bankBill 对象数组，需要进行转换的数组
     * @return 包含 bankBill 对象属性的二维字符串数组
     */
    public String[][] convertBillsToStrings(bankBill[] bills) {
        if(bills!=null){
            String[][] billStrings = new String[bills.length][6];
            System.out.println(bills.length);

            for (int i = 0; i < bills.length; i++) {
                bankBill bill = bills[i];

                billStrings[i][0] = bill.getDescription();
                billStrings[i][1] = bill.getNums();
                billStrings[i][2] = bill.getCardId();
                //billStrings[i][3] = bill.getUserId();
                billStrings[i][3] = bill.getTime().toString();
                billStrings[i][4] = bill.isType()?"消费":"充值";
                billStrings[i][5] = Double.toString(bill.getAmount());
            }

            return billStrings;
        }else{
            System.out.println("无相应内容");
            return null;
        }
    }

    /**
     * 将 bankAccount 对象数组转换为二维字符串数组,只用于学生老师界面的账单展示
     *
     * @param accounts bankAccount 对象数组，需要进行转换的数组
     * @return 包含 bankAccount 对象属性的二维字符串数组
     */
    public String[][] convertAccountsToStrings(bankAccount[] accounts) {
        if(accounts!=null){
            String[][] accountStrings = new String[accounts.length][6];
            System.out.println(accounts.length);

            for (int i = 0; i < accounts.length; i++) {
                bankAccount account = accounts[i];

                accountStrings[i][0] = account.getCardId();
                accountStrings[i][1] = account.getName();
                accountStrings[i][2] = account.getId();
                accountStrings[i][3] = Double.toString(account.getBalance());
                accountStrings[i][4] = account.isLoss()?"正常":"已挂失";
                accountStrings[i][5] = "";
            }

            return accountStrings;
        }else{
            System.out.println("无相应内容");
            return null;
        }
    }


    public boolean addBankAccount(bankAccount bankaccount){
        return bADao.addBankAccount(bankaccount);
    }
}
