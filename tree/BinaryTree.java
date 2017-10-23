package blue.datastructure.tree;

import blue.datastructure.list.linked.LinkedList;
import blue.datastructure.queue.Queue;

public class BinaryTree<T> {

    private Node<T> root;

    private void preOrder(Node<T> node) {
        if (null != node) {
            System.out.print(node.data + "\t");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    private void inOrder(Node<T> node) {
        if (null != node) {
            inOrder(node.leftChild);
            System.out.print(node.data + "\t");
            inOrder(node.rightChild);
        }
    }

    private void postOrder(Node<T> node) {
        if (null != node) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.data + "\t");
        }
    }

    public BinaryTree() {

    }

    public BinaryTree(Queue<T> queue) {
        root = create(queue);
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    public void levelOrder() {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (0 != queue.size()) {
            Node<T> node = queue.poll();
            System.out.print(node.data + "\t");
            if (null != node.leftChild) {
                queue.offer(node.leftChild);
            }
            if (null != node.rightChild) {
                queue.offer(node.rightChild);
            }
        }
        System.out.println();
    }

    private Node<T> create(Queue<T> queue) {
        if (0 != queue.size()) {
            T data = queue.poll();
            if (null == data) {
                return null;
            } else {
                Node<T> node = new Node<>();
                node.data = data;
                node.leftChild = create(queue);
                node.rightChild = create(queue);
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(null);
        queue.offer(4);
        BinaryTree<Integer> tree = new BinaryTree<>(queue);
        tree.preOrder();
        tree.inOrder();
        tree.postOrder();
    }
}