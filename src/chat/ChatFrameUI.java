package view.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import view.Global.SummaryUI;
import view.Login.logInUI;
import view.connect.*;
import view.message.*;


public class ChatFrameUI extends JFrame {
    private Timer waitTimer; // 定时器
    private int dotCount; // 点号数量
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;

    //底部
    JScrollPane bottompane;
    JTextArea textarea;
    JButton sendBtn;
    JButton backBtn;

    //中部
    JScrollPane centerpane;
    JTextArea chathistory;

    //顶部
    JLabel welcomeLabel;

    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font centerFont = new Font("楷体", Font.PLAIN, 20);//设置中间组件的文字大小、字体

    String messageSent;

    private String messageReturn; // 保存服务器返回的消息

    public ChatFrameUI() {
        super("ChatFrame");

        topPanel = new JPanel();
        centerPanel = new JPanel();
        bottomPanel = new JPanel();

        //底部
        textarea = new JTextArea();
        sendBtn = new JButton("发送");
        backBtn =new JButton("退出");

        sendBtn.setFont(buttonFont);
        textarea.setFont(centerFont);
        backBtn.setFont(buttonFont);

        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        bottompane = new JScrollPane(textarea);
        bottompane.setPreferredSize(new Dimension(800, 100));

        bottomPanel.add(bottompane);
        bottomPanel.add(sendBtn);
        bottomPanel.add(backBtn);

        //中部
        chathistory = new JTextArea();
        chathistory.setLineWrap(true);
        chathistory.setWrapStyleWord(true);
        chathistory.setEditable(false);
        chathistory.setFont(centerFont);
        centerpane = new JScrollPane(chathistory);
        centerpane.setPreferredSize(new Dimension(1000, 550));

        centerPanel.add(centerpane);

        waitTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dotCount < 6) {
                    chathistory.append(".");
                    dotCount++;
                } else {
                    chathistory.setText(chathistory.getText().replace("......", ""));
                    dotCount = 0;
                }
            }
        });
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendMessage();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                receiveMessage();
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });

        //顶部
        welcomeLabel = new JLabel("欢迎来到聊天室！");
        welcomeLabel.setFont(new Font("楷体", Font.PLAIN, 50));
        topPanel.add(welcomeLabel);

        Container pane = getContentPane();
        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(centerPanel, BorderLayout.CENTER);
        pane.add(bottomPanel, BorderLayout.SOUTH);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void sendMessage() throws PrinterException {
        messageSent = textarea.getText();
        if (!messageSent.isEmpty()) {
            chathistory.append("\n" + "You: " + messageSent + "\n");
            textarea.setText("");
        }
    }

    private void receiveMessage() {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() throws Exception {
                ChatClientAPI chatClientAPI = new ChatClientAPIImpl("localhost", 8888);
                ChatQuesMessage chatQuesMessage = new ChatQuesMessage(messageSent);
                messageReturn = chatClientAPI.getGPTAnswer(chatQuesMessage);
                return messageReturn;
            }

            @Override
            protected void done() {
                waitTimer.stop(); // 停止定时器
                if (!messageReturn.isEmpty()) {
                    // 清除等待回答的消息
                    String waitingMessage = "\n" + "AI小助手: " + getWaitingMessage();
                    int waitingMessageStart = chathistory.getText().indexOf(waitingMessage);
                    if (waitingMessageStart >= 0) {
                        int waitingMessageEnd = waitingMessageStart + waitingMessage.length();
                        chathistory.replaceRange("", waitingMessageStart, waitingMessageEnd);
                    }
                    // 显示AI助手的回答
                    chathistory.append("\nAI小助手: " + messageReturn + "\n");
                    // 将光标定位到文本末尾
                    chathistory.setCaretPosition(chathistory.getDocument().getLength());
                }
            }
        };

        // 显示等待回答的消息
        chathistory.append("\n" + "AI小助手: " + getWaitingMessage());
        waitTimer.start();
        worker.execute();
    }

    private String getWaitingMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dotCount; i++) {
            sb.append(".");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        new ChatFrameUI();
    }
}
