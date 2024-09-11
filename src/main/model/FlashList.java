package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The list that is made of flash cards
public class FlashList implements Writable {
    private final List<FlashCard> list;
    private String name;
    private int size;

    // EFFECTS: create a new list for flash cards
    public FlashList(String name) {
        list = new ArrayList<>();
        this.name = name;
        size = 0;
        EventLog.getInstance().logEvent(new Event("List created."));
    }

    // MODIFIES: this
    // EFFECTS: adds a flash card to the list, and increase the size of the list by 1
    public void addFlashCard(FlashCard card) {
        list.add(card);
        size++;
        EventLog.getInstance().logEvent(new Event("Flash card added to a list."));
    }

    public List<FlashCard> getCards() {
        return list;
    }

    // MODIFIES: this
    // EFFECTS: change the name of the list and add it to the dialog
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("List name changed."));
    }

    // MODIFIES: this
    // REQUIRES: position >=0 && position <= list.getSize() - 1
    // EFFECTS: delete a flash card on its position
    public void deleteCard(int position) {
        list.remove(position);
        size--;
        EventLog.getInstance().logEvent(new Event("Flash card deleted from a list."));
    }

    // REQUIRES: position >=0 && position <= list.getSize() - 1
    // EFFECTS: get a flash card on a specific position
    public FlashCard getFlashCard(int position) {
        return list.get(position);
    }


    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: print out the information of the list and all flash cards
    @Override
    public String toString() {
        String output = "";
        int index = 1;
        for (FlashCard card : list) {
            output = output + index + ". " + card.getName() + "\n" + card;
            index++;
        }
        return output;
    }

    // EFFECTS: Used to writting the json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("list", flashCardsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray flashCardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FlashCard l : list) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
