package view.connect;

import java.io.IOException;
import java.util.List;

import view.Library.*;
import view.message.*;

public interface ChatClientAPI {
    /**
     * 获取GPT生成的回答。
     *
     * @param chatQuesMessage 聊天问题消息对象
     * @return GPT生成的回答
     * @throws IOException 如果发生通信错误
     */
    String getGPTAnswer(ChatQuesMessage chatQuesMessage)
            throws IOException;

    /**
     * 发送用户消息。
     *
     * @param chatWithUserMessage 用户聊天消息对象
     * @throws IOException 如果发生通信错误
     */
    void sendUserMessage(ChatWithUserMessage chatWithUserMessage)
            throws IOException;

    /**
     * 获取所有在线用户的名称列表。
     *
     * @param uniqueMessage 唯一消息对象
     * @return 在线用户名称列表
     * @throws IOException 如果发生通信错误
     */
    List<String> getAllOnlineName(UniqueMessage uniqueMessage)
            throws IOException;
}
