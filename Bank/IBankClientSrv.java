package view.Bank;

import java.util.ArrayList;

public interface IBankClientSrv {
    /**
     * 充值
     * @param id 用户ID
     * @param money 充值金额
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    boolean recharge(String id, int money);

    /**
     * 修改密码
     * @param id 用户ID
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    boolean changePwd(String id, String oldPwd, String newPwd);

    /**
     * 查询账单
     * @param query 查询关键字
     * @return 匹配查询关键字的账单对象
     */
    bankBill billSearch(String query);

    /**
     * 查询限定时间内的账单
     * @param time 时间范围
     * @return 在指定时间范围内的账单列表
     */
    ArrayList<bankBill> billForSometime(String time);

    /**
     * 挂失/解挂
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    boolean changeLoss();

    /**
     * 消费
     * @param id 用户ID
     * @param money 消费金额
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    boolean bankConsume(String id, int money);
}
