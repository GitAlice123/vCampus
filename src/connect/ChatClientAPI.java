package view.connect;

import java.io.IOException;
import java.util.List;

import view.Library.*;
import view.message.*;

public interface ChatClientAPI {
    public String getGPTAnswer(ChatQuesMessage chatQuesMessage)
            throws IOException;

    public void sendUserMessage(ChatWithUserMessage chatWithUserMessage)
            throws IOException;

    public List<String> getAllOnlineName(UniqueMessage uniqueMessage)
            throws IOException;
}
