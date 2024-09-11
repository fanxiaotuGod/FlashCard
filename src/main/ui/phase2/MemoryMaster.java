package ui.phase2;

import model.FlashCard;
import model.FlashList;
import model.Folder;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// MemoryMaster application
// The code about json is referencing the sample.
public class MemoryMaster {
    private static final String JSON_STORE = "./data/flash.json";
    private Folder folder;
    private FlashList currentList;
    private Scanner input;
    private boolean inFolder = true;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the MemoryMaster application, and create a holder for all lists
    public MemoryMaster() throws FileNotFoundException {
        folder = new Folder("Stephen's folder");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMemoryMaster();
    }

    // this method is referencing in-class lab TellerApp
    // MODIFIES: this
    // EFFECTS: running the MemoryMaster application, process the command from the users
    private void runMemoryMaster() {

        boolean running = true;
        String command = null;

        initialize();
        while (running) {

            if (inFolder) {
                folderInterface();
            } else {
                listInterface();
            }

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                runCommand(command);
            }

        }
    }

    // REQUIRES: command != null
    // MODIFIES: this
    // EFFECTS: processes user command depends which level of current status
    //          if we are in the folder, then showing the folder menu
    //          if we are in a list, then showing the list menu
    private void runCommand(String command) {
        if (inFolder) {
            runInFolder(command);
        } else {
            runInList(command);
        }

    }

    // MODIFIES: this
    // EFFECTS: process users' command
    private void runInFolder(String command) {
        if (command.equals("a")) {
            createList();
        } else if (command.equals("b")) {
            enterList();
        } else if (command.equals("c")) {
            deleteList();
        } else if (command.equals("d")) {
            saveFolder();
        } else if (command.equals("e")) {
            loadFolder();
        } else {
            System.out.println("No such command");
        }
    }

    // MODIFIES: this
    // EFFECTS: process users' command
    private void runInList(String command) {
        if (command.equals("a")) {
            createFlashCard();
        } else if (command.equals("b")) {
            flipFlashCard();
        } else if (command.equals("c")) {
            changeFlashCard();
        } else if (command.equals("d")) {
            deleteFlashCard();
        } else if (command.equals("e")) {
            inFolder = true;
        } else {
            System.out.println("No such command");
        }

    }

    // this code is referencing in class lab TellerApp
    // MODIFIES: this
    // EFFECTS: initializes a starting flash card
    public void initialize() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //functions that are used in folder level

    // MODIFIES: this
    // EFFECTS: create a list with a name according to the name provided by the user
    private void createList() {
        System.out.println("Enter the name for the new list");
        Scanner stringScanner = new Scanner(System.in);
        String name = stringScanner.next();
        FlashList list = new FlashList(name);
        folder.addList(list);
    }

    // MODIFIES: thsi
    // EFFECTS: open and showing the n-th list
    private void enterList() {
        System.out.println("Enter the number of list that you want to open");
        int number = input.nextInt();
        inFolder = false;
        currentList = folder.getList(number - 1);
    }

    // MODIFIES: this
    // EFFECTS: delete the n-th list
    private void deleteList() {
        System.out.println("Enter the number of list that you want to delete");
        int number = input.nextInt();
        folder.deleteList(number - 1);
    }

    // EFFECTS: showing all the lists with an increasing index
    private void showAllLists() {
        int index = 1;
        for (FlashList list : folder.getLists()) {
            System.out.println(index + ". " + list.getName());
            index++;
        }
    }


    // functions that are used in list level

    // MODIFIES: this
    // EFFECTS:  create a flash card with a name, and set front,back content
    //           also add it into the list
    private void createFlashCard() {
        System.out.println("Enter the name for the new flash-card");
        Scanner stringScanner = new Scanner(System.in);
        stringScanner.useDelimiter("\n");
        String name = stringScanner.next();
        FlashCard card = new FlashCard(name);

        System.out.println("Enter the content for front-side:");
        stringScanner = new Scanner(System.in);
        stringScanner.useDelimiter("\n");
        String front = stringScanner.next();
        card.setFront(front);
        System.out.println("Enter the content for back-side:");
        stringScanner = new Scanner(System.in);
        stringScanner.useDelimiter("\n");
        String back = stringScanner.next();
        card.setBack(back);
        currentList.addFlashCard(card);
    }

    // MODIFIES: this
    // EFFECTS:  flip the n-th flash card
    private void flipFlashCard() {
        System.out.println("Enter the number of flashCard that you want to flip");
        int number = input.nextInt();
        currentList.getFlashCard(number - 1).flipSide();
    }

    // MODIFIES: this
    // EFFECTS: change the content of n-th flashcard
    //          the side that you can see will be changed
    private void changeFlashCard() {
        System.out.println("Please type in the index of flash card that you want to change:");
        int number = input.nextInt();
//        currentList.getFlashCard(number - 1).flipSide();
        System.out.println("Type the content that you want to change to the current-side of the flash-card:");
        Scanner stringScanner = new Scanner(System.in);
        stringScanner.useDelimiter("\n");
        String text = stringScanner.next();
        currentList.getFlashCard(number - 1).changeCard(text);
    }

    // MODIFIES: this
    // EFFECTS: delete the n-th flash card
    private void deleteFlashCard() {
        System.out.println("Please type in the index of flash card that you want to delete:");
        int number = input.nextInt();
        currentList.deleteCard(number - 1);
    }

    // EFFECTS: prompts user to select the action that will be done in folder lever
    private void folderInterface() {
        if (folder.isEmpty()) {
            System.out.println("Current folder is empty:");
        }
        showAllLists();
        System.out.println("\nKeyboard interface please type in character:");
        System.out.println("\ta. Create list");
        System.out.println("\tb. Show n-th list");
        System.out.println("\tc. Delete n-th list");
        System.out.println("\td. Save lists to file");
        System.out.println("\te. load lists from file");
        System.out.println("\tq. Quit");

    }

    // EFFECTS: prints the name of lists
    private void showAllCards() {
        System.out.println("Current List: " + currentList.getName());
        System.out.println(currentList.toString());
    }

    // EFFECTS: prompts user to select the action that will be done in list lever
    private void listInterface() {
        showAllCards();
        System.out.println("\nKeyboard interface please type in character:");
        System.out.println("\ta. Create flash-card");
        System.out.println("\tb. Flip n-th flash-card");
        System.out.println("\tc. Change flash-card");
        System.out.println("\td. Delete flash-card");
        System.out.println("\te. Go back to folder");
    }

    // EFFECTS: saves the workroom to file
    private void saveFolder() {
        try {
            jsonWriter.open();
            jsonWriter.write(folder);
            jsonWriter.close();
            System.out.println("Saved " + folder.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFolder() {
        try {
            folder = jsonReader.read();
            System.out.println("Loaded " + folder.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
