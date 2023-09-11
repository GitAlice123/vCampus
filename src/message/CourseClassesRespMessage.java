package view.message;

import view.CourseSelection.CourseClass;

public class CourseClassesRespMessage {
    private CourseClass[] courseClasses;

    public CourseClassesRespMessage(CourseClass[] courseClasses) {
        this.courseClasses = courseClasses;
    }

    public CourseClassesRespMessage() {
    }

    public CourseClass[] getCourseClasses() {
        return courseClasses;
    }
}
