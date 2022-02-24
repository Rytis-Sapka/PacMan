package algorithms;

import PickUp.Item;

public class Array {
 
    // Member variables of this class
    // Private access modifier
    public Item arr[];
    public int count;
 
    // Note they can only be called through function
 
    // Method 1
    // Inside helper class
    // to compute length of an array
    public Array(int length) { arr = new Item[length]; }
 
    // Method 2
    // Inside Helper class
    public void insert(Item element)
    {
 
        if (arr.length == count) {
 
            // Creating a new array double the size
            // of array declared above
            Item newArr[] = new Item[2 * count];
 
            // Iterating over new array using for loop
            for (int i = 0; i < count; i++) {
                newArr[i] = arr[i];
            }
 
            // Assigning new array to original array
            // created above
            arr = newArr;
        }
 
        arr[count++] = element;
    }
    
    public void delete(int pos)
    {
 
        for (int i = pos; i < count; i++) {
        	arr[i] = arr[i + 1];
        }
        count--;
    }
}