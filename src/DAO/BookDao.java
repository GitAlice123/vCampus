package view.DAO;

import view.Library.*;
//import vo.Grade;

import java.sql.*;
public class BookDao {


    /**
     * 根据书号查询书
     *
     * @param ISBN 书号
     * @return 查询到的书本对象
     */
    public Book findBookByISBN(String ISBN){

        Book book = new Book("","","","",0.0,"","",0,0,"",0);

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblBook where bookISBN = '" + ISBN + "'");

            if(!res.next()){return null;}
            res.beforeFirst();
            res.next();

            book.setBookISBN(res.getString(1));
            book.setBookName(res.getString(2));
            book.setAuthor(res.getString(3));
            book.setBookType(res.getString(4));
            book.setBookPrice(res.getDouble(5));
            book.setPublisher(res.getString(6));
            book.setSummary(res.getString(7));
            book.setTotalNum(res.getInt(8));
            book.setFreeNum(res.getInt(9));
            book.setBookPos(res.getString(10));
            book.setBorrowNum(res.getInt(11));

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 根据书名查询书
     *
     * @param bookname 书名
     * @return 查询到的书本对象，因为可能存在书名相同（作者不同）的情况，因此返回一个Book类的数组
     */
    public Book[] findBookByBookName(String bookname){
        String sqlString = "select * from tblBook where bookname = '" + bookname + "'";
        Book[] allBooks = new Book[10];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            int count =0;
            while (res.next()){
                count++;
            }//统计一共查找到多少条数据
            res.beforeFirst();

            if(count == 0){
                return null;
            }//若数据库中无书籍信息，则返回null

            allBooks = new Book[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                allBooks[index] = new Book(res.getString(1),res.getString(2),res.getString(3),
                        res.getString(4),res.getDouble(5),res.getString(6),res.getString(7),
                        res.getInt(8),res.getInt(9),res.getString(10),res.getInt(11));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBooks;
    }


    /**
     * 向数据库中新增插入书本
     *
     * @param book 需新增的书本
     * @return 新增插入是否成功,若数据库中原本已存在该书籍，则返回false，不执行新增插入操作
     */
    public boolean addBook(Book book){
        String sqlString1 = "select * from tblBook where bookISBN = '" + book.getBookISBN() + "'";
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(res.next()){
                con.close();//关闭数据库连接
                return false;
            } //若该书本在数据库中原本已存在，则返回false，不执行新增操作

        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sqlString2 = "insert into tblBook values('" + book.getBookISBN() + "','" + book.getBookName() + "','" + book.getAuthor()+ "','"
                + book.getBookType() + "'," + book.getBookPrice() + ",'" + book.getPublisher() + "','" + book.getSummary()
                + "'," + book.getTotalNum() + "," + book.getFreeNum() + ",'" + book.getBookPos() + "'," + book.getBorrowNum() +")";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\db\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            sta.executeUpdate(sqlString2);

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * 根据书号查询书
     *
     * @param ISBN 书号
     * @return 删除操作是否成功，如该书原本在数据库中不存在，则返回false，并不进行删除操作
     */
    public boolean DeleteBook(String ISBN){
        String sqlString = "delete from tblBook where bookISBN = '" + ISBN + "'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString);
            if(count == 0) return false; //若未进行删除操作，则原书不在数据库中，返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 修改书籍信息(可修改书名、作者、书本类型、出版社、位置)
     *
     * @param book 修改的书籍（存储的是修改后新书的信息）
     * @return 修改操作是否成功，如该书原本在数据库中不存在，则返回false，并不进行修改操作
     */
    public boolean ModifyBook(Book book){
        String sqlString = "update tblBook set bookname = '"+ book.getBookName() + "',author = '" + book.getAuthor() + "',bookType = '"
                + book.getBookType() + "',publisher ='" + book.getPublisher() + "',bookPos = '" + book.getBookPos() + "' where bookISBN = '" + book.getBookISBN() +"'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString);
            if(count == 0) return false; //若未进行修改操作，则说明该书籍原本在数据库中不存在，返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    /**
     * 根据书号借书，将该书籍在馆册数减一，被借次数加一
     *
     * @param ISBN 书号
     * @return 借书操作是否成功，如该书原本在数据库中不存在，或在馆册数不足以借阅则返回false，并不进行借书操作
     */
    public boolean Borrow(String ISBN){
        String sqlString1 = "select * from tblBook where bookISBN = '" + ISBN + "'";
        int old_FreeNum =0;
        int old_BorrowNum=0;

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){return false;} //若借阅数据原本在数据库中不存在，则返回false，并不进行借书操作
            res.beforeFirst();
            res.next();

            old_FreeNum=res.getInt(9);
            old_BorrowNum=res.getInt(11);
            if(old_FreeNum==0){
                return false;
            } //若该书本当前在馆册数为0，则不可借阅，返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int new_FreeNum =old_FreeNum-1;
        int new_BorrowNum=old_BorrowNum+1;
        String sqlString2 = "update tblBook set freeNum = "+ new_FreeNum + ",borrowNum =" + new_BorrowNum + " where bookISBN = '" + ISBN +"'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString2);
            if(count == 0) return false; //若未进行修改操作，则说明该书籍原本在数据库中不存在，返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * 根据书号还书，将该书籍在馆册数加一
     *
     * @param ISBN 书号
     * @return 还书操作是否成功，如该书原本在数据库中不存在，或还书后在馆册数大于馆藏册数，则返回false，并不进行借书操作
     */
    public boolean Return(String ISBN){
        String sqlString1 = "select * from tblBook where bookISBN = '" + ISBN + "'";
        int old_FreeNum =0;
        int totalNum=0;

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){return false;} //若借阅数据原本在数据库中不存在，则返回false，并不进行借书操作
            res.beforeFirst();
            res.next();

            old_FreeNum=res.getInt(9);
            totalNum=res.getInt(8);
            if(old_FreeNum>=totalNum){
                return false;
            } //若该书本当前在馆册数大于或等于馆藏册数，则说明该书不是本图书馆的，来路不明，返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int new_FreeNum =old_FreeNum+1;
        String sqlString2 = "update tblBook set freeNum = "+ new_FreeNum  + " where bookISBN = '" + ISBN +"'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString2);
            if(count == 0) return false; //若未进行修改操作，则说明该书籍原本在数据库中不存在，返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * 根据所有书籍
     *
     * @return 查询到的所有书本对象，返回一个Book类的数组
     */
    public Book[] findAllBooks(){
        String sqlString = "select * from tblBook";
        Book[] allBooks = new Book[10];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if(count == 0){
                return null;
            }//若数据库中无书籍信息，则返回null

            allBooks = new Book[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                allBooks[index] = new Book(res.getString(1),res.getString(2),res.getString(3),
                        res.getString(4),res.getDouble(5),res.getString(6),res.getString(7),
                        res.getInt(8),res.getInt(9),res.getString(10),res.getInt(11));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBooks;
    }

}