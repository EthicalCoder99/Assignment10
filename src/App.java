import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String contactsFilePath = "src/Contacts.txt";
        String contactsSerializedFilePath = "src/ContactsSerialize.txt";
        ContactServices cServices = new ContactServices();
        List<Contact> cList = cServices.addContactFromFile(contactsFilePath);

        // for (Contact contact : cList) {
        // System.out.println(contact);
        // }

        // Serialization serialization = new Serialization();
        // serialization.serialize(cList, contactsSerializedFilePath);

        // Deserialization deserialization = new Deserialization();
        // List<Contact> dList =
        // deserialization.deserialize(contactsSerializedFilePath);

        // System.out.println("\n\n***Deserialized***");
        // for (Contact contact : dList) {
        // System.out.println(contact);
        // }

        // ContactDbServices cDbServices = new ContactDbServices();
        // cDbServices.readContactsFromFile(contactsFilePath);
        // cDbServices.displayContacts();

        // for (Contact contact : cServices.searchContactByNumber("9")) {
        // System.out.println(contact);
        // }

        // for (Contact contact : cServices.searchContactByNumber("345")) {
        // System.out.println(contact);
        // }

        System.out.println(cServices.searchContactByName("Rohan"));

    }
}
