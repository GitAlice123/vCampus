package view.message;

import view.Hospital.Department;

public class DepartmentsMessage {
    private Department department;
    private Department[] departments;

    public DepartmentsMessage() {
    }

    public DepartmentsMessage(Department[] departments) {
        this.departments = departments;
    }

    public DepartmentsMessage(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public Department[] getDepartments() {
        return departments;
    }
}
