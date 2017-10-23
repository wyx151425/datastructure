package blue.datastructure.list;

public interface List<T> {
    void add(T data);
    void add(int index, T data);
    T remove(int index);
    int remove(T data);
    int size();
}
