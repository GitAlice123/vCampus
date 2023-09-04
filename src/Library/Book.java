package view.Library;
public class Book {
    String bookISBN;

    public Book() {
    }

    String bookName;
    String author;
    String bookType;
    Double bookPrice;
    String publisher;
    String summary;
    int totalNum;
    int freeNum;
    String bookPos;
    int borrowNum;

    public Book(String bookISBN, String bookName, String author, String bookType, Double bookPrice, String publisher, String summary, int totalNum, int freeNum, String bookPos, int borrowNum) {
        this.bookISBN = bookISBN;
        this.bookName = bookName;
        this.author = author;
        this.bookType = bookType;
        this.bookPrice = bookPrice;
        this.publisher = publisher;
        this.summary = summary;
        this.totalNum = totalNum;
        this.freeNum = freeNum;
        this.bookPos = bookPos;
        this.borrowNum = borrowNum;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(int freeNum) {
        this.freeNum = freeNum;
    }

    public String getBookPos() {
        return bookPos;
    }

    public void setBookPos(String bookPos) {
        this.bookPos = bookPos;
    }

    public int getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(int borrowNum) {
        this.borrowNum = borrowNum;
    }


    @Override
    public String toString() {
        return "Book{" +
                "bookISBN='" + bookISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", bookType='" + bookType + '\'' +
                ", bookPrice=" + bookPrice +
                ", publisher='" + publisher + '\'' +
                ", summary='" + summary + '\'' +
                ", totalNum=" + totalNum +
                ", freeNum=" + freeNum +
                ", bookPos='" + bookPos + '\'' +
                ", borrowNum=" + borrowNum +
                '}';
    }
}

