package _18_types_parameterization;

public class JavaQueue {

    static class Queue<T> {

    }

    public static void main(String[] args) {
        Queue<String> q = new Queue();
        System.out.println(q);
    }
}
