package view.message;

public class SearchBookNameMessage {
    // 把书名作为请求内容发送
    private String bookName;

    public SearchBookNameMessage() {
        // 通过书名找书
    }

    public SearchBookNameMessage(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }
}
