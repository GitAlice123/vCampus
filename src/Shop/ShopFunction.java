package view.Shop;

import view.DAO.GoodDao;
import view.DAO.PurchaseRecordDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ShopFunction {
    // 创建动态数组,购物车数组
    ArrayList<SelectedGood> selectedGoods = new ArrayList<>();
    private GoodDao GD = new GoodDao();
    private PurchaseRecordDao PD = new PurchaseRecordDao();

    public static Object[][] convertArrayListToObjectArray(ArrayList<SelectedGood> selectedGoods) {
        int size = selectedGoods.size();
        Object[][] result = new Object[size][4];

        for (int i = 0; i < size; i++) {
            SelectedGood selectedGood = selectedGoods.get(i);

            result[i][0] = selectedGood.getGoodName();  // 名称，String 类型
            result[i][1] = Integer.toString(selectedGood.getGoodNums());  // 数量，String 类型
            result[i][2] = Double.toString(selectedGood.getGoodPrice() * selectedGood.getGoodNums());  // 总金额，String 类型
            result[i][3] = false;  // 是否选中，boolean 类型
        }

        return result;
    }

    /**
     * 根据商品ID或商品名称查询商品信息，供学生使用。
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    public String[][] findGoodST(String query) {
        Good g1 = GD.findGoodByGoodId(query);
        Good g2 = GD.findGoodByProductName(query);
        Good[] G1 = new Good[1];
        G1[0] = g1;
        Good[] G2 = new Good[1];
        G2[0] = g2;
        if (g1 == null && g2 == null) {
            return null;
        } else if (g1 == null && g2 != null) {
            return convertGoodsToStringST(G2);
        } else if (g1 != null && g2 == null) {
            return convertGoodsToStringST(G1);
        }
        System.out.println("ID和商品名称查找重合");
        return null;
    }

    /**
     * 根据商品ID或商品名称查询商品信息，供管理员使用。
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    public String[][] findGoodM(String query) {
        Good g1 = GD.findGoodByGoodId(query);
        Good g2 = GD.findGoodByProductName(query);
        Good[] G1 = new Good[1];
        G1[0] = g1;
        Good[] G2 = new Good[1];
        G2[0] = g2;
        if (g1 == null && g2 == null) {
            return null;
        } else if (g1 == null && g2 != null) {
            return convertGoodsToStringM(G2);
        } else if (g1 != null && g2 == null) {
            return convertGoodsToStringM(G1);
        }
        System.out.println("ID和商品名称查找重合");
        return null;
    }

    /**
     * 根据商品ID或商品名称查询商品信息，并返回包含这个商品所有属性数据的String[][]
     *
     * @param query 查询关键字，可以是商品ID或商品名称。
     * @return 符合查询条件的商品信息数组。如果未找到匹配的商品，返回null。
     */
    public String[][] findGoodAllInfo(String query) {
        Good g1 = GD.findGoodByGoodId(query);
        Good g2 = GD.findGoodByProductName(query);
        Good[] G1 = new Good[1];
        G1[0] = g1;
        Good[] G2 = new Good[1];
        G2[0] = g2;
        if (g1 == null && g2 == null) {
            return null;
        } else if (g1 == null && g2 != null) {
            return convertGoodsToStringAllInfo(G2);
        } else if (g1 != null && g2 == null) {
            return convertGoodsToStringAllInfo(G1);
        }
        System.out.println("ID和商品名称查找重合");
        return null;
    }

    /**
     * 向购物车中增加商品
     */

    public boolean addSelectedGood(String goodName, int num) {
        Good g = GD.findGoodByProductName(goodName);

        System.out.println(g.getGoodId() + "********************1");
        for (SelectedGood selectedGood : selectedGoods) {
            System.out.println(selectedGood.getGoodId());
        }
        if (g == null) {
            System.out.println("要加入购物车的商品未找到");
            return false;
        } else {
            // 检查购物车中是否已存在相同商品
            for (SelectedGood selectedGood : selectedGoods) {
                if (selectedGood.getGoodId() == g.getGoodId()) {
                    System.out.println(g.getGoodId() + "********************");
                    // 商品已存在，增加对应数量
                    selectedGood.setGoodNums(selectedGood.getGoodNums() + num);
                    return true;
                }
            }

            // 购物车中不存在相同商品，创建新的 SelectedGood 元素并添加到购物车数组中
            SelectedGood selectedGood = new SelectedGood(g.getGoodId(), g.getGoodName(), g.getGoodPrice(), num);
            selectedGoods.add(selectedGood);
            return true;
        }
    }

    /**
     * 在后端的购物车数组中删除商品
     */
    public boolean removeSelectedGood(String goodName) {
        boolean found = false;

        for (SelectedGood selectedGood : selectedGoods) {
            if (selectedGood.getGoodName().equals(goodName)) {
                selectedGoods.remove(selectedGood);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("要删除的商品在购物车中未找到");
        }

        return found;
    }
//    public String[][] getSelectedGoods() {
//        if(selectedGoods.size()==0){
//            return null;
//        }else{
//            return convertArrayListToStringArray(selectedGoods);
//        }
//    }

    /**
     * 返回购物车中的商品列表，以String[][]形式表示。
     *
     * @return 购物车中商品的String[][]形式
     */
    public Object[][] getSelectedGoods() {
        if (selectedGoods.size() == 0) {
            return null;
        } else {
            return convertArrayListToObjectArray(selectedGoods);
        }
    }

    /**
     * 返回商店中所有商品的列表，以String[][]形式表示。学生用
     *
     * @return 商店中所有商品的String[][]形式
     */
    public String[][] getAllGoodsST() {
//        // 输出Good[]类型的数据
//        for (Good good : GD.findAllGoods()) {
//            System.out.println("商品序号: " + good.getGoodId());
//            System.out.println("商品名称: " + good.getGoodName());
//            System.out.println("商品价格: " + good.getGoodPrice());
//            System.out.println("商品类别: " + good.getCategory());
//            System.out.println("供应商: " + good.getProvider());
//            System.out.println("商品库存数: " + good.getGoodStock());
//            System.out.println();
//        }
        return convertGoodsToStringST(GD.findAllGoods());
    }

    /**
     * 返回商店中所有商品的列表，以String[][]形式表示。管理员用
     *
     * @return 商店中所有商品的String[][]形式
     */
    public String[][] getAllGoodsM() {
        return convertGoodsToStringM(GD.findAllGoods());
    }

    /**
     * 管理员进货操作，将指定的商品信息加入到商品数据库中。
     *
     * @param g 进货的商品信息,这里的g不是一般定义的Good，是从进货页面捕获的Good信息，这个g的库存数在这里指进货数
     * @return 如果成功进货，返回true；否则返回false
     */
    public boolean ManagerAddGood(Good g) {
        // 进货
        return GD.addGood(g);
    }

    /**
     * 管理员退货操作，从商品数据库中减少指定商品的库存数量。
     *
     * @param GoodID 商品ID
     * @param num    退货数量
     * @return 如果成功退货，返回true；否则返回false
     */
    public boolean ManagerReduceGood(String GoodID, int num) {
        // 通过商品ID查找要退货的商品
        Good g = GD.findGoodByGoodId(GoodID);

        // 退货
        return GD.reduceGood(GoodID, num);
    }

    /**
     * 查找并返回数据库所有的购买记录信息
     *
     * @return PurchaseRecord类数组allRecords，代表数据库中所有的购买记录，根据购买时间升序排序
     */
    public String[][] getAllPurchaseRecord() {
        return convertPurchaseRecordsToStringM(PD.findAllPurchaseRecord());
    }

    /**
     * 查找并返回数据库中一卡通号为uId的所有购买记录信息
     *
     * @param uId 查询购买记录的用户的一卡通号
     * @return PurchaseRecord类数组allRecords，代表数据库中一卡通号为uId的所有购买记录，根据购买时间升序排序
     */
    public String[][] getPurchaseRecordById(String uId) {
        return convertPurchaseRecordsToStringST(PD.findPurchaseRecordById(uId));
    }

    /**
     * 将Good对象转换为String[][]类型，以便在前端显示。学生用，因为学生界面显示需要五列
     *
     * @param goods 要转换的Good对象。
     * @return 表示Good对象的String数组。
     */
    public String[][] convertGoodsToStringST(Good[] goods) {
        if (goods != null) {

            String[][] GoodStrings = new String[goods.length][5];
            System.out.println(goods.length);

            for (int i = 0; i < goods.length; i++) {
                Good good = goods[i];

                GoodStrings[i][0] = good.getGoodId();      // 商品编号
                GoodStrings[i][1] = good.getGoodName();    // 商品名称
                GoodStrings[i][2] = Double.toString(good.getGoodPrice());   // 商品价格
                GoodStrings[i][3] = Integer.toString(good.getGoodStock());  // 商品库存
                GoodStrings[i][4] = "";

            }
            return GoodStrings;
        } else {
            System.out.println("无相应内容");
            return null;
        }
    }

    /**
     * 将Good对象转换为String[][]类型，以便在前端显示。管理员用，因为管理员界面显示需要四列
     *
     * @param goods 要转换的Good对象。
     * @return 表示Good对象的String数组。
     */
    public String[][] convertGoodsToStringM(Good[] goods) {
        if (goods != null) {

            String[][] GoodStrings = new String[goods.length][5];
            System.out.println(goods.length);

            for (int i = 0; i < goods.length; i++) {
                Good good = goods[i];

                GoodStrings[i][0] = good.getGoodId();      // 商品编号
                GoodStrings[i][1] = good.getGoodName();    // 商品名称
                GoodStrings[i][2] = Double.toString(good.getGoodPrice());   // 商品价格
                GoodStrings[i][3] = Integer.toString(good.getGoodStock());  // 商品库存

            }
            return GoodStrings;
        } else {
            System.out.println("无相应内容");
            return null;
        }
    }

    /**
     * 将Good对象转换为String[][]类型，以便在前端显示。管理员的进货界面用，因为进货界面需要商品类的所有属性数据
     *
     * @param goods 要转换的Good对象。
     * @return 表示Good对象的String数组。
     */
    public String[][] convertGoodsToStringAllInfo(Good[] goods) {
        if (goods != null) {

            String[][] GoodStrings = new String[goods.length][6];
            System.out.println(goods.length);

            for (int i = 0; i < goods.length; i++) {
                Good good = goods[i];

                GoodStrings[i][0] = good.getGoodId();      // 商品编号
                GoodStrings[i][1] = good.getGoodName();    // 商品名称
                GoodStrings[i][2] = Double.toString(good.getGoodPrice());   // 商品价格
                GoodStrings[i][3] = good.getCategory();//商品类别
                GoodStrings[i][4] = good.getProvider();//供应商
                GoodStrings[i][5] = Integer.toString(good.getGoodStock());  // 商品库存
//                Good类属性：
//                private String goodId;//商品序号
//                private String goodName;//商品名称
//                private double goodPrice;//商品价格
//                private String category;//商品类别
//                private String provider;//供应商
//                private int goodStock;//商品库存数

            }
            return GoodStrings;
        } else {
            System.out.println("无相应内容");
            return null;
        }
    }

//    /**
//     * 将ArrayList<SelectedGood>转换为String[][]类型。
//     *
//     * @param selectedGoods ArrayList<SelectedGood>对象
//     * @return 转换后的String[][]数组
//     */
//    public static String[][] convertArrayListToStringArray(ArrayList<SelectedGood> selectedGoods) {
//        int size = selectedGoods.size();
//        String[][] result = new String[size][4];
//
//        for (int i = 0; i < size; i++) {
//            SelectedGood selectedGood = selectedGoods.get(i);
//
//            result[i][0] = selectedGood.getGoodName();//名称
//            result[i][1] = Integer.toString(selectedGood.getGoodNums());//数量
//            result[i][2] = Double.toString(selectedGood.getGoodPrice()* selectedGood.getGoodNums());//总金额
//            result[i][3] = "";
//        }
//
//        return result;
//    }

    /**
     * 将PurchaseRecord对象转换为String[][]类型，以便在前端显示。学生用
     *
     * @param records 要转换的record对象。
     * @return 表示record对象的String数组。
     */
    public String[][] convertPurchaseRecordsToStringST(PurchaseRecord[] records) {
        if (records != null) {

            String[][] recordstrings = new String[records.length][4];
            System.out.println(records.length);

            for (int i = 0; i < records.length; i++) {
                PurchaseRecord record = records[i];

                Good g = GD.findGoodByGoodId(record.getGoodId());
                //System.out.println(record.getGoodId()+"---------------------");

                recordstrings[i][0] = g.getGoodName();   //商品名称
                recordstrings[i][1] = Integer.toString(record.getNums());   // 购买数量
                recordstrings[i][2] = Double.toString(record.getNums() * g.getGoodPrice());//支付金额
                // 将Date转换为String
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                recordstrings[i][3] = dateFormat.format(record.getPurchaseTime());//购买日期
                System.out.println("recordstrings[i][3]:" + recordstrings[i][3]);

            }
            return recordstrings;
        } else {
            System.out.println("无相应内容");
            return null;
        }
    }

    /**
     * 将PurchaseRecord对象转换为String[][]类型，以便在前端显示。管理员用，因为管理员界面显示需要四列
     *
     * @param records 要转换的record对象。
     * @return 表示record对象的String数组。
     */
    public String[][] convertPurchaseRecordsToStringM(PurchaseRecord[] records) {
        if (records != null) {

            String[][] recordstrings = new String[records.length][6];
            System.out.println(records.length);

            for (int i = 0; i < records.length; i++) {
                PurchaseRecord record = records[i];

                Good g = GD.findGoodByGoodId(record.getGoodId());

                recordstrings[i][0] = record.getOrderId();    //订单号
                recordstrings[i][1] = record.getUserId();  // 购买人ID
                recordstrings[i][2] = g.getGoodName();//商品名称
                recordstrings[i][3] = Integer.toString(record.getNums());   // 购买数量
                recordstrings[i][4] = Double.toString(record.getNums() * g.getGoodPrice());//支付金额
                System.out.println("原格式:" + record.getPurchaseTime());
                // 将Date转换为String
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                recordstrings[i][5] = dateFormat.format(record.getPurchaseTime());
                System.out.println("recordstrings[i][5]:" + recordstrings[i][5]);

            }
            return recordstrings;
        } else {
            System.out.println("无相应内容");
            return null;
        }
    }

    /**
     * 新增购买记录信息
     *
     * @param purchaseRecord 需要新增的购买记录的信息
     * @return 新增是否成功，如果数据库中原本就存在该购买记录，则不进行新增插入操作，返回false
     */
    public boolean addPurchaseRecord(PurchaseRecord purchaseRecord) {
        // 减少对应商品库存
        GD.reduceGood(purchaseRecord.getGoodId(), purchaseRecord.getNums());

        return PD.addPurchaseRecord(purchaseRecord);
    }

}
