package view.Shop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.client.ClientRWTool;
import view.message.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IShopClientAPIImpl implements IShopClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientRWTool ClientRWTool = new ClientRWTool();

    /**
     * 构造函数
     * */
    public IShopClientAPIImpl(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(serverAddress, serverPort);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据商品ID或商品名称查询商品信息，供学生使用。900
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    public String[][] findGoodST(String query) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage(query);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 900);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 根据商品ID或商品名称查询商品信息，供管理员使用。901
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    public String[][] findGoodM(String query) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage(query);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 901);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    /**
     * 根据商品ID或商品名称查询商品信息，并返回包含这个商品所有属性数据的String[][]  910
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    public String[][] findGoodAllInfo(String query) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage(query);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 910);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 将指定名称的商品按照用户输入的数量加入购物车。902
     *
     * @param goodName 商品名称
     * @param num      商品数量
     * @return 如果成功加入购物车，返回true；否则返回false
     */
    public boolean addSelectedGood(String goodName, int num) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            StringIntMessage stringIntMessage = new StringIntMessage(goodName, num);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(stringIntMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 902);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = null;
        try {
            boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        boolean result = boolRespMessage.getFlag();
        return result;
    }


    /**
     * 返回购物车中的商品列表，以String[][]形式表示。903
     *
     * @return 购物车中商品的String[][]形式
     */
    public Object[][] getSelectedGoods() {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage("");//实际上这个函数没有输入

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 903);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        Object[][] result = null;
        try {
            result = objectMapper.readValue(mess, Object[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    /**
     * 返回商店中所有商品的列表，以String[][]形式表示。学生用904
     *
     * @return 商店中所有商品的String[][]形式
     */
    public String[][] getAllGoodsST() {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage("");//实际上这个函数没有输入

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 904);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    /**
     * 返回商店中所有商品的列表，以String[][]形式表示。管理员用 905
     *
     * @return 商店中所有商品的String[][]形式
     */
    public String[][] getAllGoodsM() {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage("");//实际上这个函数没有输入

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 905);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    /**
     * 管理员进货操作，将指定的商品信息加入到商品数据库中。906
     *
     * @param g 进货的商品信息,这里的g不是一般定义的Good，是从进货页面捕获的Good信息，这个g的库存数在这里指进货数
     * @return 如果成功进货，返回true；否则返回false
     */
    public boolean ManagerAddGood(Good g) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            GoodMessage goodMessage = new GoodMessage(g);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(goodMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 906);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = null;
        try {
            boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        boolean result = boolRespMessage.getFlag();
        return result;
    }


    /**
     * 管理员退货操作，从商品数据库中减少指定商品的库存数量。907
     *
     * @param GoodID 商品ID
     * @param num    退货数量
     * @return 如果成功退货，返回true；否则返回false
     */
    public boolean ManagerReduceGood(String GoodID, int num) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            StringIntMessage stringIntMessage = new StringIntMessage(GoodID, num);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(stringIntMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 907);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = null;
        try {
            boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        boolean result = boolRespMessage.getFlag();
        return result;
    }


    /**
     * 查找并返回数据库所有的购买记录信息 908
     *
     * @return PurchaseRecord类数组allRecords，代表数据库中所有的购买记录，根据购买时间升序排序
     */
    public String[][] getAllPurchaseRecord() {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage("");//实际上这个函数没有输入

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 908);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 查找并返回数据库中一卡通号为uId的所有购买记录信息 909
     *
     * @param uId 查询购买记录的用户的一卡通号
     * @return PurchaseRecord类数组allRecords，代表数据库中一卡通号为uId的所有购买记录，根据购买时间升序排序
     */
    public String[][] getPurchaseRecordById(String uId) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage(uId);//实际上这个函数没有输入

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 909);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    /**
     * 在后端的购物车数组中删除商品 911
     */
    public boolean removeSelectedGood(String goodName) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage(goodName);//实际上这个函数没有输入

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 911);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = null;
        try {
            boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        boolean result = boolRespMessage.getFlag();
        return result;

    }

    /**
     * 新增购买记录信息 912
     *
     * @param purchaseRecord 需要新增的购买记录的信息
     * @return 新增是否成功，如果数据库中原本就存在该购买记录，则不进行新增插入操作，返回false
     */
    public boolean addPurchaseRecord(PurchaseRecord purchaseRecord) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            PurchaseRecordMessage purchaseRecordMessage = new PurchaseRecordMessage(purchaseRecord);//实际上这个函数没有输入

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(purchaseRecordMessage);
            System.out.println(jsonData);

            //发给服务端
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 912);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = null;
        try {
            boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        boolean result = boolRespMessage.getFlag();
        return result;
    }


}


