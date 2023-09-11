package view.message;

import view.Library.BookHold;

public class BookHoldListRespMessage {
    private BookHold[] books;

    public BookHoldListRespMessage() {
        // 无参数构造函数
    }

    public BookHoldListRespMessage(BookHold[] books) {
        this.books = books;
    }

    public BookHold[] getBooks() {
        return books;
    }
}
