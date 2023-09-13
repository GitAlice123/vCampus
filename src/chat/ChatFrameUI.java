package view.chat;

import view.Global.SummaryStudentTeacherUI;
import view.client.ClientReceiver;
import view.connect.ChatClientAPI;
import view.connect.ChatClientAPIImpl;
import view.message.ChatMessage;
import view.message.ChatQuesMessage;
import view.message.ChatWithUserMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.IOException;


public class ChatFrameUI extends JFrame implements ClientReceiver.MessageCallback {
    SpringLayout springLayout = new SpringLayout();
    JPanel topPanel;

    JPanel centerPanel;
    JPanel bottomPanel;
    // 底部
    JScrollPane bottompane;
    JTextArea textarea;
    JButton sendBtn;
    JButton backBtn;
    JComboBox<String> friends;
    ButtonGroup choose;
    JRadioButton AIassistant;
    JRadioButton schoolchat;
    // 中部
    JScrollPane centerpane;
    JTextArea chathistory;
    JScrollPane leftpane;
    JTextArea online;
    // 顶部
    JLabel welcomeLabel;
    Font buttonFont = new Font("楷体", Font.PLAIN, 25);// 设置按钮的文字大小、字体
    Font centerFont = new Font("楷体", Font.PLAIN, 20);// 设置中间组件的文字大小、字体

    // 颜色
    Color bottomcolor = new Color(233, 208, 222);
    Color topcolor = new Color(152, 193, 202);

    private Timer waitTimer; // 定时器
    private int dotCount; // 点号数量

    /**
     * 存储发送出去的消息
     */
    String messageSent;

    /**
     * 存储服务器发回的消息
     */
    private String messageReturn; // 保存服务器返回的消息

    /**
     * 聊天框UI的构造函数
     *
     * @throws IOException
     */
    public ChatFrameUI() throws IOException {

        super("ChatFrame");
        startReceivingMessages();

        topPanel = new JPanel();
        centerPanel = new JPanel(springLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // 加载原始尺寸的背景图片
                ImageIcon originalImageIcon = new ImageIcon("Images/chatbackground.gif");
                Image originalImage = originalImageIcon.getImage();

                // 创建与面板尺寸相同的缓冲图像
                BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bufferedImage.createGraphics();

                // 设置透明度
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
                g2d.setComposite(alphaComposite);

                // 绘制背景图片到缓冲图像
                g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

                // 绘制缓冲图像到面板
                g.drawImage(bufferedImage, 0, 0, null);

                g2d.dispose();
            }
        };
        bottomPanel = new JPanel();

        topPanel.setBackground(topcolor);
        bottomPanel.setBackground(bottomcolor);

        // 底部
        textarea = new JTextArea();
        sendBtn = new JButton("发送");
        backBtn = new JButton("退出");
        choose = new ButtonGroup();
        AIassistant = new JRadioButton("AI助手");
        schoolchat = new JRadioButton("校内聊天");

        sendBtn.setFont(buttonFont);
        textarea.setFont(centerFont);
        backBtn.setFont(buttonFont);
        AIassistant.setFont(buttonFont);
        schoolchat.setFont(buttonFont);

        choose.add(AIassistant);
        choose.add(schoolchat);
        AIassistant.setSelected(true);

        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        bottompane = new JScrollPane(textarea);
        bottompane.setPreferredSize(new Dimension(700, 100));

        bottomPanel.add(AIassistant);
        bottomPanel.add(schoolchat);
        bottomPanel.add(bottompane);
        bottomPanel.add(sendBtn);
        bottomPanel.add(backBtn);

        // 中部
        chathistory = new JTextArea();
        chathistory.setLineWrap(true);
        chathistory.setWrapStyleWord(true);
        chathistory.setEditable(false);
        chathistory.setFont(centerFont);
        centerpane = new JScrollPane(chathistory);
        centerpane.setPreferredSize(new Dimension(800, 550));

        online = new JTextArea("在线人员");
        online.setLineWrap(true);
        online.setWrapStyleWord(true);
        online.setEditable(false);
        online.setFont(centerFont);
        leftpane = new JScrollPane(online);
        leftpane.setPreferredSize(new Dimension(200, 450));

        // TODO:获得所有在线人员名单

        friends = new JComboBox<String>();
        friends.addItem("薛沛林");
        friends.addItem("余畅");
        friends.setFont(centerFont);

        centerPanel.add(leftpane);
        centerPanel.add(centerpane);
        centerPanel.add(friends);

        springLayout.putConstraint(SpringLayout.WEST, leftpane, 40, SpringLayout.WEST, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, leftpane, 20, SpringLayout.NORTH, centerPanel);
        springLayout.putConstraint(SpringLayout.WEST, centerpane, 40, SpringLayout.EAST, leftpane);
        springLayout.putConstraint(SpringLayout.NORTH, centerpane, 0, SpringLayout.NORTH, leftpane);
        springLayout.putConstraint(SpringLayout.WEST, friends, 20, SpringLayout.WEST, leftpane);
        springLayout.putConstraint(SpringLayout.NORTH, friends, 40, SpringLayout.SOUTH, leftpane);

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
                receiveGPTMessage();
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
            }
        });

        // 顶部
        welcomeLabel = new JLabel("欢迎来到聊天室！");
        welcomeLabel.setFont(new Font("楷体", Font.PLAIN, 50));
        topPanel.add(welcomeLabel);

        chathistory.setOpaque(false);
        centerpane.setOpaque(false);
        centerpane.getViewport().setBackground(new Color(255, 255, 255, 150));
        online.setOpaque(false);
        leftpane.setOpaque(false);
        leftpane.getViewport().setBackground(new Color(255, 255, 255, 150));

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

    private void receiveGPTMessage() {
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

    public void startReceivingMessages() {
        ClientReceiver clientReceiver;
        try {
            clientReceiver = new ClientReceiver(this); // 将当前 UI 文件实例作为回调接口
            clientReceiver.startReceiving();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(ChatMessage message) {
        System.out.println("Received message: " + message);
        chathistory.append("用户: " + message + "\n");
    }

//    public void testSend(String toUserId) throws IOException {
//        ChatWithUserMessage chatWithUserMessage = new ChatWithUserMessage(
//                "你好", toUserId);
//        ChatClientAPI chatClientAPI = new ChatClientAPIImpl("localhost", 8888);
//        chatClientAPI.sendUserMessage(chatWithUserMessage);
//        System.out.println("Perfectly sent!");
//    }
}
