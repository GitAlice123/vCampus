package view.Shop;

import view.Bank.bankAccount;


/**
 * 客户端接口：IShopClientSrv
 * 提供与商店相关的方法定义
 */
public interface IShopClientSrv {

    /**
     * 根据商品名称查询商品
     *
     * @param productName 商品名称
     * @return 查询到的商品对象
     */
    Good goodSearch(String productName);

    /**
     * 购买商品
     *
     * @param good         商品对象
     * @param user         用户对象,类型直接调用bank模块下的bankAccount类
     * @param quantity     购买数量
     * @return 购买是否成功的布尔值
     */
    boolean buy(Good good, bankAccount user, int quantity);

    /**
     * 查询购买记录
     *
     * @param user 用户对象
     * @return 购买记录数组
     */
    PurchaseRecord[] searchPurchaseRecord(bankAccount user);

    /**
     * 商品进货
     *
     * @param productId 商品ID
     * @param quantity  进货数量
     * @return 进货是否成功的布尔值
     */
    boolean addGood(String productId, int quantity);

    /**
     * 商品退货
     *
     * @param productId 商品ID
     * @param quantity  退货数量
     * @return 退货是否成功的布尔值
     */
    boolean deleteGood(String productId, int quantity);
}

