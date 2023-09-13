package view.chat;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.IOException;

import view.Global.GlobalData;
import view.Global.SummaryUI;
import view.client.ClientReceiver;
import view.connect.*;
import view.message.*;

import java.util.*;

import java.util.List;


public class ChatFrameUI extends JFrame implements ClientReceiver.MessageCallback {
    private Timer waitTimer; // 定时器
    private Timer timer;
    private int dotCount; // 点号数量
    SpringLayout springLayout = new SpringLayout();
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;

    //底部
    JScrollPane bottompane;
    JTextArea textarea;
    JButton sendBtn;
    JButton backBtn;
    JComboBox<String> friends;
    ButtonGroup choose;
    JRadioButton AIassistant;
    JRadioButton schoolchat;

    //中部
    JScrollPane centerpane;
    JTextArea chathistory;
    JScrollPane leftpane;
    JTextArea online;


    //顶部
    JLabel welcomeLabel;

    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font centerFont = new Font("楷体", Font.PLAIN, 20);//设置中间组件的文字大小、字体

    //颜色
    Color bottomcolor=new Color(233,208,222);
    Color topcolor=new Color(152,193,202);
    String messageSent;

    private String messageReturn; // 保存服务器返回的消息

    public ChatFrameUI() throws IOException {

        super("ChatFrame");
        startReceivingMessages();
//        startReceivingOnline();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 每秒调用的函数
                try {
                    showAllOnlineList();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        startTimer();
        topPanel = new JPanel();
        centerPanel = new JPanel(springLayout){
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
        }
        ;
        bottomPanel = new JPanel();

        topPanel.setBackground(topcolor);
        bottomPanel.setBackground(bottomcolor);


        //底部
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


        //中部
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

        friends = new JComboBox<String>();
        friends.setFont(centerFont);
        //showAllOnlineList();


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
//                AIassistant = new JRadioButton("AI助手");
//                schoolchat = new JRadioButton("校内聊天");
                if (AIassistant.isSelected()) {
                    try {
                        sendMessage();
                    } catch (PrinterException ex) {
                        throw new RuntimeException(ex);
                    }
                    receiveGPTMessage();
                } else {
                    try {
                        sendMessage();
                    } catch (PrinterException ex) {
                        throw new RuntimeException(ex);
                    }
                    String selectedTarget = (String) friends.getSelectedItem();
                    try {
                        sendOthersMessage(selectedTarget);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
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

        chathistory.setOpaque(false);
        centerpane.setOpaque(false);
        centerpane.getViewport().setBackground(new Color(255,255,255,150));
        online.setOpaque(false);
        leftpane.setOpaque(false);
        leftpane.getViewport().setBackground(new Color(255,255,255,150));


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

    private void sendOthersMessage(String targerUser) throws IOException {
        ChatClientAPI chatClientAPI = new ChatClientAPIImpl("localhost", 8888);
        ChatWithUserMessage chatWithUserMessage = new ChatWithUserMessage(GlobalData.getUID(),messageSent,targerUser);
        chatClientAPI.sendUserMessage(chatWithUserMessage);
    }

    private String getWaitingMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dotCount; i++) {
            sb.append(".");
        }
        return sb.toString();
    }

    @Override
    public void onMessageReceived(ChatMessage message) {
        // 在这里处理收到的消息
//        System.out.println("Received message: " + message);
        chathistory.append(message.getRole() +": "+ message.getContent() + "\n");
    }

    public void startReceivingMessages() {
        ClientReceiver clientReceiver;
        try {
            clientReceiver = new ClientReceiver(this);  // 将当前 UI 文件实例作为回调接口
            clientReceiver.startReceiving();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
//    public void startReceivingOnline() {
//        ChatFrameUI currentUI = this;
//        try {
//            ClientOnlineReceiver clientReceiver = new ClientOnlineReceiver(new ClientOnlineReceiver.MessageCallback() {
//                @Override
//                public void onMessageReceived(OnlineListRespMessage message) {
//                    List<String>allOnline = message.getOnlineList();
//                    for (String name : allOnline) {
//                        currentUI.friends.addItem(name);
//                    }
//                }
//            });
//            clientReceiver.startReceiving();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void showAllOnlineList() throws IOException {
        ChatClientAPI chatClientAPI = new ChatClientAPIImpl("localhost", 8888);
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        List<String> allOnline = chatClientAPI.getAllOnlineName(uniqueMessage);

        // 比较序列和 JComboBox 中的元素是否相等
        Boolean areEqual = areElementsEqual(allOnline, friends);

        if(areEqual)
            return;
        else
        {
            friends.removeAllItems();
            online.setText("");
            for (String name : allOnline) {
                if(!Objects.equals(name, GlobalData.getUID())){
                    friends.addItem(name);
                    online.append(name+"\n");
                }
            }
        }

    }

    public static boolean areElementsEqual(List<String> sequence1, JComboBox<String> comboBox) {
        // 将序列转换为集合
        Set<String> set1 = new HashSet<>(sequence1);
        Set<String> set2 = new HashSet<>();

        // 将 JComboBox 中的元素添加到集合中
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            set2.add(comboBox.getItemAt(i));
        }

        // 比较集合的大小和元素是否相等
        return set1.size() == set2.size() && set1.containsAll(set2);
    }
}

