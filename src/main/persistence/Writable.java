package persistence;

import org.json.JSONObject;

// The class is referencing the code in the sample
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
