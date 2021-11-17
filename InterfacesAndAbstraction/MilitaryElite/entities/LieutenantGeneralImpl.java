package MilitaryElite.entities;

import MilitaryElite.interfaces.LieutenantGeneral;
import MilitaryElite.interfaces.Private;

import java.util.*;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {
    private Set<Private> privateSet;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary, Set<Private> privates) {
        super(id, firstName, lastName, salary);
        privateSet = privates;
    }

    @Override
    public void addPrivate(Private priv) {
        this.privateSet.add(priv);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator());
        sb.append("Privates: ").append(System.lineSeparator());

        for (Private aPrivate : privateSet) {
            sb.append("  ").append(aPrivate.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
