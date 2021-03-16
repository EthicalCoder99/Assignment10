import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	private int contactID;
	private String contactName;
	private String email;
	private List<String> contactNumbers;

	public Contact() {
	}

	public Contact(int contactID, String contactName, String email, ArrayList<String> contactNumber) {
		super();
		this.contactID = contactID;
		this.contactName = contactName;
		this.email = email;
		this.contactNumbers = contactNumber;
	}

	public int getContactID() {
		return contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getContactNumber() {
		return contactNumbers;
	}

	public void setContactNumber(List<String> contactNumber) {
		this.contactNumbers = contactNumber;
	}

	@Override
	public String toString() {
		String result = "";
		result += "\nContact Id : " + this.contactID;
		result += "\nName : " + this.contactName;
		result += "\nEmail : " + this.email;
		result += "\nContact Numbers : " + this.contactNumbers;
		return result;
	}
}
