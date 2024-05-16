package mancala;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;
import java.nio.file.*;

public class Saver implements Serializable {
    private static final long serialVersionUID = -3715718792458960047L;
    private Path currentDirectory = Paths.get(System.getProperty("user.dir"));
    private Path assetsPath = currentDirectory.resolve("assets");

    public void saveObject (Serializable toSave, String filename) throws IOException {
        File assets = new File("assets");
        if (Files.exists(assetsPath) && Files.isDirectory(assetsPath)) {
            System.out.println("The folder exists.");
        } else {
            System.out.println("The folder does not exist.");
            assets.mkdir();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(assetsPath + "/" + filename))) {
            out.writeObject(toSave);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Serializable loadObject (String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(assetsPath + "/" + filename))) {
            return (Serializable) in.readObject();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return filename;
    }
}