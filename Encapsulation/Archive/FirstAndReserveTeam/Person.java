package Archive.FirstAndReserveTeam;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private Integer age;
    private Double salary;

    public Person(String firstName, String lastName, Integer age, Double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setSalary(Double salary) {
        if (salary >= 460) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
    }

    public void increaseSalary(double bonus) {
        double currentSalary;
        if (getAge() < 30) {

            currentSalary = salary + (salary * bonus / 200);
        } else {
            currentSalary = salary + (salary * bonus / 100);
        }
        setSalary(currentSalary);
    }

    public Double getSalary() {
        return salary;
    }

    public void setAge(Integer age) {
        if (age > 0) {
            this.age = age;
        }else {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
    }

    public void setFirstName(String firstName) {
        if (firstName.length() >= 3) {
            this.firstName = firstName;
        }else {
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
    }

    public void setLastName(String lastName) {
        if (lastName.length() >= 3) {
            this.lastName = lastName;
        }else {
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.0####");
        return String.format("%s %s gets %s leva", firstName, lastName, formatter.format(salary));
    }

}
