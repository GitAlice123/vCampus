package view.message;

import view.Library.BookOperationRecord;

public class GPTAnsRepMessage {
    private String gptanswer;

    public GPTAnsRepMessage() {
        // 无参数构造函数
    }

    public GPTAnsRepMessage(String GPTanswer) {
        this.gptanswer = GPTanswer;
    }

    public String getGPTanswer() {
        return gptanswer;
    }
}
