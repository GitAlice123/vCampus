package view.connect;

import java.io.IOException;
import view.Library.*;
import view.message.*;

public interface ChatClientAPI {
    public String getGPTAnswer(ChatQuesMessage chatQuesMessage)
        throws IOException;
}
