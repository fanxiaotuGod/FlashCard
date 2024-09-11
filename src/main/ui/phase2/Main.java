package ui.phase2;

import java.io.FileNotFoundException;

// running the memoryMaster
public class Main {
    public static void main(String[] args) {
        try {
            new MemoryMaster();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
