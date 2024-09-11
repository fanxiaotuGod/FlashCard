package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


// GUI interface of FlashCardApp application
// The code about json is referencing the sample.
public class FlashCardApp extends JFrame implements ActionListener, WindowListener {
    private static final String JSON_STORE = "./data/flash.json";
    private Folder folder;
    private FlashList currentList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton createFolder;
    private JButton deleteFolder;
    private JButton saveButton;
    private JButton reloadButton;
    private JButton goBackButton;
    private JButton changeButton;
    protected JScrollPane scrollpane;
    private JPanel topPanel;
    private JPanel listPanel;
    private Map<JPanel, FlashList> listMap;
    private Map<JPanel, FlashCard> cardMap;
    private boolean delete = false;
    private boolean change = false;
    private boolean isInList = false;



    // EFFECTS: runs the FlashCard application, and create a holder for all lists
    // initialize the GUI for the application with a starting picture
    public FlashCardApp() throws FileNotFoundException {
        super("MemoryMaster");
        listMap = new HashMap<>();
        cardMap = new HashMap<>();
        folder = new Folder("Stephen's folder");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);



        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600,700));
        setLocationRelativeTo(null); // Center the window
        initialButtons();
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);

        scrollpane = new JScrollPane(listPanel);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listPanel.add(new JLabel(new ImageIcon("src/main/ui/images/startingImage.png")));

        addWindowListener(this);

        contentPane.add(scrollpane, BorderLayout.CENTER);

        setContentPane(contentPane);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Create a dialog window with logs that were stored in the end of the application
    public void windowClosed(WindowEvent e) {
        //This will only be seen on standard output.
        String dialogText = "<html>";
        for (Event next : EventLog.getInstance()) {
            dialogText += next.toString() + "<br/>";
        }
        dialogText += "</html>";
        new Dialog(dialogText);
    }

    // EFFECTS: for getting rid of interface error
    public void windowOpened(WindowEvent e) {
       //System.out.println("Window opened");
    }

    // EFFECTS: for getting rid of interface error
    public void windowClosing(WindowEvent e) {
        //This will only be seen on standard output.
    }

    // EFFECTS: for getting rid of interface error
    public void windowIconified(WindowEvent e) {
       // System.out.println("1");
    }

    // EFFECTS: for getting rid of interface error
    public void windowDeiconified(WindowEvent e) {
        //System.out.println("2");
    }

    // EFFECTS: for getting rid of interface error
    public void windowActivated(WindowEvent e) {
        //System.out.println("3");
    }

    // EFFECTS: for getting rid of interface error
    public void windowDeactivated(WindowEvent e) {
        //System.out.println("4");
    }


    // MODIFIES: this
    // EFFECTS: initial all buttons, and add the topPanel to this and set it to the top
    public void initialButtons() {
        initialCreateAndDeleteFolderButtons();
        initialChangeAndBackButton();
        initialSaveAndLoadButtons();
        add(topPanel, BorderLayout.NORTH);
    }


    // MODIFIES: this
    // EFFECTS: initial create and delete button and add them to top panel
    public void initialCreateAndDeleteFolderButtons() {
        topPanel = new JPanel();
        createFolder = new JButton("Create");
        createFolder.setVerticalTextPosition(AbstractButton.CENTER);
        createFolder.setHorizontalTextPosition(AbstractButton.CENTER);
        createFolder.setActionCommand("create");
        createFolder.addActionListener(this);
        topPanel.add(createFolder);

        deleteFolder = new JButton("Delete");
        deleteFolder.setVerticalTextPosition(AbstractButton.CENTER);
        deleteFolder.setHorizontalTextPosition(AbstractButton.CENTER);

        deleteFolder.setActionCommand("delete");
        deleteFolder.addActionListener(this);
        topPanel.add(deleteFolder);


    }

    // MODIFIES: this
    // EFFECTS: initial save and load button with their names and add them to top panel
    public void initialSaveAndLoadButtons() {
        saveButton = new JButton("Save file");
        saveButton.setVerticalTextPosition(AbstractButton.CENTER);
        saveButton.setHorizontalTextPosition(AbstractButton.CENTER);
        saveButton.setActionCommand("save");
        reloadButton = new JButton("Reload file");
        reloadButton.setVerticalTextPosition(AbstractButton.CENTER);
        reloadButton.setHorizontalTextPosition(AbstractButton.CENTER);
        reloadButton.setActionCommand("reload");
        saveButton.addActionListener(this);
        reloadButton.addActionListener(this);
        topPanel.add(saveButton);
        topPanel.add(reloadButton);

    }

    // MODIFIES: this
    // EFFECTS: initial change and back button with their name, and add it to the top panel
    public void initialChangeAndBackButton() {
        goBackButton = new JButton("Go back");
        goBackButton.setVerticalTextPosition(AbstractButton.CENTER);
        goBackButton.setHorizontalTextPosition(AbstractButton.CENTER);
        goBackButton.setActionCommand("back");
        changeButton = new JButton("Change");
        changeButton.setVerticalTextPosition(AbstractButton.CENTER);
        changeButton.setHorizontalTextPosition(AbstractButton.CENTER);
        changeButton.setActionCommand("change");
        goBackButton.addActionListener(this);
        changeButton.addActionListener(this);
        topPanel.add(goBackButton);
        topPanel.add(changeButton);
    }




    // EFFECTS: add corresponding action to the buttons, create, delete, save, reload, back, change
    public void actionPerformed(ActionEvent e) {
        if ("create".equals(e.getActionCommand())) {
            if (!isInList) {
                createList();
            } else {
                createCard();
            }
        } else if ("delete".equals(e.getActionCommand())) {
            clickDelete();
        } else if ("save".equals(e.getActionCommand())) {
            clickSave();
        } else if ("reload".equals(e.getActionCommand())) {
            clickLoad();
        } else if ("back".equals(e.getActionCommand())) {
            clickBack();
        } else if ("change".equals(e.getActionCommand())) {
            clickChange();
        }
    }

    // MODIFIES: this
    // EFFECTS: click delete button this will help the user to delete list or flash card.
    // showing the color of the button to tell the user if the button is clicked
    private void clickDelete() {
        if (!delete) {
            delete = true;
            change = false;
            // Change the color of the delete button on the Event Dispatch Thread
            deleteFolder.setForeground(Color.RED);
            changeButton.setForeground(Color.BLACK);
        } else {
            delete = false;
            deleteFolder.setForeground(Color.BLACK);
        }
    }

    // MODIFIES: this
    // EFFECTS: click change button, if change button is clicked then change its status
    // it will help the user to delete list or flash card
    private void clickChange() {
        if (!change) {
            change = true;
            delete = false;
            changeButton.setForeground(Color.RED);
            deleteFolder.setForeground(Color.BLACK);
        } else {
            change = false;
            changeButton.setForeground(Color.BLACK);
        }
    }

    // MODIFIES: this
    // EFFECTS: go back to last level, if the user is in the list level then there's nothing to go back
    private void clickBack() {
        if (isInList) {
            isInList = false;
            visualizeList();
        } else {
            String text = "You're in the list level, there's nothing to go back";
            JOptionPane.showConfirmDialog(null, text, text, JOptionPane.DEFAULT_OPTION);
        }
    }

    // MODIFIES: this
    // EFFECTS: load the date from json file and show that it's successful
    private void clickLoad() {
        loadFolder();
        String text = "File loaded successfully";
        JOptionPane.showConfirmDialog(null, text, text, JOptionPane.DEFAULT_OPTION);
        visualizeList();
    }

    // MODIFIES: this
    // EFFECTS: save the current file and show the user that it's successful
    private void clickSave() {
        saveFolder();
        String text = "File saved successfully";
        JOptionPane.showConfirmDialog(null, text, text, JOptionPane.DEFAULT_OPTION);
    }




    // MODIFIES: this
    // EFFECTS: create a list with a name according to the name provided by the user
    private void createList() {
        String name = JOptionPane.showInputDialog("Enter the name of the list:");
        FlashList list = new FlashList(name);
        folder.addList(list);
        visualizeList();
    }

    // MODIFIES: this
    // EFFECTS: visualize lists in the folder to the users
    private void visualizeList() {
        listPanel.removeAll();
        listMap.clear();

        // generate all the lists
        for (FlashList list : folder.getLists()) {

            JPanel panelList = new JPanel();
            panelList.setPreferredSize(new Dimension(580,50));
            panelList.setMaximumSize(new Dimension(580,50));
            panelList.setBorder(new LineBorder(Color.DARK_GRAY, 2));
            panelList.setLayout(new CardLayout());

            JLabel name = createLabel(list.getName());
            panelList.add(name);
            listMap.put(panelList, list);

            listPanel.add(panelList);

            // add a mouse listener
            panelList.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickList(e);
                }
            });

        }

        listPanel.revalidate();
        listPanel.repaint();

    }

    // MODIFIES: this
    // EFFECTS: click List ,
    // if delete button is clicked, click list will delete it
    // if change button is clicked, the name of list will be changed
    // if no button are clicked then it will enter the list
    private void clickList(MouseEvent e) {
        if (delete && !isInList) {
            int index = findListIndex(e);
            folder.deleteList(index);
            delete = false;
            deleteFolder.setForeground(Color.BLACK);
            visualizeList();
        } else if (change && !isInList) {
            String name = JOptionPane.showInputDialog("Enter the name that you want to change:");
            listMap.get(e.getSource()).setName(name);
            change = false;
            changeButton.setForeground(Color.BLACK);
            visualizeList();
        } else {
            if (listMap.containsKey(e.getSource())) {
                currentList = listMap.get(e.getSource());
                isInList = true;
                visualizeCards(currentList);
            }
        }
    }


    // EFFECTS: return the index of the list in the folder
    public int findListIndex(MouseEvent e) {
        int index = 0;
        for (FlashList l : folder.getLists()) {
            if (l == listMap.get(e.getSource())) {
                break;
            }
            index++;
        }

        return index;
    }



    // MODIFIES: this
    // EFFECTS: create a flash card with front side and back side text and add it to the panel
    private void createCard() {
        String front = JOptionPane.showInputDialog("Enter Front Side:");
        String back = JOptionPane.showInputDialog("Enter Back Side:");

        FlashCard flashCard = new FlashCard("");
        flashCard.setFront(front);
        flashCard.setBack(back);

        currentList.getCards().add(flashCard);
        visualizeCards(currentList);
    }

    // MODIFIES: this
    // EFFECTS: visualize the flashcards of current list for the user on the board
    private void visualizeCards(FlashList list) {
        listPanel.removeAll();
        cardMap.clear();
        for (FlashCard card : list.getCards()) {
            JPanel panelList = new JPanel();
            panelList.setPreferredSize(new Dimension(550,200));
            panelList.setMaximumSize(new Dimension(550,200));
            panelList.setBorder(new LineBorder(Color.DARK_GRAY, 2));
            panelList.setLayout(new CardLayout());
            setCardContent(card, panelList);

            cardMap.put(panelList, card);
            listPanel.add(panelList);

            panelList.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickCard(e);
                }
            });
        }

        listPanel.revalidate();
        listPanel.repaint();
    }


    // EFFECTS: set the card content for the users
    private void setCardContent(FlashCard card, JPanel panelList) {
        if (card.getIsFront()) {
            JLabel frontLabel = createLabel(card.getFront());
            panelList.add(frontLabel);
        } else {
            JLabel frontLabel = createLabel(card.getBack());
            panelList.add(frontLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: click flashcard,
    // if delete button is clicked, click flashcard will delete it
    // if change button is clicked, the text of current side will be changed
    // if no button are clicked then it will flipside
    private void clickCard(MouseEvent e) {
        if (delete == true && isInList) {
            currentList.getCards().remove(cardMap.get(e.getSource()));
            delete = false;
            deleteFolder.setForeground(Color.BLACK);
            visualizeCards(currentList);
        } else if (change == true && isInList) {

            String text = JOptionPane.showInputDialog("Enter the text that you want to change:");
            cardMap.get(e.getSource()).changeCard(text);
            change = false;
            changeButton.setForeground(Color.BLACK);
            visualizeCards(currentList);
        } else {
            cardMap.get(e.getSource()).flipSide();
            visualizeCards(currentList);
        }
    }

    // EFFECTS: create a label with text and return it
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        return label;
    }

    // MODIFIES: this
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

    // Main method that is running the whole application
    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                new FlashCardApp();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}
