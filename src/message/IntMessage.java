package view.message;

public class IntMessage {
    private int num;

    // 无参构造函数
    public IntMessage() {
    }

    // 有参构造函数
    public IntMessage(int num) {
        this.num = num;
    }

    // 获取 num 的方法
    public int getNum() {
        return num;
    }

    // 设置 num 的方法
    public void setNum(int num) {
        this.num = num;
    }
}
