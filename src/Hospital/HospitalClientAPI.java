package view.Hospital;

import java.io.IOException;

public interface HospitalClientAPI {
    /**
     * 获取所有医院科室信息的方法。 500
     *
     * @return 包含所有科室信息的 Department 数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    Department[] GetAllDepartments() throws IOException;

    /**
     * 根据用户ID返回所有挂号记录的方法。 501
     *
     * @param ID 用户的唯一标识符
     * @return 包含所有挂号记录的 Register 数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    Register[] GetRegisterByID(String ID) throws IOException;

    /**
     * 根据用户ID返回所有未支付挂号记录的方法。502
     *
     * @param ID 用户的唯一标识符
     * @return 包含所有未支付挂号记录的 Register 数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    Register[] GetPaymentByID(String ID) throws IOException;

    /**
     * 根据挂号信息创建挂号记录的方法。503
     *
     * @param register 包含挂号信息的 Register 对象
     * @return 创建挂号是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    boolean CreaatPaymentByInfo(Register register) throws IOException;

    /**
     * 支付指定挂号订单的方法。504
     *
     * @param rID 挂号记录的唯一标识符
     * @return 支付是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    boolean PayAllPayment(String rID) throws IOException;

    /**
     * 根据科室名称和医生类型寻找挂号的方法。505
     *
     * @param type  医生类型
     * @param level 科室级别
     * @return 匹配条件的科室数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    Department[] GetDepartmentByinfo(String type, String level) throws IOException;

    /**
     * 根据科室ID查找科室的方法。506
     *
     * @param ID 科室的唯一标识符
     * @return 匹配的科室对象
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    Department GetDepartmentByID(String ID) throws IOException;

    /**
     * 返回所有挂号信息的方法。507
     *
     * @return 包含所有挂号信息的数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    Register[] GetAllRegisters() throws IOException;

    /**
     * 根据信息新建科室的方法。508
     *
     * @param department 要添加的科室信息
     * @return 新建科室是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    boolean AddDepartmentByInfo(Department department) throws IOException;

    /**
     * 根据科室编号删除科室的方法。509
     *
     * @param ID 要删除的科室的编号
     * @return 删除科室是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    boolean DeleteDepartmentByID(String ID) throws IOException;
}
