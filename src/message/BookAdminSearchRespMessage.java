package view.message;

import view.Library.BookOperationRecord;

public class BookAdminSearchRespMessage {
    private BookOperationRecord[] bookReports;

    public BookAdminSearchRespMessage() {
        // 无参数构造函数
    }

    public BookAdminSearchRespMessage(BookOperationRecord[] bookReports) {
        this.bookReports = bookReports;
    }

    public BookOperationRecord[] getBookReports() {
        return bookReports;
    }

}
