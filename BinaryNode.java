public class BinaryNode {
    public Flashcard key;
    public BinaryNode left;
    public BinaryNode right;

    public BinaryNode(Flashcard nodeKey) {
        key = nodeKey;
        left = null;
        right = null;
    }

    public void printNode(){
        System.out.println("");
        System.out.println("German Word: " + this.key.getGerWord());
        System.out.println("English Word: " + this.key.getEngWord());

    }
}

