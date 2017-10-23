package blue.datastructure.queue;

import java.util.LinkedList;

public interface Queue<T> {
    void offer(T data);
    T poll();
    T peek();
    int size();
}
