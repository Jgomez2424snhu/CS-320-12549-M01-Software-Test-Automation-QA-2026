import java.util.HashMap;
import java.util.Map;

/**
 * In-memory service for adding, updating, and deleting Contact objects.
 * Contacts are keyed by their unique contact ID.
 */
public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a new contact. The contact's ID must not already be in use.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException(
                    "A contact with ID " + contact.getContactId() + " already exists");
        }
        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Deletes the contact with the given ID.
     */
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("No contact found with ID " + contactId);
        }
        contacts.remove(contactId);
    }

    /**
     * Updates the updatable fields (firstName, lastName, phone, address) of the
     * contact with the given ID. Pass null for any field that should be left
     * unchanged. The contact ID itself can never be changed.
     */
    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("No contact found with ID " + contactId);
        }
        if (firstName != null) {
            contact.setFirstName(firstName);
        }
        if (lastName != null) {
            contact.setLastName(lastName);
        }
        if (phone != null) {
            contact.setPhone(phone);
        }
        if (address != null) {
            contact.setAddress(address);
        }
    }

    /**
     * Returns the contact with the given ID, or null if none exists.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}
