package galaxy;

public class Field {
    public int[][] matrix;

    public Field(int row, int col) {
        this.matrix = new int[row][col];
        fillMatrix();
    }

    private void fillMatrix(){
        int value = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = value++;
            }
        }
    }

    public void setValue(int row, int col, int value){
         this.matrix[row][col] = value;
    }

    public int getValue(int row, int col){
        return this.matrix[row][col];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public boolean isInRange(Field matrix, int row, int col) {
        return row >= 0 && row < matrix.getMatrix().length
                && col >= 0 && col < matrix.getMatrix()[0].length;
    }
}
