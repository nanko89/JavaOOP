package MilitaryElite.entities;

import MilitaryElite.interfaces.Private;

public class PrivateImpl extends SoldierImpl implements Private {
    protected double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public int getId() {
        return super.id;
    }

    @Override
    public String getFirstName() {
        return super.firstName;
    }

    @Override
    public String getLastName() {
        return super.lastName;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Salary: %.2f", this.salary);
    }
}




