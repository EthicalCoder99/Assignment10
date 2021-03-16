import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ContactDAO
 */
public class ContactDAO {

    private Connection conn = null;

    public ContactDAO(Connection conn) {
        this.conn = conn;
    }

    private String toValueString(Contact contact) {
        String res = " (";
        res += "\"" + contact.getContactID() + "\", ";
        res += "\"" + contact.getContactName() + "\", ";
        res += "\"" + contact.getEmail() + "\", ";
        res += "\"" + this.convertListToString(contact.getContactNumber()) + "\")";
        return res;
    }

    private Contact convertToContactObj(ResultSet rs) {
        Contact contact = new Contact();
        try {
            contact.setContactID(rs.getInt(1));
            contact.setContactName(rs.getString(2));
            contact.setEmail(rs.getString(3));
            contact.setContactNumber(this.convertStringToList(rs.getString(4)));
        } catch (SQLException e) {
            System.out.println("Error while converting to contact object." + e);
        }
        return contact;
    }

    private List<String> convertStringToList(String s) {
        return new ArrayList<String>(Arrays.asList(s.split(",")));
    }

    private String convertListToString(List<String> list) {
        if (list.size() == 0)
            return "";
        String result = "";
        for (int i = 0; i < (list.size() - 1); i++) {
            result += list.get(i) + ",";
        }
        result += list.get(list.size() - 1);
        return result;
    }

    public void insertContact(Contact contact) {
        try {
            Statement stmt = this.conn.createStatement();
            String insertStmt = "INSERT INTO contacts VALUES" + this.toValueString(contact);
            stmt.executeUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.println("Error while inserting contact data." + e);
        }
    }

    public Contact getContactByName(String contactName) {
        Contact contact = null;
        try {
            Statement stmt = this.conn.createStatement();
            String query = "SELECT * FROM contacts WHERE name=" + contactName;
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                contact = this.convertToContactObj(rs);
            }

        } catch (Exception e) {
            System.out.println("Error while fetching contact data." + e);
        }
        return contact;
    }

    public List<Contact> getContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        try {

            Statement stmt = this.conn.createStatement();
            String query = "SELECT * FROM contacts";
            ResultSet rs = stmt.executeQuery(query);
            Contact contact = null;
            while (rs.next()) {
                contact = this.convertToContactObj(rs);
                contactList.add(contact);
            }

        } catch (Exception e) {
            System.out.println("Error while fetching contacts and creating contact list." + e);
        }
        return contactList;
    }

    public void deleteContactById(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM contacts WHERE id=" + id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting contact." + e);
        }

    }

    public void createTables() {
        String createContactsTable = "CREATE TABLE IF NOT EXISTS contacts (id INT NOT NULL, name VARCHAR(30), email VARCHAR(40), contactNumbers VARCHAR(100), PRIMARY KEY(id))";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createContactsTable);
        } catch (SQLException e) {
            System.out.println("Error while creating contacts tables." + e);
        }
    }

    public void dropTables() {
        String dropContactsTable = "DROP TABLE contacts";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(dropContactsTable);
        } catch (SQLException e) {
            System.out.println("Error while dropping contacts table." + e);
        }
    }
}