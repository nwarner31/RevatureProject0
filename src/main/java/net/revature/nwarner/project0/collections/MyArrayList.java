package net.revature.nwarner.project0.collections;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MyArrayList<T> {
    private T[] collection;
    private int size;

    public MyArrayList() {
        collection = (T[]) new Object[10];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * Adds an item to the end of the ArrayList.
     * @param t The object to the added to the ArrayList.
     */
    public void addItem(T t) {
        if(isArrayFull()) {
            increaseArraySize();
        }
        collection[size] = t;
        size++;
    }

    /**
     * Inserts an object in the ArrayList at the position provided.
     * @param t The object to be inserted
     * @param index The index where the object will be inserted
     * @return Boolean whether the insert was successful or not.
     */
    public Boolean insertItem(T t, int index) {
        if(isArrayFull()) {
            increaseArraySize();
        }
        if(isIndexValid(index)) {
            for(int i = size - 1; i >= index; i--) {
                collection[i + 1] = collection[i];
            }
            collection[index] = t;
            size++;
            return true;
        } else {
            return false;
        }

    }


    /**
     * Returns the object at the index
     * @param index The index of the object to be returned
     * @return The object at the index or null if the index is invalid.
     */
    public T getItem(int index) {
        if(isIndexValid(index)) {
            return collection[index];
        }
        return null;
    }

    public Boolean updateItem(T t, int index) {
        if(isIndexValid(index)) {
            collection[index] = t;
            return true;
        } else {
            return false;
        }
    }

    public int findItem(T t) {
        for(int x = 0; x < size; x++) {
            if (collection[x].equals(t)) {
                return x;
            }
        }
        return -1;
    }

    public int[] findAllInstances(T t) {
        String indexString = "";
        for(int x = 0; x < size; x++) {
            if (collection[x].equals(t)) {
                indexString += String.format("%s,", x);
            }
        }
        if(indexString != "") {
            String[] stringArray = indexString.split(",");
            int[] indexes = new int[stringArray.length];
            for(int x = 0; x < stringArray.length; x++) {
                indexes[x] = Integer.parseInt(stringArray[x]);
            }
            return indexes;
        } else {
            return null;
        }

    }

    public Boolean isSubsetOf(MyArrayList mal) {
        if (size > mal.getSize()) return false;
        Object[] tempArray = new Object[mal.getSize()];
        System.arraycopy(mal.collection, 0, tempArray, 0, tempArray.length);
        for(int x = 0; x < size; x++) {
            Boolean foundCurrentItem = false;
            for(int y = 0; y < tempArray.length; y++) {
                if (collection[x].equals(tempArray[y])) {
                    foundCurrentItem = true;
                    tempArray[y] = null;
                    break;
                }
            }
            if (!foundCurrentItem) return false;
        }
        return true;
    }

    public Boolean isEqualTo(MyArrayList mal) {
        if (size != mal.getSize()) return false;
        return isSubsetOf(mal);
    }

    /**
     * Removes the object at the given index
     * @param index The index of the object to remove.
     * @return Boolean whether the remove was successful or not.
     */
    public Boolean removeItem(int index) {
        if(isIndexValid(index)) {
            for(int i = index; i < size; i++) {
                collection[i] = collection[i + 1];
            }
            size--;
            return true;
        } else {
            return false;
        }
    }


    private Boolean isArrayFull() {
        return size == collection.length;
    }

    /**
     * Tests to see if the index provided is within the range of the array's values.
     * @param index The index being tested.
     * @return True if the value is in the range of the array. False if not.
     */
    private Boolean isIndexValid(int index) {
        return index > -1 && index < size;
    }

    /**
     * Increases the size of the array by 10 if the array is full.
     */
    private void increaseArraySize() {
        T[] tempArray = (T[]) new Object[size + 10];
        for(int index = 0; index < size; index++) {
            tempArray[index] = collection[index];
        }
        collection = tempArray;
    }

    private interface GenericsOperator <T> {
        void op(T t);
    }
}
