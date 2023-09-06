package view.connect;

import java.io.*;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.Library.*;
import view.message.*;

public class LibraryClientAPIImpl implements LibraryClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private RWTool rwTool = new RWTool();

    public LibraryClientAPIImpl(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(serverAddress, serverPort);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book[] getStoredBookList(UniqueMessage noDataReqMessage)
            throws IOException {
        //以下发送无数据的请求消息给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 Message 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(noDataReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 200);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

//      处理结果
        Book[] result = bookListRespMessage.getBooks();

        return result;
    }

    @Override
    public Book[] getBorrowedBooksByUserId(RegisterReqMessage registerReqMessage)
            throws IOException {
        //以下发送无数据的请求消息给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(registerReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 201);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

//      处理结果
        Book[] result = bookListRespMessage.getBooks();

        return result;
    }

    @Override
    public Book[] getBooksBySearchBookName(SearchBookNameMessage searchBookNameMessage)
            throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(searchBookNameMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 202);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

//      处理结果
        Book[] result = bookListRespMessage.getBooks();

        return result;
    }

    @Override
    public Boolean deleteBook(BookISBNMessage bookISBNMessage)
            throws IOException {
        //以下发送无数据的请求消息给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookISBNMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 203);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }

    @Override
    public Boolean AddBook(Book bookMessage)
            throws IOException {
        //添加图书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 204);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }

    @Override
    public Boolean BorrowBook(BookOperationRecord bookOperationRecord)
            throws IOException {
        //添加图书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookOperationRecord);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 205);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }

    @Override
    public Boolean ReturnBook(BookOperationRecord bookOperationRecord) throws IOException {
        //还书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookOperationRecord);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 206);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }

    @Override
    public Boolean ChangeBook(Book bookMessage) throws IOException {
        //还书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 207);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }

    @Override
    public Book getBookByISBN(BookISBNMessage bookISBNMessage) throws IOException {
        //还书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookISBNMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 208);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        Book bookMessage = objectMapper.readValue(mess, Book.class);

        return bookMessage;
    }

    @Override
    public BookHold[] getBorrowedBooks(RegisterReqMessage registerReqMessage)
            throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(registerReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 201);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookHoldListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookHoldListRespMessage.class);

//      处理结果
        BookHold[] result = bookListRespMessage.getBooks();

        return result;
    }

    @Override
    public String getNextOPRId(UniqueMessage uniqueMessage) throws IOException {
        // 得到下一个操作编号
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 209);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookOPRListRespMessage bookOPRListRespMessage = objectMapper.readValue(mess, BookOPRListRespMessage.class);

//      处理结果
        BookOperationRecord[] result = bookOPRListRespMessage.getBooks();
        if(result==null)
        {
            int num = 1;
            String str = String.format("%011d", num);
            return str;
        }
        BookOperationRecord lastElement = result[result.length - 1];
        String lastOPRid = lastElement.getOprId();
        int number = Integer.parseInt(lastOPRid);
        int idNumber = number+1;
        String str = String.format("%011d", idNumber);

        return str;
    }

    @Override
    public int getTotalBooksNum(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 210);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

        int totalNum=0;
//      处理结果
        Book[] books = bookListRespMessage.getBooks();
        for (Book book : books) {
            totalNum+=book.getTotalNum();
        }
        return totalNum;

    }

    @Override
    public int getFreeBooksNum(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 211);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

        int totalNum=0;
//      处理结果
        Book[] books = bookListRespMessage.getBooks();
        for (Book book : books) {
            totalNum+=book.getFreeNum();
        }
        return totalNum;
    }

    @Override
    public int getBorrowedBooksNum(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 212);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

        int totalNum=0;
//      处理结果
        Book[] books = bookListRespMessage.getBooks();
        for (Book book : books) {
            totalNum+=book.getBorrowNum();
        }
        return totalNum;
    }

    @Override
    public Boolean renewBook(BookOperationRecord bookOperationRecord) throws IOException {
        //添加图书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookOperationRecord);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 213);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }

    @Override
    public int[] getBookReport(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 214);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        LibraryReportRespMessage bookListRespMessage = objectMapper.readValue(mess, LibraryReportRespMessage.class);

        int[] report = new int[0];
//      处理结果
        report = bookListRespMessage.getBookReports();

        return report;
    }

    @Override
    public BookOperationRecord[] getBookAllOperationRecord(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 209);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookOPRListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookOPRListRespMessage.class);

//      处理结果
        BookOperationRecord[] result = bookListRespMessage.getBooks();

        return result;
    }

    @Override
    public BookOperationRecord[] getBookOprRecordByUid(RegisterReqMessage registerReqMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(registerReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 215);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookAdminSearchRespMessage bookListRespMessage = objectMapper.readValue(mess, BookAdminSearchRespMessage.class);

//      处理结果
        BookOperationRecord[] result = bookListRespMessage.getBookReports();

        return result;
    }

}
