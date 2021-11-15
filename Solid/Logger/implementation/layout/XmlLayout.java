package implementation.layout;

import enums.ReportLevel;
import interfaces.Layout;

public class XmlLayout implements Layout {
    @Override
    public String format(String date, String message, ReportLevel reportLevel) {
        return String.format("<log>%n" +
                "<date>%s</date>%n" +
                "<level>%s</level>%n" +
                "<message>%s</message>%n" +
                "</log>%n", date, reportLevel, message);
    }
}
