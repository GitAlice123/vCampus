package view.message;
import view.CourseSelection.Course;
public class CoursesRespMessage {
    private Course[] courses;

    public CoursesRespMessage() {
    }

    public CoursesRespMessage(Course[] courses) {
        this.courses = courses;
    }

    public Course[] getCourses() {
        return courses;
    }
}
