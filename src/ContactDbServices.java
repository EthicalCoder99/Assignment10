import java.sql.Connection;
import java.util.List;

public class ContactDbServices {

    final private String username = "";
    final private String password = "";
    final private String database = "";

    private DbConnection dbConnection;
    private Connection conn;
    private ContactDAO contactDAO;

    public ContactDbServices() {
        this.dbConnection = new DbConnection(username, password, database);
        this.conn = dbConnection.getConnection();
        this.contactDAO = new ContactDAO(conn);
    }

    public void readContactsFromFile(String filePath) {

        contactDAO.dropTables();
        contactDAO.createTables();

        ContactServices contactServices = new ContactServices();

        List<Contact> contactList = contactServices.addContactFromFile(filePath);

        for (Contact contact : contactList) {
            contactDAO.insertContact(contact);
        }
    }

    public void displayContacts() {
        List<Contact> contactList = contactDAO.getContacts();
        for (Contact contact : contactList)
            System.out.println(contact);
    }
}
