package view.message;

public class BookISBNMessage {
    // 包含索书号的请求
    private String bookISBN;

    public BookISBNMessage() {
        // 无参数构造函数
    }
    public BookISBNMessage(String bookISBN) {
        this.bookISBN=bookISBN;
    }

    public String getBookISBN(){
        return bookISBN;
    }
}
