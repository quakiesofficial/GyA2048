package Spelet2048;

public abstract class AlgoritmAbstarct {

    protected int[] copyArray(int[] array){
        int[] tempArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tempArray[i]=array[i];
        }
        return tempArray;
    }
    protected int findInArray(int[] array, int number){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==number){return i;}
        }
        return -1;
    }
    protected boolean contains(int[] arr, int element){
        boolean contains=false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==element)contains=true;
        }
        return contains;
    }
}
