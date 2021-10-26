import java.io.File;
import java.io.FileInputStream;

//creates Node object to be stored in doubly linked list
public class Node {
    //public FileInputStream data;
    public Flashcard data;
    public Node next;
    public Node previous;

    public Node(Flashcard initialData) {
        data = initialData;
        next = null;
        previous = null;
    }



}

