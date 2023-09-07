package view.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.*;
import view.DAO.*;

import view.Library.*;

import view.Library.BookHold;
import view.Login.User;
import view.chat.Text;
import view.message.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

public class ServerActionTool {
    public ServerActionTool() {
    }

    private RWTool rwTool = new RWTool();

    private static final String KEY = "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";
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private static final String HOST = "localhost";
    private static final int PORT = 8888;

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
            flag = true;
        }
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

    public void Action101(String jsonData, Socket clientSocket) {
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

    public void Action102(String jsonData, Socket clientSocket) {
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

    public void Action200(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        System.out.println(jsonData);
        // 将 JSON 数据还原为对象
        UniqueMessage noDataReqMessage = null;
        try {
            noDataReqMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 200");
        /* 以下是和数据库交互部分,最后返回一个BookListRespMessage */
        // TODO:服务器端返回馆藏书籍列表
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

//        UserDao userdao = new UserDao();
//        view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
//        boolean flag = false;
//        // flag = true表示user已经在表单里了
//        if (user != null) {
//            flag = true;
//        }
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

    public void Action201(String jsonData, Socket clientSocket) {
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
        /* 以下是和数据库交互部分,最后返回一个BookHoldListRespMessage,表示用户借了哪些书 */
        //TODO:服务器端返回用户借书列表
        BookHoldDao bookDao = new BookHoldDao();
        BookHold[] bookHolds = bookDao.findBookHoldsById(registerReqMessage.getUserId());
        BookHoldListRespMessage bookHoldListRespMessage = new BookHoldListRespMessage(bookHolds);
//        UserDao userdao = new UserDao();
//        view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
//        boolean flag = false;
//        // flag = true表示user已经在表单里了
//        if (user != null) {
//            flag = true;
//        }
        //下面将response信息返回客户端
        try {
            String outputData = objectMapper.writeValueAsString(bookHoldListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action202(String jsonData, Socket clientSocket) {
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

    public void Action203(String jsonData, Socket clientSocket) {
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

    public void Action204(String jsonData, Socket clientSocket) {
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

    public void Action205(String jsonData, Socket clientSocket) {
        // 借阅书籍
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookOperationRecord bookOperationRecord = null;
        try {
            bookOperationRecord = objectMapper.readValue(jsonData, BookOperationRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 205");
        Boolean flag_1 = false;
        Boolean flag_2 = false;
        Boolean flag_3 = false;

        /* 以下是和数据库交互部分借阅图书 */


        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        // TODO:借书操作，需要传入用户id
        flag_2 = bookOperationRecordDao.AddBookOperationRecord(bookOperationRecord);

        BookHoldDao bookHoldDao = new BookHoldDao();
        flag_3 = bookHoldDao.Borrow(bookOperationRecord);

        Boolean flag = false;
        if (flag_3) {
            BookDao bookDao = new BookDao();
            flag_1 = bookDao.Borrow(bookOperationRecord.getISBN());
            flag = flag_1 && flag_2 && flag_3;
        } else {
            flag = false;
        }


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

    public void Action206(String jsonData, Socket clientSocket) {
        // 还书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookOperationRecord bookOperationRecord = null;
        try {
            bookOperationRecord = objectMapper.readValue(jsonData, BookOperationRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 206");
        Boolean flag_1 = false;
        Boolean flag_2 = false;
        Boolean flag_3 = false;

        /* 以下是和数据库交互部分借阅图书 */

        BookDao bookDao = new BookDao();
        flag_1 = bookDao.Return(bookOperationRecord.getISBN());

        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        // TODO:借书操作，需要传入用户id
        flag_2 = bookOperationRecordDao.AddBookOperationRecord(bookOperationRecord);

        BookHoldDao bookHoldDao = new BookHoldDao();
        flag_3 = bookHoldDao.Return(bookOperationRecord);

        Boolean flag = flag_1 && flag_2 && flag_3;
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

    public void Action207(String jsonData, Socket clientSocket) {
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

    public void Action208(String jsonData, Socket clientSocket) {
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

    public void Action209(String jsonData, Socket clientSocket) {
        // 取回所有操作记录
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 209");

        /* 以下是和数据库交互部分获取所有操作记录 */
        BookOperationRecord[] bookOperationRecords;
        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        bookOperationRecords = bookOperationRecordDao.findAllBookOperationRecord();

        BookOPRListRespMessage bookMessage = new BookOPRListRespMessage(bookOperationRecords);

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

    public void Action210(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 210");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

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

    public void Action211(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 211");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

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

    public void Action212(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 212");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

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

    public void Action213(String jsonData, Socket clientSocket) {
        // 续借书籍
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookOperationRecord bookOperationRecord = null;
        try {
            bookOperationRecord = objectMapper.readValue(jsonData, BookOperationRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 213");
        Boolean flag_2 = false;
        Boolean flag_3 = false;

        /* 以下是和数据库交互部分续借图书 */

        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        // TODO:借书操作，需要传入用户id
        flag_2 = bookOperationRecordDao.AddBookOperationRecord(bookOperationRecord);

        BookHoldDao bookHoldDao = new BookHoldDao();
        flag_3 = bookHoldDao.Renew(bookOperationRecord);

        Boolean flag = flag_2 && flag_3;

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

    public void Action214(String jsonData, Socket clientSocket) {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 214");
        BookDao bookDao = new BookDao();
        int[] report = bookDao.getReport();

        //下面将response信息返回客户端

        LibraryReportRespMessage bookListRespMessage = new LibraryReportRespMessage(report);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action215(String jsonData, Socket clientSocket) {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        RegisterReqMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 215");
        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        BookOperationRecord[] report = bookOperationRecordDao.findBookOperationRecordById(uniqueMessage.getUserId());

        //下面将response信息返回客户端

        BookAdminSearchRespMessage bookListRespMessage = new BookAdminSearchRespMessage(report);

        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action400(String jsonData, Socket clientSocket) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        ChatQuesMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, ChatQuesMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 400");




        //将服务器作为中转服务器和GPT连接
        String content = uniqueMessage.getqueString();
        // 创建 ObjectMapper 用于解析 JSON
        objectMapper = new ObjectMapper();
        Text text = new Text();
        // 设置模型
        text.setModel("gpt-3.5-turbo");
        text.setTemperature(0.7);
        text.setMessages(Collections.singletonList(new Text.MessagesBean("user", content)));
        // 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 创建请求体，携带 JSON 参数
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                objectMapper.writeValueAsString(text));
        // 创建请求
        Request request =
                new Request.Builder().url(URL).addHeader("Authorization", "Bearer ".concat(KEY)).post(requestBody).build();
        // 发送请求并处理响应
        JsonNode jsonNode = null;
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // 解析json 获取结果
            jsonNode = objectMapper.readTree(response.body().string());
            System.out.println(jsonNode.get("choices").get(0).get("message").get("content").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }



        //下面将response信息返回客户端
        String answer = jsonNode.get("choices").get(0).get("message").get("content").asText();
        GPTAnsRepMessage gptAnsRepMessage = new GPTAnsRepMessage(answer);

        try {
            String outputData = objectMapper.writeValueAsString(gptAnsRepMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


