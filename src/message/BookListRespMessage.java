package view.message;

import view.Library.Book;

public class BookListRespMessage {
    private Book[] books;

    public BookListRespMessage() {
        // 无参数构造函数
    }

    public BookListRespMessage(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }
}
