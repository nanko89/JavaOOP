package interfaces;

import enums.ReportLevel;

public interface Appender {
    void append(String time, String message, ReportLevel reportLevel);
    void setReportLevel(ReportLevel error);

}
