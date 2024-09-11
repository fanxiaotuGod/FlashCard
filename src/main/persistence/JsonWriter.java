package persistence;

import model.Event;
import model.EventLog;
import model.FlashList;
import model.Folder;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
// And this class is referencing the code in the sample.
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    // This method is referencing the code in the sample
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // This method is referencing the code in the sample
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    // This method is referencing the code in the sample
    public void write(Folder f) {
        JSONObject json = f.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // This method is referencing the code in the sample
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // This method is referencing the code in the sample
    private void saveToFile(String json) {
        writer.print(json);
    }
}