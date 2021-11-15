package implementation.layout;

import enums.ReportLevel;
import interfaces.Layout;


public class SimpleLayout implements Layout {
    @Override
    public String format(String date, String message, ReportLevel reportLevel) {
        return String.format("%s - %s - %s", date,reportLevel,message);
    }
}
