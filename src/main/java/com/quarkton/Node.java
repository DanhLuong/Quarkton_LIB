package com.quarkton;

public class Node{
    public Node() {
        this.value = 0;
    }
    public Node(int value) {
        this.value = value;
    }
    public Node copyFrom(Node node) {
        Node newNode = new Node(node.value());
        if(node.next!=null) {
            newNode.setNext(node.next());
        }
        return newNode;
    }
    private int value;
    private Node next;
    public int value() {
        return value;
    }

    public Node next() {
        return next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "{" + value +'}';
    }
}
