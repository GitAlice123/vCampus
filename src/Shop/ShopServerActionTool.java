package view.Shop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.message.*;
import view.server.ServerRWTool;

import java.io.OutputStream;
import java.net.Socket;


public class ShopServerActionTool {
    private ShopFunction funcs = new ShopFunction();
    private ServerRWTool ServerRWTool = new ServerRWTool();

    public ShopServerActionTool() {

    }

    /**
     * 对应findGoodST
     */
    public void Action900(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 900");


        //执行对应操作，这里是查找
        String[][] result;
        String query = uniqueMessage.getUniMessage();
        result = funcs.findGoodST(query);

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应查询商品管理员findGoodM
     */
    public void Action901(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 901");


        //执行对应操作，这里是查找
        String[][] result;
        String query = uniqueMessage.getUniMessage();
        result = funcs.findGoodM(query);

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应加入购物车addSelectedGood
     */
    public void Action902(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StringIntMessage stringIntMessage = null;
        try {
            stringIntMessage = objectMapper.readValue(jsonData, StringIntMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 902");


        //执行对应操作，这里是加入购物车
        Boolean flag = false;
        String goodName = stringIntMessage.getStr();
        int num = stringIntMessage.getNum();
        flag = funcs.addSelectedGood(goodName, num);

        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应getSelectedGood前端获取购物车数据
     */
    public void Action903(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 903");


        //执行对应操作
        Object[][] result;
        String query = uniqueMessage.getUniMessage();
        result = funcs.getSelectedGoods();

        //下面将response信息返回客户端
        Object[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应getAllGoodsST学生界面获取所有商品
     */
    public void Action904(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 904");


        //执行对应操作
        String[][] result;
        String query = uniqueMessage.getUniMessage();
        result = funcs.getAllGoodsST();
//        String[][] data=result;
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[i].length; j++) {
//                System.out.print(data[i][j] + " ");
//            }
//            System.out.println();
//        }

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 对应getAllGoodsM管理员界面获取所有商品
     */
    public void Action905(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 905");


        //执行对应操作
        String[][] result;
        String query = uniqueMessage.getUniMessage();
        result = funcs.getAllGoodsM();

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员进货ManagerAddGood
     */
    public void Action906(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        GoodMessage goodMessage = null;
        try {
            goodMessage = objectMapper.readValue(jsonData, GoodMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 906");


        //执行对应操作，这里是加入购物车
        Boolean flag = false;
        Good g = goodMessage.getG();
        flag = funcs.ManagerAddGood(g);

        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 管理员退货ManagerReduceGood
     */
    public void Action907(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StringIntMessage stringIntMessage = null;
        try {
            stringIntMessage = objectMapper.readValue(jsonData, StringIntMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 907");


        //执行对应操作，这里是加入购物车
        Boolean flag = false;
        String goodID = stringIntMessage.getStr();
        int num = stringIntMessage.getNum();
        flag = funcs.ManagerReduceGood(goodID, num);

        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 对应getAllPurchaseRecord获取所有购买记录，管理员
     */
    public void Action908(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 908");


        //执行对应操作
        String[][] result;
        String query = uniqueMessage.getUniMessage();
        result = funcs.getAllPurchaseRecord();

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找并返回数据库中一卡通号为uId的所有购买记录信息,对应getPurchaseRecordById获取所有购买记录，学生
     */
    public void Action909(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 909");


        //执行对应操作
        String[][] result;
        String uId = uniqueMessage.getUniMessage();
        result = funcs.getPurchaseRecordById(uId);

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应管理员进货界面用的findGoodAllInfo
     */
    public void Action910(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 910");


        //执行对应操作，这里是查找
        String[][] result;
        String query = uniqueMessage.getUniMessage();
        result = funcs.findGoodAllInfo(query);

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 对应删除购物车中商品函数removeSelectedGood
     */
    public void Action911(String jsonData, Socket clientSocket) {
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
        System.out.println("Into object 911");


        //执行对应操作，这里是加入购物车
        Boolean flag = false;
        String goodName = uniqueMessage.getUniMessage();
        flag = funcs.removeSelectedGood(goodName);

        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * addPurchaseRecord增加购买记录
     */
    public void Action912(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        PurchaseRecordMessage purchaseRecordMessage = null;
        try {
            purchaseRecordMessage = objectMapper.readValue(jsonData, PurchaseRecordMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 912");


        //执行对应操作，这里是加入购物车
        Boolean flag = false;
        PurchaseRecord p = purchaseRecordMessage.getPurchaseRecord();
        flag = funcs.addPurchaseRecord(p);

        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
