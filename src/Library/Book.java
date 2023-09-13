package view.Library;

public class Book {
    String bookISBN;
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

    /**
     * 无参构造函数
     */
    public Book() {
    }

    /**
     * 有参构造函数
     * @param bookISBN ISBN码
     * @param bookName 书名
     * @param author 作者
     * @param bookType 书类型
     * @param bookPrice 书价格
     * @param publisher 出版社
     * @param summary 总结
     * @param totalNum 总量
     * @param freeNum 可借量
     * @param bookPos 摆放位置
     * @param borrowNum 借阅数量
     */
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


    /**
     * 得到书名
     * @return 书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置书名
     * @param bookName 书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 得到ISBN
     * @return ISBN码
     */
    public String getBookISBN() {
        return bookISBN;
    }

    /**
     * 设置ISBN
     * @param bookISBN
     */
    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    /**
     * 得到作者
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 得到书类型
     * @return 书类型
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * 设置书籍类型。
     *
     * @param bookType 书籍类型
     */
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    /**
     * 获取书籍价格。
     *
     * @return 书籍价格
     */
    public Double getBookPrice() {
        return bookPrice;
    }

    /**
     * 设置书籍价格。
     *
     * @param bookPrice 书籍价格
     */
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * 获取出版社。
     *
     * @return 出版社
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置出版社。
     *
     * @param publisher 出版社
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 获取书籍摘要。
     *
     * @return 书籍摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置书籍摘要。
     *
     * @param summary 书籍摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取书籍总数量。
     *
     * @return 书籍总数量
     */
    public int getTotalNum() {
        return totalNum;
    }

    /**
     * 设置书籍总数量。
     *
     * @param totalNum 书籍总数量
     */
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 获取可借阅的书籍数量。
     *
     * @return 可借阅的书籍数量
     */
    public int getFreeNum() {
        return freeNum;
    }

    /**
     * 设置可借阅的书籍数量。
     *
     * @param freeNum 可借阅的书籍数量
     */
    public void setFreeNum(int freeNum) {
        this.freeNum = freeNum;
    }

    /**
     * 获取书籍位置。
     *
     * @return 书籍位置
     */
    public String getBookPos() {
        return bookPos;
    }

    /**
     * 设置书籍位置。
     *
     * @param bookPos 书籍位置
     */
    public void setBookPos(String bookPos) {
        this.bookPos = bookPos;
    }

    /**
     * 获取借阅次数。
     *
     * @return 借阅次数
     */
    public int getBorrowNum() {
        return borrowNum;
    }

    /**
     * 设置借阅次数。
     *
     * @param borrowNum 借阅次数
     */
    public void setBorrowNum(int borrowNum) {
        this.borrowNum = borrowNum;
    }

    /**
     * 返回书籍对象的字符串表示。
     *
     * @return 书籍对象的字符串表示
     */
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

