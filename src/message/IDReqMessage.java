package view.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IDReqMessage {
    @JsonProperty("id")
    private  String ID;
    public IDReqMessage(String ID) {
        this.ID = ID;
    }

    public IDReqMessage() {
    }

    public String getID() {
        return ID;
    }
}
