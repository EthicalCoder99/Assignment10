
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ContactServices {
    private static NameCompare nameComparator = new NameCompare();
    private List<Contact> contactList;

    public ContactServices() {
        contactList = new ArrayList<Contact>();
    }

    public List<Contact> addContactFromFile(String filePath) {
        File file = new File(filePath);
        List<Contact> contactList = new ArrayList<Contact>();
        try (Scanner scanner = new Scanner(file);) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String words[] = data.split(",", 0);
                Contact new_contact = new Contact();

                new_contact.setContactID(Integer.parseInt(words[0]));
                new_contact.setContactName(words[1]);
                new_contact.setEmail(words[2]);

                ArrayList<String> contactNumbers = new ArrayList<String>();
                for (int i = 3; i < words.length; i++) {
                    contactNumbers.add(words[i]);
                }

                new_contact.setContactNumber(contactNumbers);
                contactList.add(new_contact);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\n*** Error while opening file. ***\n\n");
            e.printStackTrace();
        }

        this.contactList = contactList;
        return this.contactList;
    }

    public List<Contact> getContactList() {
        return this.contactList;
    }

    public void addContact(Contact contact) {
        this.contactList.add(contact);
    }

    public void removeContact(String contactName) {
        try {
            for (Contact contact : this.contactList) {
                if (contact.getContactName().equals(contactName)) {
                    this.contactList.remove(contact);
                    return;
                }
                throw new ContactNotFoundException("Error while removing contact.");
            }
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Contact searchContactByName(String contactName) {
        try {
            for (Contact contact : contactList) {
                if (contact.getContactName().equals(contactName)) {
                    return contact;
                }
            }
            throw new ContactNotFoundException("Error while searching contacts by name.");
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> searchContactByNumber(String contactNumber) {
        List<Contact> resultList = new ArrayList<Contact>();
        for (Contact contact : this.contactList) {
            for (String contactNum : contact.getContactNumber()) {
                if (contactNum.contains(contactNumber)) {
                    resultList.add(contact);
                    break;
                }
            }
        }
        try {
            if (resultList.isEmpty())
                throw new ContactNotFoundException("Error while searching contact by number.");
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void addContactNumber(Integer contactID, String contactNumber) {
        for (Contact contact : this.contactList) {
            if (contactID.equals(contact.getContactID())) {
                contact.getContactNumber().add(contactNumber);
            }
        }
    }

    public List<Contact> orderByName() {
        List<Contact> resultList = new ArrayList<Contact>(this.contactList);
        Collections.sort(resultList, nameComparator);
        return resultList;
    }

    public void addContactsFromList(List<Contact> contactList) {
        for (Contact contact : contactList) {
            this.contactList.add(contact);
        }
    }
}
