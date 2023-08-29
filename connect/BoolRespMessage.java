package connect;

public class BoolRespMessage {
    private boolean flag;

    public BoolRespMessage() {
        // 无参数构造函数
    }
    public BoolRespMessage(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag(){
        return flag;
    }
}
