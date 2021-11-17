package MilitaryElite;

import MilitaryElite.entities.*;
import MilitaryElite.enums.Corps;
import MilitaryElite.enums.State;
import MilitaryElite.interfaces.Mission;
import MilitaryElite.interfaces.Private;
import MilitaryElite.interfaces.Repair;
import MilitaryElite.interfaces.Soldier;

import java.util.*;

public class Main {
    static Set<Private> privateSet = new TreeSet<>(new Comparator<Private>() {
        @Override
        public int compare(Private first, Private second) {
            return second.getId() - first.getId();
        }
    });

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String input = s.nextLine();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            Soldier soldier = createSoldier(tokens);
            if (soldier != null) {
                System.out.println(soldier);
            }
            input = s.nextLine();
        }
    }

    private static Soldier createSoldier(String[] tokens) {

        switch (tokens[0]) {
            case "Private":
                PrivateImpl aPrivate = new PrivateImpl(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
                        Double.parseDouble(tokens[4]));
                privateSet.add(aPrivate);
                return aPrivate;
            case "LieutenantGeneral":
                return new LieutenantGeneralImpl(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
                        Double.parseDouble(tokens[4]), privateSet);
            case "Engineer":
                if (checkCorps(tokens[5])) {
                    EngineerImpl engineer = new EngineerImpl(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
                            Double.parseDouble(tokens[4]), Corps.valueOf(tokens[5]));
                    for (int i = 6; i < tokens.length; i += 2) {
                        String partName = tokens[i];
                        int hoursWorked = Integer.parseInt(tokens[i + 1]);
                        Repair repair = new RepairImpl(partName, hoursWorked);
                        engineer.addRepair(repair);
                    }
                    return engineer;
                }
                break;

            case "Commando":
                if (checkCorps(tokens[5])) {
                    CommandoImpl commando = new CommandoImpl(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
                            Double.parseDouble(tokens[4]), Corps.valueOf(tokens[5]));
                    for (int i = 6; i < tokens.length; i += 2) {
                        String codeName = tokens[i];
                        String state = tokens[i + 1];

                        if (state.equals("inProgress") || state.equals("finished")) {
                            Mission mission = new MissionImpl(codeName, State.valueOf(state));
                            commando.addMission(mission);
                        }
                    }
                    return commando;
                }
                break;
            case "Spy":
                return new SpyImpl(Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4]);
        }
        return null;
    }

    private static boolean checkCorps(String token) {
        return Corps.Airforces.toString().equals(token) || Corps.Marines.name().equals(token);
    }
}
