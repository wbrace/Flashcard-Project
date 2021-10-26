import java.io.File;
import java.util.ArrayList;

public class DoublyLinkedList {
    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    //adds node to the end
    public void append(Node newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    //adds node to the beginning
    public void prepend(Node newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
    }

    //inserts node after specified node
    public void insertAfter(Node currentNode, Node newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (currentNode == tail) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else {
            Node successor = currentNode.next;
            newNode.next = successor;
            newNode.previous = currentNode;
            currentNode.next = newNode;
            successor.previous = newNode;
        }
    }

    //removes node
    public void remove(Node currentNode) {
        Node successor = currentNode.next;
        Node predecessor = currentNode.previous;

        if (successor != null)
            successor.previous = predecessor;

        if (predecessor != null)
            predecessor.next = successor;

        if (currentNode == head)
            head = successor;

        if (currentNode == tail)
            tail = predecessor;
    }
    public void printGermanList(){
        Node currNode = this.head;
        while(currNode !=null){
            System.out.println(currNode.data.getGerWord());
            currNode = currNode.next;
            //System.out.println("");
        }
    }
    public void printEnglishList(){
        Node currNode = this.head;
        while(currNode !=null){
            System.out.println(currNode.data.getEngWord());
            currNode = currNode.next;
            //System.out.println("");
        }
    }
}
