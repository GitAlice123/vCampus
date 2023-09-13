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
    /**
     * 获取所有存储的图书列表。
     *
     * @param noDataReqMessage 无数据请求消息，用于触发获取存储的图书列表的操作。
     * @return 包含存储的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Book[] getStoredBookList(UniqueMessage noDataReqMessage)
            throws IOException;

    /**
     * 获取特定用户借阅的图书列表。
     *
     * @param registerReqMessage 包含用户信息的请求消息，用于获取用户借阅的图书列表。
     * @return 包含用户借阅的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Book[] getBorrowedBooksByUserId(RegisterReqMessage registerReqMessage)
            throws IOException;


    /**
     * 根据图书名称搜索图书列表。
     *
     * @param searchBookNameMessage 包含搜索关键字的消息，用于搜索匹配的图书。
     * @return 包含匹配的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Book[] getBooksBySearchBookName(SearchBookNameMessage searchBookNameMessage)
            throws IOException;


    /**
     * 删除特定图书。
     *
     * @param bookISBNMessage 包含图书ISBN的消息，用于删除指定图书。
     * @return 如果删除成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Boolean deleteBook(BookISBNMessage bookISBNMessage)
            throws IOException;


    /**
     * 添加新图书。
     *
     * @param bookMessage 包含新图书信息的消息，用于向服务器添加新图书。
     * @return 如果添加成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Boolean AddBook(Book bookMessage)
            throws IOException;


    /**
     * 借阅图书。
     *
     * @param bookOperationRecord 包含借阅记录的消息，用于借阅图书。
     * @return 如果借阅成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Boolean BorrowBook(BookOperationRecord bookOperationRecord)
            throws IOException;


    /**
     * 归还图书。
     *
     * @param bookOperationRecord 包含归还记录的消息，用于归还图书。
     * @return 如果归还成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Boolean ReturnBook(BookOperationRecord bookOperationRecord)
            throws IOException;


    /**
     * 修改图书信息。
     *
     * @param bookMessage 包含修改后的图书信息的消息，用于向服务器提交图书修改请求。
     * @return 如果修改成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Boolean ChangeBook(Book bookMessage)
            throws IOException;


    /**
     * 根据ISBN获取图书信息。
     *
     * @param bookISBNMessage 包含图书ISBN的消息，用于获取特定ISBN的图书信息。
     * @return 包含特定ISBN的图书信息。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Book getBookByISBN(BookISBNMessage bookISBNMessage)
            throws IOException;

    /**
     * 获取某用户当前借阅的图书列表。
     *
     * @param registerReqMessage 包含用户信息的请求消息，用于获取用户当前借阅的图书列表。
     * @return 包含用户当前借阅的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public BookHold[] getBorrowedBooks(RegisterReqMessage registerReqMessage)
            throws IOException;


    /**
     * 获取下一个图书操作记录的唯一标识符（ID）。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取下一个操作记录ID的操作。
     * @return 下一个图书操作记录的唯一标识符（ID）。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public String getNextOPRId(UniqueMessage uniqueMessage)
            throws IOException;


    /**
     * 获取图书馆中所有图书的总数量。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取所有图书总数量的操作。
     * @return 图书馆中所有图书的总数量。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public int getTotalBooksNum(UniqueMessage uniqueMessage)
            throws IOException;


    /**
     * 获取图书馆中可借阅的图书总数量。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取可借阅图书总数量的操作。
     * @return 图书馆中可借阅的图书总数量。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public int getFreeBooksNum(UniqueMessage uniqueMessage)
            throws IOException;


    /**
     * 获取图书馆中已借阅的图书总数量。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取已借阅图书总数量的操作。
     * @return 图书馆中已借阅的图书总数量。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public int getBorrowedBooksNum(UniqueMessage uniqueMessage)
            throws IOException;


    /**
     * 续借图书。
     *
     * @param bookOperationRecord 包含续借记录的消息，用于续借图书。
     * @return 如果续借成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public Boolean renewBook(BookOperationRecord bookOperationRecord)
            throws IOException;


    /**
     * 获取图书馆的图书报告。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取图书馆图书报告的操作。
     * @return 包含图书报告信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public int[] getBookReport(UniqueMessage uniqueMessage)
            throws IOException;


    /**
     * 获取特定图书的所有操作记录。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取特定图书的所有操作记录的操作。
     * @return 包含图书操作记录的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public BookOperationRecord[] getBookAllOperationRecord(UniqueMessage uniqueMessage)
            throws IOException;


    /**
     * 获取特定用户的图书操作记录。
     *
     * @param registerReqMessage 包含用户信息的请求消息，用于获取特定用户的图书操作记录。
     * @return 包含用户图书操作记录的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    public BookOperationRecord[] getBookOprRecordByUid(RegisterReqMessage registerReqMessage)
            throws IOException;
}
