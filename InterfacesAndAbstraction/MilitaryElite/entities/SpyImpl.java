package MilitaryElite.entities;

import MilitaryElite.interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    protected String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
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
    public String getCodeNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()+ String.format("Code Number: %s", this.codeNumber);
    }
}
