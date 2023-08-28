package view.Login;

public class User {
    private String uId;
    private String uPwd;
    private String uRole;


    public String getuId() {
        return uId;
    }
    public void setuId(String uId) {
        this.uId = uId;
    }
    public String getuPwd() {
        return uPwd;
    }
    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getuRole() {
        return uRole;
    }
    public void setuRole(String uRole) {
        this.uRole = uRole;
    }
    @Override
    public String toString() {
        return "User [uId=" + uId + ", uPwd=" + uPwd + ", uRole=" + uRole + "]";
    }

}