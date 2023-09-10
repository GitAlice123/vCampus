package view.chat;

import java.io.IOException;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import view.connect.ChatClientAPI;
import view.connect.*;
import view.message.ChatQuesMessage;

public class Test {
    public static void main(String[] args) throws IOException {
        ChatClientAPI chatClientAPI = new ChatClientAPIImpl("localhost",8888);
        ChatQuesMessage chatQuesMessage = new ChatQuesMessage("帮我解析一首李白的诗");
        String answer = chatClientAPI.getGPTAnswer(chatQuesMessage);
        System.out.println(answer);
    }
}

