//汇总页面（主页）

package view.Global;

import view.Global.SummaryUI;
import view.Login.logInUI;
import view.Bank.BankTeacherStudentUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.*;

public class SummaryHandler extends KeyAdapter implements ActionListener
{
    private SummaryUI summaryview;
    public SummaryHandler(SummaryUI summaryview)
    {
        this.summaryview=summaryview;
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton jButton=(JButton) e.getSource();
        String text=jButton.getText();

        //summaryview.dispose();
        switch (text){
            case "学生学籍管理":System.out.println("学生学籍管理");break;
            case "选课系统":System.out.println("选课系统");break;
            case "图书馆":System.out.println("图书馆");break;
            case "商店":System.out.println("商店");break;
            case "医院":System.out.println("医院");break;
            case "银行":
                summaryview.dispose();
                new BankTeacherStudentUI();//跳转页面的判断逻辑待写
                System.out.println("银行");break;
            case "登出":
                GlobalData.logout();//登出时注销用户
                summaryview.dispose();
                new logInUI();
        }
    }

}
