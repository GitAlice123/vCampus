package view.chat;

import java.io.IOException;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import iew.connect.ChatClientAPI;
import view.conneimport view.message.ChatQuesMessage;

public lass Tet {
    public static void main(String[] args) throws IOException {
        ChatClientAPI chatClientAPI = new ChatClientAPIImpl("localhost",8888);
        ChatQuesMessage chatQuesMessage = new ChatQuesMessage("帮我写一个Python代码，输出hello world");
        String answer = chatClientAPI.getGPTAnswer(chatQuesMessage);
        System.out.println(answer);
    }
}

