package view.Bank;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import view.message.*;

public interface IBankClientAPI {
    /**
     * 充值
     * @param id 用户一卡通ID
     * @param money 充值金额
     * @param pwd 用户输入的密码
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    boolean recharge(String id, double money, String pwd);

    /**
     * 修改密码
     * @param id 用户一卡通ID
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @param newNewPwd 确认新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    boolean changePwd(String id, String oldPwd, String newPwd,String newNewPwd)
            throws NullPointerException;
//
//    /**
//     * 查询账单
//     * @param query 查询关键字
//     * @return 匹配查询关键字的账单对象，若用户自己查找，关键字为订单号或商品说明；若管理员查找,关键字为订单号或师生一卡通ID
//     */
//    String[][] billSearch(String query,String ID);
//
    /**
     * 查找并返回数据库中一卡通号为id、账单描述（类型）为query、且时间在startTime和endTime之间的所有账单信息
     *
     * @param id       需查询账户的一卡通号
     * @param startTime 查询时间段的开始时间
     * @param endTime   查询时间段的结束时间
     * @param query    查询关键字，即账单描述
     * @return bankBill类数组allbills，代表数据库中所有的账单
     */
    String[][] billForSometime(String id, Date startTime, Date endTime, String query);

    /**
     * 挂失/解挂
     * @param id 一卡通号
     * @param pwd 用户输入的密码
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    boolean changeLoss(String id,String pwd) throws NullPointerException;

    /**
     * 消费
     * @param id 用户一卡通ID
     * @param bill 账单信息
     * @param pwd 用户输入的密码
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    boolean bankConsume(String id, bankBill bill,String pwd) throws IOException ;

    /**
     * 通过一卡通号查找account
     * @param id 一卡通号
     * @return bankAccount账户
     */
    public bankAccount findBankAccountById(String id) throws NullPointerException;
}
