
import enums.ReportLevel;
import factories.LoggerFactory;
import implementation.layout.SimpleLayout;
import implementation.layout.XmlLayout;
import implementation.appenders.ConsoleAppender;
import implementation.MessageLogger;
import interfaces.Appender;
import interfaces.Layout;
import interfaces.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        InputParser inputParser = new InputParser();

        Scanner scanner = new Scanner(System.in);

        LoggerFactory loggerFactory = new LoggerFactory();

        Logger logger = loggerFactory.produce(inputParser.readLoggerInfo(scanner));

        String input = scanner.nextLine();

        while (!input.equals("END")){
            String[] tokens = input.split("\\|");
            ReportLevel reportLevel = ReportLevel.valueOf(tokens[0]);
            String time = tokens[1];
            String message = tokens[2];

            switch (reportLevel){
                case INFO -> logger.logInfo(time,message);
                case ERROR -> logger.logError(time,message);
                case FATAL -> logger.logFatal(time,message);
                case WARNING -> logger.logWarning(time,message);
                case CRITICAL -> logger.logCritical(time,message);
            }

            input = scanner.nextLine();

        }
        
System.out.println(logger);
    }
}
