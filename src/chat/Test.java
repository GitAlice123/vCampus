package view.chat;

import view.connect.ChatClientAPI;
import view.connect.ChatClientAPIImpl;
import view.message.ChatQuesMessage;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        ChatClientAPI chatClientAPI = new ChatClientAPIImpl("localhost", 8888);
        ChatQuesMessage chatQuesMessage = new ChatQuesMessage("帮我解析一首李白的诗");
        String answer = chatClientAPI.getGPTAnswer(chatQuesMessage);
        System.out.println(answer);
    }
}

