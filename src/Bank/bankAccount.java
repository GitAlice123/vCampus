package view.Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import java.io.*;
import java.util.*;

import javax.swing.*;

/**
 * 银行账户类
 */
public class bankAccount {
    private String cardId="";  // 校园卡账户，六位数字
    private String name="";  // 姓名
    private String id="";  // 一卡通号，9位
    private String PaymentPwd="";//密码，6为数字
    private double balance=0.00;  // 余额
    private boolean isLoss=true;  // 是否挂失，true表示正常，false表示挂失

    public bankAccount(){

    }

    /**
     * 构造函数
     *
     * @param cardId   校园卡账户
     * @param name     姓名
     * @param id       一卡通号
     * @param paymentPwd   密码
     * @param balance  余额
     * @param isLoss   是否挂失
     */
    public bankAccount(String cardId, String name, String id, String paymentPwd,double balance, boolean isLoss) {
        this.cardId = cardId;
        this.name = name;
        this.id = id;
        this.PaymentPwd=paymentPwd;
        this.balance = balance;
        this.isLoss = isLoss;
    }

    /**
     * 获取校园卡账户
     *
     * @return 校园卡账户
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置校园卡账户
     *
     * @param cardId 校园卡账户
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取姓名
     *
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取一卡通号
     *
     * @return 一卡通号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置一卡通号
     *
     * @param id 一卡通号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取校园卡账户
     *
     * @return 校园卡账户
     */
    public String getPaymentPwd() {
        return PaymentPwd;
    }

    /**
     * 设置校园卡账户
     *
     * @param paymentPwd 校园卡账户
     */
    public void setPaymentPwd(String paymentPwd) {
        this.PaymentPwd = paymentPwd;
    }

    /**
     * 获取余额
     *
     * @return 余额
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 是否挂失
     *
     * @return 是否挂失
     */
    public boolean isLoss() {
        return isLoss;
    }

    /**
     * 设置是否挂失
     *
     * @param loss 是否挂失
     */
    public void setLoss(boolean loss) {
        isLoss = loss;
    }

    /**
     * 重写 toString 方法
     *
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "bankAccount{" +
                "cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", PaymentPwd='" + PaymentPwd + '\'' +
                ", balance=" + balance +
                ", isLoss=" + isLoss +
                '}';
    }
}
