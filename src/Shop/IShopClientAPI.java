package view.Shop;

/**
 * 客户端接口：IShopClientSrv
 * 提供与商店相关的方法定义
 */
public interface IShopClientAPI {

    /**
     * 根据商品ID或商品名称查询商品信息，供学生使用。
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    String[][] findGoodST(String query);

    /**
     * 根据商品ID或商品名称查询商品信息，供管理员使用。
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    String[][] findGoodM(String query);


    /**
     * 根据商品ID或商品名称查询商品信息，并返回包含这个商品所有属性数据的String[][]
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    String[][] findGoodAllInfo(String query);


    /**
     * 将指定名称的商品按照用户输入的数量加入购物车。
     *
     * @param goodName 商品名称
     * @param num      商品数量
     * @return 如果成功加入购物车，返回true；否则返回false
     */
    boolean addSelectedGood(String goodName, int num);


    /**
     * 返回购物车中的商品列表，以String[][]形式表示。
     *
     * @return 购物车中商品的String[][]形式
     */
    Object[][] getSelectedGoods();


    /**
     * 返回商店中所有商品的列表，以String[][]形式表示。学生用
     *
     * @return 商店中所有商品的String[][]形式
     */
    String[][] getAllGoodsST();


    /**
     * 返回商店中所有商品的列表，以String[][]形式表示。管理员用
     *
     * @return 商店中所有商品的String[][]形式
     */
    String[][] getAllGoodsM();


    /**
     * 管理员进货操作，将指定的商品信息加入到商品数据库中。
     *
     * @param g 进货的商品信息,这里的g不是一般定义的Good，是从进货页面捕获的Good信息，这个g的库存数在这里指进货数
     * @return 如果成功进货，返回true；否则返回false
     */
    boolean ManagerAddGood(Good g);


    /**
     * 管理员退货操作，从商品数据库中减少指定商品的库存数量。
     *
     * @param GoodID 商品ID
     * @param num    退货数量
     * @return 如果成功退货，返回true；否则返回false
     */
    boolean ManagerReduceGood(String GoodID, int num);


    /**
     * 查找并返回数据库所有的购买记录信息
     *
     * @return PurchaseRecord类数组allRecords，代表数据库中所有的购买记录，根据购买时间升序排序
     */
    String[][] getAllPurchaseRecord();

    /**
     * 查找并返回数据库中一卡通号为uId的所有购买记录信息
     *
     * @param uId 查询购买记录的用户的一卡通号
     * @return PurchaseRecord类数组allRecords，代表数据库中一卡通号为uId的所有购买记录，根据购买时间升序排序
     */
    String[][] getPurchaseRecordById(String uId);

    /**
     * 在后端的购物车数组中删除商品
     */
    boolean removeSelectedGood(String goodName);

    /**
     * 新增购买记录信息
     *
     * @param purchaseRecord 需要新增的购买记录的信息
     * @return 新增是否成功，如果数据库中原本就存在该购买记录，则不进行新增插入操作，返回false
     */
    boolean addPurchaseRecord(PurchaseRecord purchaseRecord);

}