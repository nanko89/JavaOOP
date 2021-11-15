package factories;

import enums.ReportLevel;
import implementation.appenders.ConsoleAppender;
import implementation.appenders.FileAppender;
import interfaces.Appender;
import interfaces.Factory;
import interfaces.Layout;

import java.util.Locale;

public class AppenderFactory implements Factory<Appender> {
    private LayoutFactory layoutFactory;

    public AppenderFactory() {
        this.layoutFactory = new LayoutFactory();
    }

    @Override
    public Appender produce(String input) {
        String[] token = input.split("\\s+");
        String appenderType = token[0];
        String layerType = token[1];

        Layout layout = layoutFactory.produce(layerType);

        Appender appender = null;
        if (appenderType.equals("ConsoleAppender")){
            appender = new ConsoleAppender(layout);
        }else if (appenderType.equals("FileAppender")){
            appender = new FileAppender(layout);
        }

        if (token.length >= 3){
            appender.setReportLevel(ReportLevel.valueOf(token[2].toUpperCase()));
        }
        return appender;
    }
}
