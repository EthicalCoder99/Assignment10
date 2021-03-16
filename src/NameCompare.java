
import java.util.Comparator;

public class NameCompare implements Comparator<Contact> {
    @Override
    public int compare(Contact contact1, Contact contact2) {
        return contact1.getContactName().compareTo(contact2.getContactName());
    }
}
