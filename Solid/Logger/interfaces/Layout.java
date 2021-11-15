package interfaces;

import enums.ReportLevel;

public interface Layout{
    String format(String date, String message, ReportLevel reportLevel);

}
