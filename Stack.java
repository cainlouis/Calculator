package Calculator;
public class Stack {

    private String[] arr;
    private int size;
    private int len;

    public Stack() {
        this.arr = new String[10];
        this.size = 0;
        this.len = 10;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(String val) {
        if (size == len) {
            String[] newarr = new String[len * 2];
            for (int i = 0; i < len; i++) {
                newarr[i] = arr[i];
            }
            arr = newarr;
            len *= 2;
        }
        arr[size++] = val;
    }
    
    public String top() {
        if (size == 0) {
            throw new RuntimeException("Empty Stack");
        }
        return arr[size - 1];
    }

    public String pop() {
        if (size == 0) {
            throw new RuntimeException("Empty Stack");
        }
        String val = arr[--size];
        arr[size] = "";
        return val;
    }

}
