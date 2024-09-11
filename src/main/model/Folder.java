package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The folder that contains the lists
public class Folder implements Writable {
    private String name;
    private List<FlashList> lists;
    private int size;

    // EFFECTS: Create a folder with a name
    public Folder(String name) {
        this.name = name;
        lists = new ArrayList<>();
        size = 0;
//        EventLog.getInstance().logEvent(new Event("Folder created."));
    }

    // MODIFIES: this
    // EFFECTS: adds a flash card to the list, and increase the size of the list by 1
    public void addList(FlashList list) {
        lists.add(list);
        size++;
//        EventLog.getInstance().logEvent(new Event("List added to a folder."));
    }

    // MODIFIES: this
    // REQUIRES: position >=0 && position <= list.getSize() - 1
    // EFFECTS: delete a flash card on its position
    public void deleteList(int position) {
        lists.remove(position);
        size--;
        EventLog.getInstance().logEvent(new Event("List deleted from a folder."));
    }

    public int getSize() {
        return size;
    }

    public List<FlashList> getLists() {
        return lists;
    }

    public FlashList getList(int position) {
        return lists.get(position);
    }

    // EFFECTS: check if the folder is empty
    public boolean isEmpty() {
        return lists.isEmpty();
    }

    public String getName() {
        return name;
    }

    // Help to write the json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lists", listsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray listsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FlashList l : lists) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }


}
