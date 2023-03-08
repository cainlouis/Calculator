package Calculator;
public class Stack {
    //the array of string for the tokens
    private String[] arr;
    //the number of elements in the stack
    private int size;
    //the capacity of the array
    private int len;

    //initialize the variable in constructor
    public Stack() {
        this.arr = new String[10];
        this.size = 0;
        this.len = 10;
    }

    // Check if size is empty 
    public boolean isEmpty() {
        return size == 0;
    }

    //get the size
    public int size() {
        return size;
    }

    //Create another bigger array if stack is full
    public void push(String val) {
        // if the number of element is equal to the length of the array
        if (size == len) {
            //Create a new array with double capacity
            String[] newarr = new String[len * 2];
            //Copy the array to the new one
            for (int i = 0; i < len; i++) {
                newarr[i] = arr[i];
            }
            // Override the old array
            arr = newarr;
            // Double the length 
            len *= 2;
        }
        // push the value to the array
        arr[size++] = val;
    }
    
    // Return the last element 
    public String top() {
        if (size == 0) {
            throw new RuntimeException("Empty Stack");
        }
        return arr[size - 1];
    }

    // Change the last element for an empty string
    public String pop() {
        if (size == 0) {
            throw new RuntimeException("Empty Stack");
        }
        String val = arr[--size];
        arr[size] = "";
        return val;
    }

}
