package view.connect;

import java.io.IOException;

import view.Library.*;
import view.message.*;

public interface LibraryClientAPI {
    // 让服务器调取当前在馆书籍列表
    public Book[] getStoredBookList(UniqueMessage noDataReqMessage)
            throws IOException;

    public Book[] getBorrowedBooksByUserId(RegisterReqMessage registerReqMessage)
            throws IOException;

    public Book[] getBooksBySearchBookName(SearchBookNameMessage searchBookNameMessage)
            throws IOException;

    public Boolean deleteBook(BookISBNMessage bookISBNMessage)
            throws IOException;

    public Boolean AddBook(Book bookMessage)
            throws IOException;

    public Boolean BorrowBook(BookOperationRecord bookOperationRecord)
            throws IOException;

    public Boolean ReturnBook(BookOperationRecord bookOperationRecord)
            throws IOException;

    public Boolean ChangeBook(Book bookMessage)
            throws IOException;

    public Book getBookByISBN(BookISBNMessage bookISBNMessage)
            throws IOException;

    public BookHold[] getBorrowedBooks(RegisterReqMessage registerReqMessage)
            throws IOException;

    public String getNextOPRId(UniqueMessage uniqueMessage)
            throws IOException;

}
