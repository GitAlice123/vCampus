package view.message;
import view.Bank.*;

/**
 * BillRespMessage 类表示账户响应消息，包含银行账户信息。
 */
public class BillRespMessage {
    private bankAccount bankA;    // 银行账户信息

    /**
     * 无参构造函数，创建一个 BillRespMessage 的实例，使用默认值初始化属性。
     */
    public BillRespMessage() {
        this.bankA = null;
    }

    /**
     * 有参构造函数，创建一个 BillRespMessage 的实例，使用指定的银行账户信息初始化属性。
     *
     * @param bankA 银行账户信息
     */
    public BillRespMessage(bankAccount bankA) {
        this.bankA = bankA;
    }

    /**
     * 获取银行账户信息。
     *
     * @return 银行账户信息
     */
    public bankAccount getBankA() {
        return bankA;
    }

    /**
     * 设置银行账户信息。
     *
     * @param bankA 银行账户信息
     */
    public void setBankA(bankAccount bankA) {
        this.bankA = bankA;
    }
}
