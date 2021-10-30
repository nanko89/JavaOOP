package jediGalaxy;

public class Galaxy {
    private Field field;

    public Galaxy(Field field) {
        this.field = field;
    }

    public void moveEvil(int row, int col){
        while (row >= 0 && col >= 0) {

            if (this.field.isInBound(row,col)) {
              this.field.setValue(row,col,0);
            }
            row--;
            col--;
        }
    }

    public long moveJedi(int row , int col){
        long startsCollect = 0;

        while (row >= 0 && col < field.getCollLength(1)) {

            if (field.isInBound(row,col)) {
                startsCollect += this.field.getValue(row, col);
            }

            col++;
            row--;
        }
        return startsCollect;
    }
}
