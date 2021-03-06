package com.dominicwong.DataStructures.LinkedList;

import java.util.HashSet;

/**
 * Created by dominicwong on 1/2/15.
 */
public class LinkedList {
    private Node head;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        Node current = head;
        while (current != null) {
            if (current.getNext() == null) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void insertHead(int data) {
        Node newHead = new Node(data);
        newHead.setNext(head);
        this.head = newHead;
    }

    public void deleteHead() {
        this.head = head.getNext();
    }

    public int length() {
        Node current = head;
        int length = 0;
        while (current != null) {
            length++;
            current = current.getNext();
        }
        return length;
    }

    public Node search(int data) {
        Node current = head;
        while (current != null) {
            if (current.getData() == data) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public Node findKthElementFromTheEnd(int k) {
        if (k > length()) {
            return null;
        }

        if (k == length()) {
            return head;
        }

        Node current = head;
        int targetNodeIndex = length() - k + 1;
        int currentIndex = 1;
        while (current != null) {
            currentIndex++;
            current = current.getNext();
            if (currentIndex == targetNodeIndex) {
                return current;
            }
        }
        return null;
    }

    public void reverse() {
        int length = length();
        if (length <= 1) {
            return;
        }

        Node[] nodes = new Node[length];
        Node current = head;
        int currentIndex = 0;
        while (current != null) {
            nodes[currentIndex] = current;
            current = current.getNext();
            if (current != null) {
                currentIndex++;
            }
        }
        while (currentIndex >= 0) {
            Node node = nodes[currentIndex];
            if (currentIndex == length - 1) {
                this.head = node;
            }
            currentIndex--;
            if (currentIndex >= 0) {
                node.setNext(nodes[currentIndex]);
            } else {
                node.setNext(null);
            }
        }
    }

    public void removeDuplicates() {
        if (length() <= 1) {
            return;
        }
        HashSet<Integer> datas = new HashSet<Integer>();

        Node prev = head;
        Node current = prev.getNext();

        datas.add(new Integer(prev.getData()));
        while (current != null) {
            Integer val = new Integer(current.getData());
            if (datas.contains(val)) {
                prev.setNext(current.getNext());
                current = current.getNext();
            } else {
                datas.add(new Integer(current.getData()));
                prev = prev.getNext();
                current = current.getNext();
            }
        }
    }

    public void deleteNode(Node node) {
        Node prev = head;
        Node current = prev.getNext();

        while (current != null) {
            if (current.getData() == node.getData()) {
                prev.setNext(current.getNext());
                return;
            }
            prev = prev.getNext();
            current = current.getNext();
        }
    }

    public void appendLastNNodesToBeginningOfList(int n) {
        int length = length();
        if (head == null || length == 1 || n >= length) {
            return;
        }
        int targetNodeCount = length - n + 1;
        int currentCount = 1;
        Node current = head;
        Node newHead = null;
        Node tail = null;
        Node newTail = null;
        while (current != null) {
            if (currentCount == targetNodeCount - 1) {
                newTail = current;
            }
            if (currentCount == targetNodeCount) {
                newHead = current;
            }
            if (current.getNext() == null) {
                tail = current;
            }
            current = current.getNext();
            currentCount++;
        }
        tail.setNext(head);
        newTail.setNext(null);
        this.head = newHead;
    }

    public boolean isCircular() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.getNext() != null) {
            if (fast.getNext().getNext() == slow.getNext()) {
                return true;
            }
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "{";
        Node current = this.head;
        while (current != null) {
            result += current.toString() + ", ";
            current = current.getNext();
        }
        result += "}";
        return result;
    }
}
