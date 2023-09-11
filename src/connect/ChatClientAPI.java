package view.connect;

import view.message.ChatQuesMessage;

import java.io.IOException;

public interface ChatClientAPI {
    public String getGPTAnswer(ChatQuesMessage chatQuesMessage)
            throws IOException;
}
