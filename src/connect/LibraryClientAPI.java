package view.connect;

import view.Library.Book;
import view.Library.BookHold;
import view.Library.BookOperationRecord;
import view.message.BookISBNMessage;
import view.message.RegisterReqMessage;
import view.message.SearchBookNameMessage;
import view.message.UniqueMessage;

import java.io.IOException;

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

    public int getTotalBooksNum(UniqueMessage uniqueMessage)
            throws IOException;

    public int getFreeBooksNum(UniqueMessage uniqueMessage)
            throws IOException;

    public int getBorrowedBooksNum(UniqueMessage uniqueMessage)
            throws IOException;

    public Boolean renewBook(BookOperationRecord bookOperationRecord)
            throws IOException;

    public int[] getBookReport(UniqueMessage uniqueMessage)
            throws IOException;

    public BookOperationRecord[] getBookAllOperationRecord(UniqueMessage uniqueMessage)
            throws IOException;

    public BookOperationRecord[] getBookOprRecordByUid(RegisterReqMessage registerReqMessage)
            throws IOException;
}
