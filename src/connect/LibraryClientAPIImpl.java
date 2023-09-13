package view.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.Global.GlobalData;
import view.Library.Book;
import view.Library.BookHold;
import view.Library.BookOperationRecord;
import view.client.ClientRWTool;
import view.message.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 实现LibraryClientAPI接口的客户端API类，用于与图书馆服务进行通信。
 * 通过Socket连接到服务器，并发送各种请求以获取图书信息和执行图书操作。
 */
public class LibraryClientAPIImpl implements LibraryClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientRWTool ClientRWTool = new ClientRWTool();

    public LibraryClientAPIImpl(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有存储的图书列表。
     *
     * @param noDataReqMessage 无数据请求消息，用于触发获取存储的图书列表的操作。
     * @return 包含存储的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
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

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 200);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

//      处理结果
        Book[] result = bookListRespMessage.getBooks();

        return result;
    }

    /**
     * 获取特定用户借阅的图书列表。
     *
     * @param registerReqMessage 包含用户信息的请求消息，用于获取用户借阅的图书列表。
     * @return 包含用户借阅的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
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

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 201);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

//      处理结果
        Book[] result = bookListRespMessage.getBooks();

        return result;
    }


    /**
     * 根据图书名称搜索图书列表。
     *
     * @param searchBookNameMessage 包含搜索关键字的消息，用于搜索匹配的图书。
     * @return 包含匹配的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
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

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 202);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

//      处理结果
        Book[] result = bookListRespMessage.getBooks();

        return result;
    }


    /**
     * 删除特定图书。
     *
     * @param bookISBNMessage 包含图书ISBN的消息，用于删除指定图书。
     * @return 如果删除成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
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

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 203);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }


    /**
     * 添加新图书。
     *
     * @param bookMessage 包含新图书信息的消息，用于向服务器添加新图书。
     * @return 如果添加成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
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

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 204);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }


    /**
     * 借阅图书。
     *
     * @param bookOperationRecord 包含借阅记录的消息，用于借阅图书。
     * @return 如果借阅成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
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

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 205);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }


    /**
     * 归还图书。
     *
     * @param bookOperationRecord 包含归还记录的消息，用于归还图书。
     * @return 如果归还成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public Boolean ReturnBook(BookOperationRecord bookOperationRecord) throws IOException {
        //还书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookOperationRecord);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 206);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }


    /**
     * 修改图书信息。
     *
     * @param bookMessage 包含修改后的图书信息的消息，用于向服务器提交图书修改请求。
     * @return 如果修改成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public Boolean ChangeBook(Book bookMessage) throws IOException {
        //还书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 207);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }


    /**
     * 根据ISBN获取图书信息。
     *
     * @param bookISBNMessage 包含图书ISBN的消息，用于获取特定ISBN的图书信息。
     * @return 包含特定ISBN的图书信息。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public Book getBookByISBN(BookISBNMessage bookISBNMessage) throws IOException {
        //还书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookISBNMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 208);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        Book bookMessage = objectMapper.readValue(mess, Book.class);

        return bookMessage;
    }


    /**
     * 获取某用户当前借阅的图书列表。
     *
     * @param registerReqMessage 包含用户信息的请求消息，用于获取用户当前借阅的图书列表。
     * @return 包含用户当前借阅的图书信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
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

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 201);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookHoldListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookHoldListRespMessage.class);

//      处理结果
        BookHold[] result = bookListRespMessage.getBooks();

        return result;
    }


    /**
     * 获取下一个图书操作记录的唯一标识符（ID）。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取下一个操作记录ID的操作。
     * @return 下一个图书操作记录的唯一标识符（ID）。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public String getNextOPRId(UniqueMessage uniqueMessage) throws IOException {
        // 得到下一个操作编号
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 209);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookOPRListRespMessage bookOPRListRespMessage = objectMapper.readValue(mess, BookOPRListRespMessage.class);

//      处理结果
        BookOperationRecord[] result = bookOPRListRespMessage.getBooks();
        if (result == null) {
            int num = 1;
            String str = String.format("%011d", num);
            return str;
        }
        BookOperationRecord lastElement = result[result.length - 1];
        String lastOPRid = lastElement.getOprId();
        int number = Integer.parseInt(lastOPRid);
        int idNumber = number + 1;
        String str = String.format("%011d", idNumber);

        return str;
    }


    /**
     * 获取图书馆中所有图书的总数量。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取所有图书总数量的操作。
     * @return 图书馆中所有图书的总数量。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public int getTotalBooksNum(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 210);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

        int totalNum = 0;
//      处理结果
        Book[] books = bookListRespMessage.getBooks();
        for (Book book : books) {
            totalNum += book.getTotalNum();
        }
        return totalNum;

    }


    /**
     * 获取图书馆中可借阅的图书总数量。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取可借阅图书总数量的操作。
     * @return 图书馆中可借阅的图书总数量。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public int getFreeBooksNum(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 211);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

        int totalNum = 0;
//      处理结果
        Book[] books = bookListRespMessage.getBooks();
        for (Book book : books) {
            totalNum += book.getFreeNum();
        }
        return totalNum;
    }


    /**
     * 获取图书馆中已借阅的图书总数量。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取已借阅图书总数量的操作。
     * @return 图书馆中已借阅的图书总数量。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public int getBorrowedBooksNum(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 212);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookListRespMessage.class);

        int totalNum = 0;
//      处理结果
        Book[] books = bookListRespMessage.getBooks();
        for (Book book : books) {
            totalNum += book.getBorrowNum();
        }
        return totalNum;
    }


    /**
     * 续借图书。
     *
     * @param bookOperationRecord 包含续借记录的消息，用于续借图书。
     * @return 如果续借成功，则返回true；否则返回false。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public Boolean renewBook(BookOperationRecord bookOperationRecord) throws IOException {
        //添加图书
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bookOperationRecord);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 213);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        Boolean result = boolRespMessage.getFlag();

        return result;
    }


    /**
     * 获取图书馆的图书报告。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取图书馆图书报告的操作。
     * @return 包含图书报告信息的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public int[] getBookReport(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 214);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

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


    /**
     * 获取特定图书的所有操作记录。
     *
     * @param uniqueMessage 无数据请求消息，用于触发获取特定图书的所有操作记录的操作。
     * @return 包含图书操作记录的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public BookOperationRecord[] getBookAllOperationRecord(UniqueMessage uniqueMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 209);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BookOPRListRespMessage bookListRespMessage = objectMapper.readValue(mess, BookOPRListRespMessage.class);

//      处理结果
        BookOperationRecord[] result = bookListRespMessage.getBooks();

        return result;
    }


    /**
     * 获取特定用户的图书操作记录。
     *
     * @param registerReqMessage 包含用户信息的请求消息，用于获取特定用户的图书操作记录。
     * @return 包含用户图书操作记录的数组。
     * @throws IOException 如果与服务器通信时发生I/O错误，则抛出IOException。
     */
    @Override
    public BookOperationRecord[] getBookOprRecordByUid(RegisterReqMessage registerReqMessage) throws IOException {
        //发送书名，得到书籍列表
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(registerReqMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 215);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

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
