/**
 * Represents a single contact record.
 *
 * The contact ID is required, immutable, and limited to 10 characters.
 * firstName and lastName are each required and limited to 10 characters.
 * phone is required and must be exactly 10 digits.
 * address is required and limited to 30 characters.
 */
public class Contact {

    private static final int MAX_ID_LENGTH = 10;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int PHONE_LENGTH = 10;
    private static final int MAX_ADDRESS_LENGTH = 30;

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.length() > MAX_ID_LENGTH) {
            throw new IllegalArgumentException(
                    "Contact ID must not be null and must not exceed " + MAX_ID_LENGTH + " characters");
        }
        this.contactId = contactId;

        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "First name must not be null and must not exceed " + MAX_NAME_LENGTH + " characters");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Last name must not be null and must not exceed " + MAX_NAME_LENGTH + " characters");
        }
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{" + PHONE_LENGTH + "}")) {
            throw new IllegalArgumentException(
                    "Phone must not be null and must be exactly " + PHONE_LENGTH + " digits");
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > MAX_ADDRESS_LENGTH) {
            throw new IllegalArgumentException(
                    "Address must not be null and must not exceed " + MAX_ADDRESS_LENGTH + " characters");
        }
        this.address = address;
    }
}
