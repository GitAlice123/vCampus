package view.connect;


public interface NoticeClientAPI {

    /**
     * 读取数据库中的公告内容
     * @return 字符串类型的公告内容
     */
    String getNotice();

    /**
     * 将公告内容修改为传入的字符串
     * @param text 公告内容
     * @return 是否成功修改
     */
    boolean editNotice(String text);

}
