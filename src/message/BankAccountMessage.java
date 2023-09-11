package view.message;

import view.Bank.bankAccount;

public class BankAccountMessage {
    private bankAccount bankA;

    // 无参构造函数
    public BankAccountMessage() {
    }

    // 有参构造函数
    public BankAccountMessage(bankAccount bankA) {
        this.bankA = bankA;
    }

    // 设置 bankA 的方法
    public void setBankA(bankAccount bankA) {
        this.bankA = bankA;
    }

    // 获取 bankA 的方法
    public bankAccount getBankA() {
        return bankA;
    }
}
