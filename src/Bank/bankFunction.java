package view.Bank;

import view.DAO.bankAccountDao;
import view.DAO.bankBillDao;
import view.Global.GlobalData;

import java.io.IOException;
import java.util.Date;


public class bankFunction {
    BankTeacherStudentUI bankTeacherStudentView;
    private bankAccountDao bADao = new bankAccountDao();
    private bankBillDao bBDao = new bankBillDao();

    /**
     * 充值
     *
     * @param id    用户一卡通ID
     * @param money 充值金额
     * @param pwd
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    public double recharge(String id, double money, String pwd) {
        double result = 0;
        bankAccount thisAccount = bADao.findBankAccountById(id);
        //若卡正常，则可以消费
        try {
            //若密码正确且卡正常
            if (bADao.isLoss(id) && paymentPwdJudge(id, pwd)) {
                System.out.println(Boolean.toString(bADao.isLoss(id)));
                bADao.recharge(id, money);

                //重新获取数据库最新数据然后显示余额弹窗
                thisAccount = bADao.findBankAccountById(GlobalData.getUID());
                result = thisAccount.getBalance();//若成功，返回余额

            } else if (!bADao.isLoss(id)) {
                result = -1.00;//卡已挂失返回-1
                System.out.println("卡已挂失");

            } else if (!paymentPwdJudge(id, pwd)) {
                result = -2.00;//密码错误，请重新输入
                System.out.println("密码错误，请重新输入");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -3;
    }

    /**
     * 修改密码
     *
     * @param id        用户一卡通ID
     * @param oldPwd    原密码
     * @param newPwd    新密码
     * @param newNewPwd 确认新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    public int changePwd(String id, String oldPwd, String newPwd, String newNewPwd) throws NullPointerException {
        try {
            bankAccount thisAccount = bADao.findBankAccountById(id);
            if (!newPwd.equals(newNewPwd)) {
                System.out.println("新密码两次输入不同");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "新密码两次输入不同");
                return 0;
            }
            if (thisAccount.getPaymentPwd().equals(oldPwd)) {
                bADao.changePwd(id, oldPwd, newPwd);
                System.out.println("修改成功");
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "修改成功");
                return 1;
            } else {
                System.out.println("原密码输入错误");
                return 2;
                //JOptionPane.showMessageDialog(bankTeacherStudentView, "原密码输入错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 查找并返回数据库中一卡通号为id、账单描述（类型）为query、且时间在startTime和endTime之间的所有账单信息
     *
     * @param id        需查询账户的一卡通号
     * @param startTime 查询时间段的开始时间
     * @param endTime   查询时间段的结束时间
     * @param query     查询关键字，即账单描述
     * @return bankBill类数组allbills，代表数据库中所有的账单
     */
    public String[][] billForSometime(String id, Date startTime, Date endTime, String query) {
        return convertBillsToStrings(bBDao.findBillsForSometime(id, startTime, endTime, query));
    }

    /**
     * 用id查询银行账户信息。管理员用
     *
     * @return 包含所有银行账户的 bankAccount[] 数组，如果没有找到任何账户则返回 null。
     */
    public String[][] findBankAccounts(String id) {
        if (id == null) {
            return convertAccountsToStrings(bADao.findfindBankAccounts());
        } else if (id.equals("")) {
            return convertAccountsToStrings(bADao.findfindBankAccounts());
        } else {
            bankAccount[] bankA = new bankAccount[1];
            bankA[0] = bADao.findBankAccountById(id);
            return convertAccountsToStrings(bankA);
        }

    }

