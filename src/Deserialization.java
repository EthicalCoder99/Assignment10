import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Deserialization {
    public List<Contact> deserialize(String fileName) {
        List<Contact> contactList = null;
        File file = new File(fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream);) {

            contactList = (List<Contact>) objectOutputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not able to convert to file output stream.");
            e.printStackTrace();
        }
        return contactList;
    }
}
