package Smartphone;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {
    private final List<String> numbers;
    private final List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
            Pattern pattern = Pattern.compile("^[^0-9]+$");
        for (String url : urls) {
                if (this.isValid(url, pattern)) {
                    sb.append(String.format("Browsing: %s!", url));
                } else {
                    sb.append("Invalid URL!");
                }
                sb.append(System.lineSeparator());
            }
            return sb.toString().trim();
    }

    @Override
    public String call() {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        StringBuilder sb = new StringBuilder();
        for (String number : this.numbers) {
            if (this.isValid(number,pattern)){
                sb.append(String.format("Calling... %s", number));
            }else {
                sb.append("Invalid number!");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private boolean isValid(String number, Pattern pattern){
        Matcher matcher = pattern.matcher(number);
        return matcher.find();

    }
}
