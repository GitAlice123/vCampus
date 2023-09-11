package view.Hospital;

import java.io.IOException;

public interface HospitalClientAPI {
    //500 查找所有科室(预约挂号)
    Department[] GetAllDepartments() throws IOException;

    //501  根据用户ID返回所有挂号记录(挂号记录)
    Register[] GetRegisterByID(String ID) throws IOException;

    //502  根据用户ID返回所有未支付挂号记录(缴费)
    Register[] GetPaymentByID(String ID) throws IOException;

    //503 通过ID和departmentid创建挂号(挂号)
    boolean CreaatPaymentByInfo(Register register) throws IOException;

    //504 支付所选订单(支付)
    boolean PayAllPayment(String rID) throws IOException;

    //505 按科室/专家查找挂号(查询)
    Department[] GetDepartmentByinfo(String type, String level) throws IOException;

    //*******************************管理员界面
    //506 按科室ID返回科室
    Department GetDepartmentByID(String ID) throws IOException;

    //507 返回所有用户的挂号信息
    Register[] GetAllRegisters() throws IOException;

    //508 根据信息添加科室
    boolean AddDepartmentByInfo(Department department) throws IOException;

    //509 根据科室ID删除科室
    boolean DeleteDepartmentByID(String ID) throws IOException;
}
