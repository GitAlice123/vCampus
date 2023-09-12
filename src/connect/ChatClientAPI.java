package view.connect;

import view.message.ChatQuesMessage;
import view.message.ChatWithUserMessage;

import java.io.IOException;

public interface ChatClientAPI {
    public String getGPTAnswer(ChatQuesMessage chatQuesMessage)
            throws IOException;

    public void sendUserMessage(ChatWithUserMessage chatWithUserMessage)
            throws IOException;
}
