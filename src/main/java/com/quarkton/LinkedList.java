package com.quarkton;

public class LinkedList {
    private Node first;
    private Node last;
    private int size = 1;
    public LinkedList(int firstValue) {
        first = new Node(firstValue);
        last = first;
    }
    public LinkedList(int[] arr) {
        if(arr.length == 0) throw new IllegalArgumentException("Can not construct LinkedList with empty array");
        Node current = null;
        Node prev = null;
        for (int n : arr) {
            current = new Node(n);
            if (prev == null) {
                prev = current;
                first = current;
            } else {
                prev.setNext(current);
                prev = current;
            }
        }
        last=current;
        size = arr.length;
    }

    public int size() {
        return size;
    }

    public void addNodeLast(Node node) {
        last.setNext(node);
        last = last.next();
        size++;
    }

    public void addNodeFirstWithValue(int value) {
        Node temp = new Node(value);
        temp.setNext(first);
        first = temp;
        size++;
    }

    public void popFirst(int n) {
        if(size >= 0) {
            for (int i =0; i<n; i++) {
                Node temp = first;
                first = first.next();
                temp.setNext(null);
                size--;
            }
        }
    }

    public void popLast(int n) {
        reverse();
        popFirst(n);
        reverse();
    }

    public void reverse() {
        Node head = new Node();
        Node current = first;
        last = first;
        Node prev = null;
        while(current != null) {
            prev = current;
            current = current.next();
            if(head.next() == null) {
                head.setNext(first);
                first.setNext(null);
            } else {
                prev.setNext(head.next());
                head.setNext(prev);
            }
        }
        first = prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedList{=");
        Node temp = first;
        while(temp != null) {
            sb.append(temp.toString());
            temp = temp.next();
        }
        return  sb.append("=}").toString();
    }
}
