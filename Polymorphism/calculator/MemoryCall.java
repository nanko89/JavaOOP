package calculator;

import java.util.Deque;

public class MemoryCall implements Operation {

    private Deque<Integer> memory;

    public MemoryCall(Deque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {

    }

    @Override
    public int getResult() {
        return this.memory.pop();
    }

    @Override
    public boolean isCompleted() {
        return this.memory.size() != 0;
    }
}
