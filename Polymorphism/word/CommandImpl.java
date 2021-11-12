package word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandImpl implements CommandInterface {
    static class ToUpperTransform implements TextTransform {
        @Override
        public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
            for (int i = startIndex; i < endIndex; i++) {
                text.setCharAt(i, Character.toUpperCase(text.charAt(i)));
            }
        }
    }

    class Cut implements TextTransform {
        private StringBuilder lastCut;

        @Override
        public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
            this.lastCut = new StringBuilder();
            lastCut.append(text, startIndex, endIndex);
            text.delete(startIndex, endIndex);
        }

        public StringBuilder getLastCut() {
            return lastCut;
        }
    }

    class Paste implements TextTransform {
        private Cut cut;

        public Paste(Cut cut) {
            this.cut = cut;
        }

        @Override
        public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        text.replace(startIndex,endIndex,cut.getLastCut().toString());
        }

    }
    private Map<String, TextTransform> commandTransforms;
    private StringBuilder text;

    public CommandImpl(StringBuilder text) {
        this.commandTransforms = new HashMap<>();
        this.text = text;
    }

    @Override
    public void init() {
        this.commandTransforms.clear();
        for (Command p : this.initCommands()) {
            this.commandTransforms.putIfAbsent(p.getText(), p.getTextTransform());
        }
    }

    @Override
    public void handleInput(String input) {
        String[] tokens = input.split("\\s+");

        String commandName = tokens[0];
        int startInd = Integer.parseInt(tokens[1]);
        int endInd = Integer.parseInt(tokens[2]);

        this.commandTransforms.get(commandName).invokeOn(this.text, startInd, endInd);
    }

    protected List<Command> initCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new Command("uppercase", new ToUpperTransform()));
        Cut cut = new Cut();
        commands.add(new Command("cut", cut));
        commands.add(new Command("paste", new Paste(cut)));

        return commands;
    }
}
