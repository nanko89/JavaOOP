package factories;

import implementation.MessageLogger;
import interfaces.Appender;
import interfaces.Factory;
import interfaces.Logger;

public class LoggerFactory implements Factory<Logger> {
    AppenderFactory appenderFactory;

    public LoggerFactory() {
        this.appenderFactory = new AppenderFactory();
    }

    @Override
    public Logger produce(String input) {
        String[] token = input.split(System.lineSeparator());
        Appender[] appenders = new Appender[token.length];

        for (int i = 0; i < token.length; i++) {
            appenders[i] = this.appenderFactory.produce(token[i]);
        }
        return new MessageLogger(appenders);
    }
}
