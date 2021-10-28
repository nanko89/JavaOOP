package StudentSystem;

import java.util.HashMap;
import java.util.Map;


public class StudentSystem {
    private Map<String, Student> students;

    public StudentSystem() {
        this.students = new HashMap<>();
    }

    public Map<String, Student> getStudents() {
        return this.students;
    }

    public String parseCommand(String[] args) {

        String command = args[0];
        String name = args[1];
        switch (command) {
            case "Create":
                int age = Integer.parseInt(args[2]);
                double grade = Double.parseDouble(args[3]);
                addAndRegisterStudent(name, age, grade);
                return null;
            case "Show":
                return getStudentInfo(name);

            default:
                throw new IllegalArgumentException("Unknown command " + command);
        }
    }


    private void addAndRegisterStudent(String name, int age, double grade) {
        this.students.putIfAbsent(name, new Student(name, age, grade));
    }

    private String getStudentInfo(String name) {
        Student student = this.students.get(name);
        if (student != null) {
        return StudentInfoFormatter.getFormatInfo(student);
        }
        return null;
    }
}
