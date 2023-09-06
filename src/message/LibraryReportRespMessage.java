package view.message;

import view.Library.BookHold;

public class LibraryReportRespMessage {
    private int[] bookReports;

    public LibraryReportRespMessage() {
        // 无参数构造函数
    }

    public LibraryReportRespMessage(int[] bookReports) {
        this.bookReports = bookReports;
    }

    public int[] getBookReports() {
        return bookReports;
    }
}
