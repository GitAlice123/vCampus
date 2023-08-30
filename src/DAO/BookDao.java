package view.DAO;

import view.Library.Book;

import java.sql.*;

public class BookDao {

    public Book findBookByISBN(String ISBN){

        Book book = new Book();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\Database\\vCampus.mdb", "", "");
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

}
