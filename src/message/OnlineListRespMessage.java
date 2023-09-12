package view.message;

import java.util.List;

public class OnlineListRespMessage {
    private List<String> onlineList;

    public OnlineListRespMessage() {
    }

    public OnlineListRespMessage(List<String> onlineList) {
        this.onlineList = onlineList;
    }

    public List<String> getOnlineList() {
        return onlineList;
    }

    public void setOnlineList(List<String> onlineList) {
        this.onlineList = onlineList;
    }
}
