package view.Bank;

import view.DAO.bankAccountDao;
import view.DAO.bankBillDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class bankFunction {
    private bankAccountDao bADao;
    private bankBillDao bankBillDao;

    /**
     * 充值
     * @param id 用户一卡通ID
     * @param money 充值金额
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    public boolean recharge(String id, int money){

    }

    /**
     * 修改密码
     * @param id 用户一卡通ID
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    public boolean changePwd(String id, String oldPwd, String newPwd);

    /**
     * 查询账单
     * @param query 查询关键字
     * @return 匹配查询关键字的账单对象，若用户自己查找，关键字为订单号或商品说明；若管理员查找,关键字为订单号或师生一卡通ID
     */
    public bankBill billSearch(String query,String ID);

    /**
     * 查询限定时间内的账单
     * @param startTime 起始时间，大于等于这个时间
     * @param endTime 结束时间，小于等于这个时间
     * @return 在指定时间范围内的账单列表，返回值为String[][]类型
     */
    public ArrayList<bankBill> billForSometime(Date startTime, Date endTime);

    /**
     * 挂失/解挂
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    public boolean changeLoss(String id){
        bankAccount thisAccount=bADao.findBankAccountById(id);
        if(bADao.isLoss(id)){//若正常
            thisAccount.setLoss(false);//挂失
        }else{//若已挂失
            thisAccount.setLoss(true);//解挂
        }
        return true;
    }

    /**
     * 消费
     * @param id 用户一卡通ID
     * @param money 消费金额
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    public boolean bankConsume(String id, int money) throws IOException {
        //若卡正常，则可以消费
        try{

        }
    }
}