    /**
     * 挂失/解挂
     *
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    public int changeLoss(String id, String pwd) throws NullPointerException {
        int result = -1;
        if (pwd == null) {
            bADao.changeLoss(id);
            System.out.println("管理员改变挂失状态成功");
        }
        try {
            bankAccount thisAccount = bADao.findBankAccountById(id);
            if (!thisAccount.getPaymentPwd().equals(pwd)) {
                return 0;//密码输入错误


            }
            if (bADao.isLoss(id)) {//若正常

                //System.out.println("正常，现在在挂失");
                bADao.changeLoss(id);
                return 1;//成功挂失


            } else {//若已挂失

                bADao.changeLoss(id);
                System.out.println("解挂成功");
                return 2;//成功解挂

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 消费
     *
     * @param id   用户一卡通ID
     * @param bill 消费账单信息
     * @param pwd  密码
     * @param isCoercive 是否为强制扣费（如水电费、学费），true代表是强制扣费
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    public double bankConsume(String id, bankBill bill, String pwd,boolean isCoercive) throws IOException {
        double result = -400000.00;
        bankAccount thisAccount = bADao.findBankAccountById(id);
        if(isCoercive){//如果强制扣费，直接扣钱
            //扣款
            bADao.bankConsume(id, bill.getAmount(),isCoercive);//调用DAO类扣钱


            //向数据库添加订单信息
            AddBankBill(bill);
            thisAccount = bADao.findBankAccountById(id);
            result = thisAccount.getBalance();
            System.out.println("扣款成功，余额￥" + Double.toString(thisAccount.getBalance()));
        }else{//当不是强制扣费时
            //若卡正常，则可以消费
            try {
                //若密码正确且卡正常
                if (bADao.isLoss(id) && paymentPwdJudge(id, pwd)) {
                    if (thisAccount.getBalance() - bill.getAmount() >= 0) {//余额够
                        //扣款
                        bADao.bankConsume(id, bill.getAmount(),isCoercive);//调用DAO类扣钱
                        System.out.println("支付成功，余额￥" + Double.toString(thisAccount.getBalance()));

                        //向数据库添加订单信息
                        AddBankBill(bill);
                        thisAccount = bADao.findBankAccountById(id);
                        result = thisAccount.getBalance();
                    } else{
                        result=-100000.00;//余额不足
                    }
                } else if (!bADao.isLoss(id)) {
                    System.out.println("卡已挂失");
                    result = -200000.00;

                } else if (!paymentPwdJudge(id, pwd)) {
                    System.out.println("密码错误，请重新输入");
                    result = -300000.00;

                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return -400000.00;
    }

    /**
     * 通过一卡通号查找对应的account
     */
    public bankAccount findBankAccountById(String id) {
        return bADao.findBankAccountById(id);
    }

    /**
     * 向数据库中新增账单信息
     *
     * @param bankbill 增加的账单信息
     * @return 是否新增账单成功
     */
    public boolean AddBankBill(bankBill bankbill) {
        return bBDao.AddBankBill(bankbill);
    }


    /**
     * 判断输入的付款密码和指定账户的密码是否匹配。
     *
     * @param id              账户ID
     * @param enteredPassword 用户输入的密码
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
        if (bills != null) {
            String[][] billStrings = new String[bills.length][6];
            System.out.println(bills.length);

            for (int i = 0; i < bills.length; i++) {
                bankBill bill = bills[i];

                billStrings[i][0] = bill.getDescription();
                billStrings[i][1] = bill.getNums();
                billStrings[i][2] = bill.getCardId();
                //billStrings[i][3] = bill.getUserId();
                billStrings[i][3] = bill.getTime().toString();
                billStrings[i][4] = bill.isType() ? "消费" : "充值";
                billStrings[i][5] = Double.toString(bill.getAmount());
            }

            return billStrings;
        } else {
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
        if (accounts != null) {
            String[][] accountStrings = new String[accounts.length][6];
            System.out.println(accounts.length);

            for (int i = 0; i < accounts.length; i++) {
                bankAccount account = accounts[i];

                accountStrings[i][0] = account.getCardId();
                accountStrings[i][1] = account.getName();
                accountStrings[i][2] = account.getId();
                accountStrings[i][3] = Double.toString(account.getBalance());
                accountStrings[i][4] = account.isLoss() ? "正常" : "已挂失";
                accountStrings[i][5] = "";
            }

            return accountStrings;
        } else {
            System.out.println("无相应内容");
            return null;
        }
    }


    public boolean addBankAccount(bankAccount bankaccount) {
        return bADao.addBankAccount(bankaccount);
    }
}
