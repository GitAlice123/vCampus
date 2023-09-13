//汇总页面（主页）

package view.Global;

import view.Bank.BankManagerUI;
import view.Bank.BankTeacherStudentUI;
import view.CourseSelection.CurriculumAdminUI;
import view.CourseSelection.CurriculumStudentUI;
import view.CourseSelection.CurriculumTeacherUI;
import view.Hospital.HospitalManagerUI;
import view.Hospital.HospitalTeacherStudentUI;
import view.Library.LibraryAdminUI;
import view.Library.LibraryUI;
import view.Login.logInUI;
import view.SchoolRolls.StudentStatusAdminUI;
import view.SchoolRolls.StudentStatusTeacherUI;
import view.SchoolRolls.StudentStatusUI;
import view.Shop.ShopManagerUI;
import view.Shop.ShopTeacherStudentUI;
import view.chat.ChatFrameUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.IOException;

public class SummaryManagerHandler extends KeyAdapter implements ActionListener {
    private SummaryManagerUI summaryview;

    public SummaryManagerHandler(SummaryManagerUI summaryview) {
        this.summaryview = summaryview;
    }

    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();

        //summaryview.dispose();
        switch (text) {
            case "学生学籍管理": {
                summaryview.dispose();
                if (GlobalData.getUType() == 1) {
                    try {
                        new StudentStatusUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (GlobalData.getUType() == 2) {
                    try {
                        new StudentStatusTeacherUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        new StudentStatusAdminUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println("学生学籍管理");
                break;
            }
            case "选课系统": {
                summaryview.dispose();
                if (GlobalData.getUType() == 1) {
                    try {
                        new CurriculumStudentUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (GlobalData.getUType() == 2) {
                    try {
                        new CurriculumTeacherUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        new CurriculumAdminUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println("选课");
                break;
            }
            case "图书馆": {
                summaryview.dispose();
                if (GlobalData.getUType() == 1 || GlobalData.getUType() == 2) {
                    try {
                        new LibraryUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        new LibraryAdminUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println("图书馆");
                break;
            }
            case "商店": {
                summaryview.dispose();
                if (GlobalData.getUType() == 1 || GlobalData.getUType() == 2) {
                    new ShopTeacherStudentUI();
                } else {
                    new ShopManagerUI();
                }
                System.out.println("商店");
                break;
            }
            case "医院": {
                summaryview.dispose();
                if (GlobalData.getUType() == 1 || GlobalData.getUType() == 2) {
                    new HospitalTeacherStudentUI();
                } else {
                    new HospitalManagerUI();
                }
                System.out.println("医院");
                break;
            }
            case "银行": {
                summaryview.dispose();
                if (GlobalData.getUType() == 1 || GlobalData.getUType() == 2) {
                    new BankTeacherStudentUI();//跳转页面的判断逻辑待写
                } else {
                    new BankManagerUI();
                }

                System.out.println("银行");
                break;
            }
            case "chat":
                summaryview.dispose();
                try {
                    new ChatFrameUI();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "登出":
                GlobalData.logout();//登出时注销用户
                summaryview.dispose();
                try {
                    new logInUI();
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
        }
    }

}
