package view.message;

public class StringArrayRespMessage {
    private String[][] data;

    public StringArrayRespMessage(){

    }

    public StringArrayRespMessage(String[][] d){
        this.data=d;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

}
