package view.message;

import view.Library.BookOperationRecord;

public class BookOPRListRespMessage {
    private BookOperationRecord[] books;

    public BookOPRListRespMessage() {
        // 无参数构造函数
    }

    public BookOPRListRespMessage(BookOperationRecord[] books) {
        this.books = books;
    }

    public BookOperationRecord[] getBooks() {
        return books;
    }
}
