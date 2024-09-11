package model;

import org.json.JSONObject;
import persistence.Writable;

// A class of flash card that has front side and back side
public class FlashCard implements Writable {
    private String frontSide;  // the content that user wants to store on the front
    private String backSide;   // the content that user wants to store on the back
    private final String name;       // the name of the flashcard
    private String currentSide;// default showing side starting from the front
    private boolean isFront;

    // EFFECTS: Create a flash card with empty content, and with name
    public FlashCard(String name) {
        frontSide = "";
        backSide = "";
        this.name = name;
        currentSide = frontSide;
        isFront = true;
        EventLog.getInstance().logEvent(new Event("Flash card added to the list."));
    }

    // MODIFIES: this
    // EFFECTS: change the content of the current side
    //          if it's front size, then change the front side
    //          else change the back side
    public void changeCard(String content) {
        if (isFront) {
            this.frontSide = content;
            currentSide = frontSide;
        } else {
            this.backSide = content;
            currentSide = backSide;
        }
        EventLog.getInstance().logEvent(new Event("Flash card text changed."));
    }

    // MODIFIES: this
    // EFFECTS: set the content for front side, and default currentSide is front Side
    public void setFront(String content) {
        frontSide = content;
        currentSide = frontSide;
    }

    public void setBack(String content) {
        backSide = content;
    }

    // For testing front
    public String getFront() {
        return frontSide;
    }

    // For testing back
    public String getBack() {
        return backSide;
    }

    /*
     * MODIFIES: this
     * EFFECTS: flip side of the flashcard so that the user can see different side of the card
     *
     */
    public void flipSide() {
        if (isFront) {
            currentSide = backSide;
            isFront = false;
        } else {
            currentSide = frontSide;
            isFront = true;
        }
        EventLog.getInstance().logEvent(new Event("Flash card flipped."));
    }


    public String getName() {
        return name;
    }

    public boolean getIsFront() {
        return isFront;
    }

    // EFFECTS:  // EFFECTS: print out the information of the flash cards, and also the current side's content
    @Override
    public String toString() {
        String separation = "-------------------------------------";
        return separation + "\n" + currentSide + "\n" + separation + "\n";
    }

    // EFFECTS: use for writting the json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("front_side", frontSide);
        json.put("back_side", backSide);
        return json;
    }

}
