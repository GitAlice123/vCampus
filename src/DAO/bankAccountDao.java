package view.DAO;

import view.Bank.bankAccount;

import java.sql.*;

public class bankAccountDao {


    // 是否挂失，true表示正常，false表示挂失
    public boolean isLoss(String id){
        String sqlString = "select * from tblBankAccount where account_id = '" + id + "'";
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            res.next();
            if(res.getBoolean(6)){
                con.close();//关闭数据库连接
                return true; //账户正常
            }else{
                con.close();//关闭数据库连接
                return false;//账户挂失
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    /*
        通过一卡通号查询银行账户信息
        传入参数为一卡通号uId
        返回值为该uId对应的一个 bankAccount类的银行账户数据

        突然发现一个问题，好像写了这个就没必要写什么查看挂失状态、判断密码的方法了
        直接通过id查询bankAccount类的返回对象查它的属性就行了 T T
     */
    public bankAccount findBankAccountById(String id){

        String sqlString = "select * from tblBankAccount where account_id = '" + id + "'";
        bankAccount bankaccount = new bankAccount("" ,"","","",0,true);

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            if(!res.next()){
                return null;
            }//如果在数据库找不到该银行账户信息，则返回null
            res.beforeFirst();

            res.next();

            bankaccount.setCardId(res.getString(1));
            bankaccount.setName(res.getString(2));
            bankaccount.setId(res.getString(3));
            bankaccount.setPaymentPwd(res.getString(4));
            bankaccount.setBalance(res.getDouble(5));
            bankaccount.setLoss(res.getBoolean(6));

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankaccount;
    }

    /*
        通过传入的一卡通号id和用户输入的支付密码Pwd，从数据库中查找相应用户并判断密码是否正确
    */
    public boolean checkPwd(String id, String Pwd){

        String sqlString = "select * from tblBankAccount where account_id = '" + id + "'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            if(!res.next()){
                return false;
            } //如果该账户不存在，则返回false
            res.beforeFirst();

            res.next();
            if(!Pwd.equals(res.getString(4))){
                return false; //如果密码错误，则返回false
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*
        通过传入的一卡通号id和用户输入的原支付密码oldPwd、新密码newPwd，
        从数据库中查找相应用户判断密码是否正确，并更改密码
    */
    public boolean changePwd(String id, String oldPwd, String newPwd){


        String sqlString1 = "select * from tblBankAccount where account_id = '" + id + "'";
        //验证原密码是否正确
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){
                return false;
            } //如果该账户不存在，则返回false
            res.beforeFirst();

            res.next();
            if(!oldPwd.equals(res.getString(4))){
                return false; //如果原密码错误，则返回false
            }
            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlString2 = "update tblBankAccount set account_Pwd = '"+ newPwd + "' where account_id = '" + id + "'";
        //更改密码
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString2);
            if(count == 0) return false; //若未进行更改操作，则返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /*
        通过传入的一卡通号id,从数据库中查找相应用户并更改其挂失状态
        原本挂失则变为正常，原本正常则变为挂失
    */
    public boolean changeLoss(String id){
        String sqlString1 = "select * from tblBankAccount where account_id = '" + id + "'";
        //获取原挂失状态
        boolean isloss =  true;

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){
                return false;
            } //如果该账户不存在，则返回false
            res.beforeFirst();

            res.next();
            isloss=!res.getBoolean(6);
            //获取原挂失状态并将其取反

            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlString2 = "update tblBankAccount set account_isLoss = '"+ isloss + "' where account_id = '" + id + "'";
        //更改挂失状态
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString2);
            if(count == 0) return false; //若未进行更改操作，则返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /*
        通过一卡通号查询银行账户，并进行充值操作
        传入参数为一卡通号id，和充值金额money
    */
    public boolean recharge(String id, double money){
        String sqlString1 = "select * from tblBankAccount where account_id = '" + id + "'";
        //查询原有余额
        double original_balance= 0;

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){
                return false;
            } //如果该账户不存在，则返回false
            res.beforeFirst();

            res.next();
            original_balance=res.getDouble(5); //查询账户原本的余额

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        double new_balance=original_balance+money;
        String sqlString2 = "update tblBankAccount set account_balance = '"+ new_balance + "' where account_id = '" + id + "'";
        //进行账户充值，修改余额数值

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString2);
            if(count == 0) return false; //若未进行更改操作，则返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /*
        通过一卡通号查询银行账户，并进行消费操作
        传入参数为一卡通号id，和消费金额money
        若消费金额大于账户余额，则不进行消费操作，并返回false
    */
    public boolean bankConsume(String id, double money){
        String sqlString1 = "select * from tblBankAccount where account_id = '" + id + "'";
        //查询原有余额
        double original_balance= 0;

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){
                return false;
            } //如果该账户不存在，则返回false
            res.beforeFirst();

            res.next();
            original_balance=res.getDouble(5); //查询账户原本的余额

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        double new_balance=original_balance-money;
        if(new_balance<0)return false;  //若消费金额大于账户余额，则不进行消费操作，并返回false
        String sqlString2 = "update tblBankAccount set account_balance = '"+ new_balance + "' where account_id = '" + id + "'";
        //进行消费，修改账户余额值

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString2);
            if(count == 0) return false; //若未进行更改操作，则返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 查询并返回所有的银行账户信息。
     *
     * @return 包含所有银行账户的 bankAccount[] 数组，如果没有找到任何账户则返回 null。
     */
    public bankAccount[] findfindBankAccounts() {
        // SQL查询语句
        String sqlString = "select * from tblBankAccount";

        // 创建一个包含10个元素的 bankAccount[] 数组
        bankAccount[] allAccounts = new bankAccount[10];

        try {
            // 加载 Access 驱动
            Class.forName("com.hxtt.sql.access.AccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // 建立数据库连接
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\database\\vCampus.mdb", "", "");
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            // 定位到结果集的最后一行，获取记录总数
            res.last();
            int count = res.getRow();
            res.beforeFirst();

            // 如果记录数为0，返回 null
            if (count == 0) {
                return null;
            }

            // 根据记录数重新创建 bankAccount[] 数组
            allAccounts = new bankAccount[count];
            int index = 0;
            while (res.next()) {
                // 从结果集中获取账户信息并创建 bankAccount 对象
                allAccounts[index] = new bankAccount(res.getString(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getDouble(5), res.getBoolean(6));
                index++;
            }

            con.close(); // 关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAccounts;
    }

    public boolean addBankAccount(bankAccount bankaccount){
        String sqlString1 = "select * from tblBankAccount where account_id = '" + bankaccount.getId() + "'";
        //查找数据库中原本是否存在该用户的银行卡信息

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\db\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if (res.next()) {
                return false;
            }//如果在数据库原本就存在该用户的银行卡信息，则返回false，不进行新增插入操作
            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sqlString2 = "insert into tblBankAccount values('" + bankaccount.getCardId() + "','" + bankaccount.getName() + "','" + bankaccount.getId()
                + "','" + bankaccount.getPaymentPwd() + "'," + bankaccount.getBalance() + "," + bankaccount.isLoss() + ")";


        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sta.executeUpdate(sqlString2);

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
