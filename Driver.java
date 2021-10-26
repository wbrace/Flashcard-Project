//
//@file : Driver.java
// ∗ @description : implements the code for flashcards
// ∗ @author : Wesley Brace
// ∗ @date : August 8, 2021
// ∗ @acknowledgement :

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class Driver {
    //gets max length for Radix sort
    private static int radixGetMaxLength(ArrayList<Flashcard> flash) {
        int maxDigits = 0;
        for (int i = 0; i < flash.size(); i++) {
            int digitCount = radixGetLength(flash.get(i).getId());
            if (digitCount > maxDigits) {
                maxDigits = digitCount;
            }
        }
        return maxDigits;
    }

    //gets the number of digits for each number being sorted
    private static int radixGetLength(int value) {
        if (value == 0) {
            return 1;
        }

        int digits = 0;
        while (value != 0) {
            digits++;
            value /= 10;
        }
        return digits;
    }

    //radix sorts the flashcards by ID number or when they were added
    private static void radixSort(ArrayList<Flashcard> flash) {
        ArrayList<ArrayList<Flashcard>> buckets = new ArrayList<ArrayList<Flashcard>>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<Flashcard>());
        }

        int copyBackIndex = 0;

        // Find the max length, in number of digits
        int maxDigits = radixGetMaxLength(flash);

        int pow10 = 1;
        for (int digitIndex = 0; digitIndex < maxDigits; digitIndex++) {
            for (int i = 0; i < flash.size(); i++) {
                int num = flash.get(i).getId();
                int bucketIndex = (Math.abs(num) / pow10) % 10;
                buckets.get(bucketIndex).add(flash.get(i));
            }

            // Copy buckets back into numbers array
            copyBackIndex = 0;
            for (int i = 0; i < 10; i++) {
                ArrayList<Flashcard> bucket = buckets.get(i);
                for (int j = 0; j < bucket.size(); j++) {
                    flash.set(copyBackIndex,bucket.get(j));
                    //numbers[copyBackIndex] = bucket.get(j);
                    copyBackIndex++;
                }
                bucket.clear();
            }

            pow10 *= 10;
        }

    }

    //Selection Sorts the list in alphabetical order for german words
    private static void selectionSortGerman(ArrayList<Flashcard> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            // Find index of smallest remaining element
            int indexSmallest = i;
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(j).getGerWord().compareTo(cards.get(indexSmallest).getGerWord())
                 < 0 ) {
                    indexSmallest = j;
                }
            }

            // Swap numbers[i] and numbers[indexSmallest]
            Flashcard temp = cards.get(i);
            cards.set(i,cards.get(indexSmallest));
            cards.set(indexSmallest,temp);

        }
    }
    //Selection Sorts the list in alphabetical order for english words
    private static void selectionSortEnglish(ArrayList<Flashcard> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            // Find index of smallest remaining element
            int indexSmallest = i;
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(j).getEngWord().compareTo(cards.get(indexSmallest).getEngWord())
                        < 0 ) {
                    indexSmallest = j;
                }
            }

            // Swap numbers[i] and numbers[indexSmallest]
            Flashcard temp = cards.get(i);
            cards.set(i,cards.get(indexSmallest));
            cards.set(indexSmallest,temp);
        }
    }

    public static void printWelcome(){
        System.out.println("Welcome to your Flashcards!");
        System.out.println();
    }

    //prints the menu and gets user input
    public static void printMenu(){
        System.out.println("MENU OPTIONS:");
        System.out.println("(1) Add a flashcard.");
        System.out.println("(2) Remove a flashcard.");
        System.out.println("(3) Search for word definition.");
        System.out.println("(4) Practice.");
        System.out.println("(5) Quiz");
        System.out.println("(6) Print Directory.");
        System.out.println("(7) Exit Program.");
        System.out.println();
        System.out.print("I would like to: ");
    }

    //prints the list of words RECURSION
    public static void printList(int num, ArrayList<Flashcard> card){
        if(num >= card.size()){
            return;
        }
        else{
            card.get(num).printCard();
            num++;
            printList(num,card);
        }
    }

    public static void main(String args[]){
        Scanner scnr = new Scanner(System.in);

        //creates list to hold falshcards
        ArrayList<Flashcard> cardList = new ArrayList<Flashcard>();
        BinarySearchTree tree = new BinarySearchTree();
        printWelcome();
        int newID = 0;

        boolean inputValid = false;
        while (!inputValid) {
            printMenu();

            int choice = scnr.nextInt();
            while (choice == 1 || choice == 2 || choice == 3 || choice == 4
                    || choice == 5 || choice == 6 || choice==7) {
                //ads flashcard to list
                if(choice == 1) {
                    Flashcard newCard = new Flashcard();
                    inputValid = true;
                    System.out.println("What is the German word you are adding?");
                    newCard.setGerWord(scnr.next());
                    System.out.println("What is the English version?");
                    newCard.setEngWord(scnr.next());
                    newCard.setId(newID);
                    newID++;
                    BinaryNode newNode = new BinaryNode(newCard);
                    cardList.add(newCard);
                    tree.insert(newNode);

                }
                //removes flashcard
                else if(choice == 2){
                    inputValid = true;
                    boolean haveItem = false;
                    System.out.println("What word do you want to remove");
                    String name = scnr.next();
                    int wordLoc= 0;
                    while(!haveItem) {

                        //checks if word is there
                        for(int j = 0; j < cardList.size(); j++){
                            if(cardList.get(j).getGerWord().equals(name)
                                    ||cardList.get(j).getEngWord().equals(name)){
                                haveItem =true;
                                wordLoc = j;
                            }
                        }
                            if(haveItem) {
                                cardList.remove(wordLoc);
                                System.out.println("It is removed.");
                            }

                        if(!haveItem){
                            System.out.println("That file is not here, try another.");
                            name = scnr.next();
                        }
                    }
                }

                //searches for word
                else if(choice == 3) {

                    System.out.println("Are you searching for a German or English word?");
                    String wordType = scnr.next();
                    boolean validAns = false;

                    while(!validAns) {
                        if (wordType.equals("German")) {
                            validAns = true;
                            System.out.println("What word are you searching for?");
                            boolean haveItem = false;
                            String name = scnr.next();
                            while (!haveItem) {

                                if (name.equals("exit")) {
                                    haveItem = true;
                                } else {
                                    //searches list
                                    BinaryNode answer = tree.gerSearch(name);
                                    if (answer != null) {
                                        haveItem = true;
                                        answer.printNode();
                                    } else if (answer == null) {
                                        System.out.println("That word is not in the flashcards try again.");
                                        System.out.println("Or type exit to get to menu again.");
                                        name = scnr.next();
                                    }
                                }
                            }
                        } else if (wordType.equals("English")) {
                            validAns = true;
                            System.out.println("What word are you searching for?");
                            boolean haveItem = false;
                            String name = scnr.next();
                            while (!haveItem) {
                                if (name.equals("exit")) {
                                    haveItem = true;
                                } else {
                                    //searches list
                                    BinaryNode answer = tree.engSearch(name);
                                    if (answer != null) {
                                        haveItem = true;
                                        answer.printNode();
                                    } else if (answer == null) {
                                        System.out.println("That word is not in the flashcards try again.");
                                        System.out.println("Or type exit to get to menu again.");
                                        name = scnr.next();
                                    }
                                }


                            }

                        } else {
                            System.out.println("Not a valid option try again.");
                            wordType = scnr.next();
                        }
                    }
                }

                //practice with all the word on list
                else if(choice == 4){
                    System.out.println("Do you want to practice words");
                    System.out.println("(1) in alphabetical order");
                    System.out.println("(2)the order they were added");
                    String listType = scnr.next();
                    System.out.println("Do you want to practice with German or English?");
                    String wordType = scnr.next();
                    if(wordType.equals("German")){
                        if(listType.equals("1")){
                            selectionSortGerman(cardList);
                           // quicksortGerman(cardList,0, cardList.size()-1);
                        }
                        else if(listType.equals("2")){
                            radixSort(cardList);
                        }
                        System.out.println("");
                        int i = 0;
                        int count = 0;
                        String ans;
                        System.out.println("Type exit if you want to exit.");
                        do{
                           System.out.println("What is the english word for " +
                                   cardList.get(i).getGerWord());
                           ans = scnr.next();

                           //checks if word is correct
                           if(ans.equals(cardList.get(i).getEngWord())){
                               System.out.println("That is correct!");
                               i++;
                               count++;
                               System.out.println("");
                           }
                           else{
                               System.out.println("That is incorrect");
                               System.out.println("The correct answer is " +
                                       cardList.get(i).getEngWord());
                               i++;
                               System.out.println("");
                           }
                        }
                        while(!ans.equals("exit") && i < cardList.size());
                        int wordCount = i;
                        if(ans.equals("exit")){
                            wordCount = wordCount-1;
                        }
                        double percent = count/wordCount;
                        System.out.println("You got " + count + "/" + wordCount);

                    }
                    else if(wordType.equals("English")){
                        if(listType.equals("1")){
                            selectionSortEnglish(cardList);
                            // quicksortGerman(cardList,0, cardList.size()-1);
                        }
                        else if(listType.equals("2")){
                            radixSort(cardList);
                        }
                        System.out.println("");
                        int i = 0;
                        int count = 0;
                        String ans;
                        System.out.println("Type exit if you want to exit.");
                        do{
                            System.out.println("What is the german word for " +
                                    cardList.get(i).getEngWord());
                            ans = scnr.next();
                            //checks if word is correct
                            if(ans.equals(cardList.get(i).getGerWord())){
                                System.out.println("That is correct!");
                                i++;
                                count++;
                                System.out.println("");
                            }
                            else{
                                System.out.println("That is incorrect");
                                System.out.println("The correct answer is " +
                                        cardList.get(i).getGerWord());
                                i++;
                                System.out.println("");
                            }
                        }
                        while(!ans.equals("exit") && i < cardList.size());
                        int wordCount = i;
                        if(ans.equals("exit")){
                            wordCount = wordCount-1;
                        }
                        double percent = count/wordCount;
                        System.out.println("You got " + count + "/" + wordCount);
                    }

                }

                //quizzes user on a set of 5 random words
                else if(choice == 5){
                    inputValid = true;
                    if(cardList.size()>5) {
                        inputValid = true;
                        ArrayList<Integer> index = new ArrayList<Integer>();
                        Random randy = new Random();
                        for (int i = 0; i < 5; i++) {
                            int h = randy.nextInt(cardList.size() - 1);
                            if (!index.contains(h)) {
                                index.add(h);
                            } else {
                                i--;
                            }
                        }
                        DoublyLinkedList quiz = new DoublyLinkedList();
                        for (int i = 0; i < index.size(); i++) {
                            Node newNode = new Node(cardList.get(index.get(i)));
                            quiz.append(newNode);
                        }
                        System.out.println("Here are 5 random words.");
                        System.out.println("Write their meaning in english (hit enter after each word).");
                        quiz.printGermanList();
                        ArrayList<String> answers = new ArrayList<String>();
                        for (int i = 0; i < 5; i++) {
                            String ans = scnr.next();
                            answers.add(ans);
                        }
                        Node head = quiz.getHead();
                        int count = 0;
                        for (int i = 0; i < 5; i++) {
                            if (answers.get(i).equals(head.data.engWord)) {
                                count++;
                            }
                            head = head.next;
                        }
                        if (count == 5) {
                            System.out.println("You got all five correct!");
                        } else {
                            System.out.println("You missed one answer.");
                            System.out.println("Here are the correct answers.");
                            quiz.printEnglishList();
                        }
                    }
                    else{
                        System.out.println("You need more than 5 words in the list");
                    }

                }

                //prints out flashcards
                else if(choice == 6){
                    inputValid = true;
                    System.out.println("");
                    System.out.println("Flashcards");
//                    for(int i  = 0; i < cardList.size(); i++){
//                        cardList.get(i).printCard();
//                    }
                    printList(0,cardList);
                    System.out.println("");

                }

                //exits program and prints flashcards to file
                if (choice == 7) {
                    inputValid = true;
                    System.out.println("Are you sure you want to leave? Yes/No");
                    String name = scnr.next();
                    if(name.equals("Yes")) {
                        System.out.println("Do you want to print flashcards onto a file? Yes/No");
                        String ans = scnr.next();
                        if(ans.equals("Yes")){
                            try {
                                FileOutputStream fileStream = new FileOutputStream("Flashcards2.txt");
                                PrintWriter filePrinter = new PrintWriter(fileStream);
                                filePrinter.println("Flashcards:");
                                for(int i = 0; i < cardList.size();i++){
                                    filePrinter.println("");
                                    filePrinter.println("German Word: " + cardList.get(i).getGerWord());
                                    filePrinter.println("English Word: " + cardList.get(i).getEngWord());
                                }


                                filePrinter.close();
                            }
                            catch (IOException e){
                                System.out.println("An error occurred");
                                System.out.println(e.getMessage());
                            }

                        }
                        System.exit(0);
                    }
                }

                System.out.println("");
                printMenu();
                choice = scnr.nextInt();
            }
        }
    }
}
