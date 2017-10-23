package blue.datastructure.list.linked;

import blue.datastructure.list.List;
import blue.datastructure.queue.Queue;

public class LinkedList<T> implements List<T>, Queue<T> {

    private Node<T> first;

    public LinkedList() {
        first = new Node<>();
    }

    @Override
    public int size() {
        Node<T> node = first.next;
        int size = 0;
        while (null != node) {
            node = node.next;
            size++;
        }
        return size;
    }

    @Override
    public void add(T data) {
        Node<T> node = first;
        while (null != node.next) {
            node = node.next;
        }
        node.next = new Node<>();
        node.next.data = data;
    }

    @Override
    public void add(int index, T data) {
        Node<T> node = first;
        int count = 0;
        // 假设在1位置插入一个元素，那么需要遍历到1位置之前，也就是0位置；
        // 因为要获得0位置元素以便于获取0的尾引用，并进行链表的拼接；
        while (null != node && count < index) {
            node = node.next;
            count++;
        }
        if (null == node) {
            throw new RuntimeException("Overflow");
        } else {
            Node<T> newNode = new Node<>();
            newNode.data = data;
            newNode.next = node.next;
            node.next = newNode;
        }
    }

    @Override
    public T remove(int index) {
        Node<T> node = first;
        int count = 0;
        while (null != node && count < index) {
            node = node.next;
            count++;
        }
        if (null == node || null == node.next) {
            throw new RuntimeException("Overflow");
        } else {
            Node<T> targetNode = node.next;
            node.next = targetNode.next;
            return targetNode.data;
        }
    }

    @Override
    public int remove(T data) {
        Node<T> prevNode = first;
        Node<T> targetNode = first.next;
        int count = 0;
        while (null != targetNode && !data.equals(targetNode.data)) {
            prevNode = targetNode;
            targetNode = targetNode.next;
            count++;
        }
        if (null == targetNode) {
            return -1;
        } else {
            prevNode.next = targetNode.next;
            return count;
        }
    }

    @Override
    public String toString() {
        Node<T> node = first.next;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        while (null != node.next) {
            stringBuffer.append(node.data);
            stringBuffer.append(", ");
            node = node.next;
        }
        stringBuffer.append(node.data);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(2, 11);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        System.out.println(list.remove(new Integer(3)));
    }

    @Override
    public void offer(T data) {
        add(data);
    }

    @Override
    public T poll() {
        if (null != first.next) {
            Node<T> node = first.next;
            first.next = node.next;
            return node.data;
        }
        return null;
    }

    @Override
    public T peek() {
        if (null != first.next) {
            return first.next.data;
        }
        return null;
    }
}
