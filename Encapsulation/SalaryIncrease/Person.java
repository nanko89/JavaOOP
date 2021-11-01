package SalaryIncrease;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void increaseSalary(double percentage) {
        double currentSalary;
        if (this.getAge() < 30) {
            currentSalary = this.getSalary() + (this.getSalary() * percentage / 200);
        } else {
            currentSalary = this.getSalary() + (this.getSalary() * percentage / 100);
        }
        this.setSalary(currentSalary);
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.0####");
        return String.format("%s %s gets %f leva", firstName, lastName, formatter.format(salary));
    }
}
