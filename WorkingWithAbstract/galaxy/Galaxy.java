package galaxy;

public class Galaxy {
    private Field field;

    public Galaxy(Field field) {
        this.field = field;
    }

    public void moveEvil(int evilRow, int evilCol){
        while (evilRow >= 0 && evilCol >= 0) {
            if (this.field.isInRange(this.field, evilRow, evilCol)) {
                field.setValue(evilRow,evilCol,0);
            }
            evilRow--;
            evilCol--;
        }
    }

    public long movePlayer(int playerRow, int playerCol){
        long sum = 0;
        while (playerRow >= 0 && playerCol < this.field.getMatrix()[1].length) {
            if (this.field.isInRange(this.field, playerRow, playerCol)) {
                sum += this.field.getValue(playerRow,playerCol);
            }

            playerCol++;
            playerRow--;
        }
        return sum;
    }
}
