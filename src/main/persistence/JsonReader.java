package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// And this class is referencing the code in the sample.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // This method is referencing the code in the sample
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    // This method is referencing the code in the sample
    public Folder read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFolder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // This method is referencing the code in the sample
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    // This method is referencing the code in the sample
    private Folder parseFolder(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Folder fd = new Folder(name);
        addLists(fd, jsonObject);
        return fd;
    }

    // MODIFIES: fl
    // EFFECTS: parses list from JSON object and adds them to folder
    // This method is referencing the code in the sample
    private void addLists(Folder fd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lists");
        for (Object json : jsonArray) {
            JSONObject nextList = (JSONObject) json;
            String name = nextList.getString("name");
            FlashList list = new FlashList(name);
            addList(list, nextList);
            fd.addList(list);
        }
    }

    // MODIFIES: fl
    // EFFECTS: parses flashcards from JSON object and adds them to list
    // This method is referencing the code in the sample
    private void addList(FlashList fl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object json : jsonArray) {
            JSONObject nextFlashCard = (JSONObject) json;
            addFlashCard(fl, nextFlashCard);
        }
    }

    // MODIFIES: fl
    // EFFECTS: parses flashcard from JSON object and adds it to list
    // This method is referencing the code in the sample
    private void addFlashCard(FlashList fl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String frontSide = jsonObject.getString("front_side");
        String backSide = jsonObject.getString("back_side");
        FlashCard flashcard = new FlashCard(name);
        flashcard.setFront(frontSide);
        flashcard.setBack(backSide);
        fl.addFlashCard(flashcard);
    }
}
