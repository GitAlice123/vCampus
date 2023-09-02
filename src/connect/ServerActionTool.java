package view.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import view.DAO.*;

import view.Library.*;

import view.Login.User;
import view.message.*;

import java.io.OutputStream;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerActionTool {
    public ServerActionTool() {
    }
    private RWTool rwTool = new RWTool();
    public void Action100(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        LoginMessage loginMessage = null;
        try {
            loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 100");
        UserDao userdao = new UserDao();
        view.Login.User user = userdao.findUserByuId(loginMessage.getUserId());
        boolean flag = false;
        if (user != null && user.getuPwd().equals(loginMessage.getPassword())
                && user.getuRole().equals(loginMessage.getRole())) {
            flag = true;}
            //下面将response信息返回客户端
            BoolRespMessage respMessage = new BoolRespMessage(flag);
            try {
                // 将 LoginMessage 对象转换为 JSON 字符串
                String outputData = objectMapper.writeValueAsString(respMessage);
                OutputStream outputStream = clientSocket.getOutputStream();
                rwTool.ServerSendOutStream(outputStream, outputData);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void Action101(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        RegisterReqMessage registerReqMessage = null;
        try {
            registerReqMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 101");
        UserDao userdao = new UserDao();
        view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
        boolean flag = false;
        // flag = true表示user已经在表单里了
        if (user != null) {
            flag = true;
        }
        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(respMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action102(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        LoginMessage loginMessage = null;
        try {
            loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 102");

        Boolean flag = false;

        String userId = loginMessage.getUserId();
        String password = loginMessage.getPassword();
        String role = loginMessage.getRole();
        User user = new User();
        user.setuId(userId);
        user.setuPwd(password);
        user.setuRole(role);
        UserDao userdao = new UserDao();
        userdao.CreateUser(user);//创建新用户
        flag = true;

        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action200(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        NoDataReqMessage noDataReqMessage = null;
        try {
            noDataReqMessage = objectMapper.readValue(jsonData, NoDataReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 200");
        /* 以下是和数据库交互部分,最后返回一个BookListRespMessage */
        // TODO:服务器端返回馆藏书籍列表
//        UserDao userdao = new UserDao();
//        view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
//        boolean flag = false;
//        // flag = true表示user已经在表单里了
//        if (user != null) {
//            flag = true;
//        }
        //下面将response信息返回客户端
        Book bookList[]={};
        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action201(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        RegisterReqMessage registerReqMessage = null;
        try {
            registerReqMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 201");
        /* 以下是和数据库交互部分,最后返回一个BookListRespMessage,表示用户借了哪些书 */
        //TODO:服务器端返回用户借书列表
//        UserDao userdao = new UserDao();
//        view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
//        boolean flag = false;
//        // flag = true表示user已经在表单里了
//        if (user != null) {
//            flag = true;
//        }
        //下面将response信息返回客户端
        Book[] bookList ={};
        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action202(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        SearchBookNameMessage searchBookNameMessage = null;
        try {
            searchBookNameMessage = objectMapper.readValue(jsonData, SearchBookNameMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 202");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findBookByBookName(searchBookNameMessage.getBookName());

        //下面将response信息返回客户端

        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action203(String jsonData, Socket clientSocket){
        // 删除图书,成功返回true
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookISBNMessage bookISBNMessage = null;
        try {
            bookISBNMessage = objectMapper.readValue(jsonData, BookISBNMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 203");
        Boolean flag = false;
        /* 以下是和数据库交互部分删除图书 */
        BookDao bookDao = new BookDao();
        flag = bookDao.DeleteBook(bookISBNMessage.getBookISBN());


        //下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action204(String jsonData, Socket clientSocket){
        // 增加图书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        Book bookMessage = null;
        try {
            bookMessage = objectMapper.readValue(jsonData, Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 204");
        Boolean flag = false;
        /* 以下是和数据库交互部分增加图书 */

        BookDao bookDao = new BookDao();
        flag = bookDao.addBook(bookMessage);

        //下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action205(String jsonData, Socket clientSocket){
        // 借阅书籍
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookISBNMessage bookISBNMessage = null;
        try {
            bookISBNMessage = objectMapper.readValue(jsonData, BookISBNMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 205");
        Boolean flag = false;
        /* 以下是和数据库交互部分借阅图书 */

        BookDao bookDao = new BookDao();
        flag = bookDao.Borrow(bookISBNMessage.getBookISBN());

        //下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action206(String jsonData, Socket clientSocket){
        // 还书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookISBNMessage bookISBNMessage = null;
        try {
            bookISBNMessage = objectMapper.readValue(jsonData, BookISBNMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 206");
        Boolean flag = false;
        /* 以下是和数据库交互部分还书 */

        BookDao bookDao = new BookDao();
        flag = bookDao.Return(bookISBNMessage.getBookISBN());
        // TODO:还书还要调用其他函数

        //下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action207(String jsonData, Socket clientSocket){
        // 修改图书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        Book bookMessage = null;
        try {
            bookMessage = objectMapper.readValue(jsonData, Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 207");
        Boolean flag = false;
        /* 以下是和数据库交互部分更改图书 */
        BookDao bookDao = new BookDao();
        flag = bookDao.ModifyBook(bookMessage);

        //下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action208(String jsonData, Socket clientSocket){
        // 用ISBN找书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookISBNMessage bookISBNMessage = null;
        try {
            bookISBNMessage = objectMapper.readValue(jsonData, BookISBNMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 208");
        Book bookMessage;
        /* 以下是和数据库交互部分获取图书 */
        BookDao bookDao = new BookDao();
        bookMessage = bookDao.findBookByISBN(bookISBNMessage.getBookISBN());

        //下面将response信息返回客户端
        try {
            String outputData = objectMapper.writeValueAsString(bookMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


