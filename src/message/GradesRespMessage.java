package view.message;

import view.SchoolRolls.Grade;

public class GradesRespMessage {
    private Grade[] grades;
    private Grade grade;

    public GradesRespMessage(Grade[] grades) {
        this.grades = grades;
    }

    public GradesRespMessage() {
    }

    public GradesRespMessage(Grade grade) {
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public Grade[] getGrades() {
        return grades;
    }
}
