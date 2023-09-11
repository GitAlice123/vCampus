package view.SchoolRolls;

public class Grade {
    private String CardID;
    private String courseID;
    private double Grade;

    public Grade(String cardID, String courseID, double grade) {
        CardID = cardID;
        this.courseID = courseID;
        this.Grade = grade;
    }

    public Grade() {
    }

    public String getCardID() {
        return CardID;
    }

    public void setCardID(String cardID) {
        CardID = cardID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public double getGrade() {
        return Grade;
    }

    public void setGrade(double grade) {
        this.Grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "CardID='" + CardID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", Grade=" + Grade +
                '}';
    }
}
