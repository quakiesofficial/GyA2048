package Spelet2048;

public abstract class AlgorithmAbstarct {

    protected int[] copyArray(int[] array){
        int[] tempArray = new int[array.length];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        return tempArray;
    }
    protected int findInArray(int[] array, int number){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==number){return i;}
        }
        return -1;
    }
    protected boolean contains(int[] arr, int element){
        for (int j : arr) {
            if (j == element) {
                return true;

            }
        }
        return false;
    }
}
