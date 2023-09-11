package view.message;

/* 这个用于客户端只发送请求但不需要发送数据表单的情况 */
public class NoDataReqMessage {
    String ReqMessage;

    public NoDataReqMessage() {
        // 无参数构造函数
        this.ReqMessage = "null";
    }
}
