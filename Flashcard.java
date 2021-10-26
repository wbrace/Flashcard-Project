//
//@file : Driver.java
// ∗ @description : creates flashcard objects
// ∗ @author : Wesley Brace
// ∗ @date : August 8, 2021
// ∗ @acknowledgement :

public class Flashcard {
    protected int id;
    protected String gerWord;
    protected String engWord;

    public Flashcard(){
        gerWord = null;
        engWord = null;
        id = 0;
    }

    public Flashcard(int startId, String startGerWord, String startEngWord){
        gerWord = startGerWord;
        engWord = startEngWord;
        id = startId;
    }

    public int getId() {
        return id;
    }

    public String getGerWord() {
        return gerWord;
    }

    public String getEngWord() {
        return engWord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGerWord(String gerWord) {
        this.gerWord = gerWord;
    }

    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }
    public void printCard(){
        System.out.println("");
        System.out.println("German Word: " + this.getGerWord());
        System.out.println("English Word: " + this.getEngWord());

    }
}
