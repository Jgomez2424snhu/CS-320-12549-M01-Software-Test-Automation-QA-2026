import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Verifies that the Contact class meets all five contact class requirements:
 * a required, non-null, non-updatable contact ID (max 10 chars); a required,
 * non-null firstName (max 10 chars); a required, non-null lastName (max 10
 * chars); a required, non-null phone (exactly 10 digits); and a required,
 * non-null address (max 30 chars).
 */
class ContactTest {

    private static final String VALID_ID = "1234567890";
    private static final String VALID_FIRST_NAME = "John";
    private static final String VALID_LAST_NAME = "Smith";
    private static final String VALID_PHONE = "1234567890";
    private static final String VALID_ADDRESS = "123 Main Street";

    @Test
    void testValidContactIsCreatedSuccessfully() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);

        assertEquals(VALID_ID, contact.getContactId());
        assertEquals(VALID_FIRST_NAME, contact.getFirstName());
        assertEquals(VALID_LAST_NAME, contact.getLastName());
        assertEquals(VALID_PHONE, contact.getPhone());
        assertEquals(VALID_ADDRESS, contact.getAddress());
    }

    // ---- Contact ID: required, not null, max 10 characters, not updatable ----

    @Test
    void testContactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS));
    }

    @Test
    void testContactIdAtMaxLengthIsAccepted() {
        String tenCharId = "1234567890";
        Contact contact = new Contact(tenCharId, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        assertEquals(tenCharId, contact.getContactId());
    }

    @Test
    void testContactIdLongerThanTenCharactersIsRejected() {
        String elevenCharId = "12345678901";
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(elevenCharId, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS));
    }

    @Test
    void testContactIdIsNotUpdatable() {
        // Contact exposes no setter for contactId, so it can never change after
        // construction. Confirm it stays the same even as other fields update.
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        contact.setFirstName("Jane");
        contact.setLastName("Doe");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Avenue");

        assertEquals(VALID_ID, contact.getContactId());
    }

    // ---- firstName: required, not null, max 10 characters ----

    @Test
    void testFirstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, null, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS));
    }

    @Test
    void testFirstNameAtMaxLengthIsAccepted() {
        String tenCharName = "Alexanderr";
        Contact contact = new Contact(VALID_ID, tenCharName, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        assertEquals(tenCharName, contact.getFirstName());
    }

    @Test
    void testFirstNameLongerThanTenCharactersIsRejected() {
        String elevenCharName = "Alexanderrr";
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, elevenCharName, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS));
    }

    // ---- lastName: required, not null, max 10 characters ----

    @Test
    void testLastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, null, VALID_PHONE, VALID_ADDRESS));
    }

    @Test
    void testLastNameAtMaxLengthIsAccepted() {
        String tenCharName = "Washington";
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, tenCharName, VALID_PHONE, VALID_ADDRESS);
        assertEquals(tenCharName, contact.getLastName());
    }

    @Test
    void testLastNameLongerThanTenCharactersIsRejected() {
        String elevenCharName = "Washingtonn";
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, elevenCharName, VALID_PHONE, VALID_ADDRESS));
    }

    // ---- phone: required, not null, exactly 10 digits ----

    @Test
    void testPhoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, null, VALID_ADDRESS));
    }

    @Test
    void testPhoneShorterThanTenDigitsIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "123456789", VALID_ADDRESS));
    }

    @Test
    void testPhoneLongerThanTenDigitsIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "12345678901", VALID_ADDRESS));
    }

    @Test
    void testPhoneWithNonDigitCharactersIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "123-456-78", VALID_ADDRESS));
    }

    // ---- address: required, not null, max 30 characters ----

    @Test
    void testAddressCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, null));
    }

    @Test
    void testAddressAtMaxLengthIsAccepted() {
        String thirtyCharAddress = "123456789012345678901234567890"; // 30 characters
        assertEquals(30, thirtyCharAddress.length());
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, thirtyCharAddress);
        assertEquals(thirtyCharAddress, contact.getAddress());
    }

    @Test
    void testAddressLongerThanThirtyCharactersIsRejected() {
        String thirtyOneCharAddress = "1234567890123456789012345678901"; // 31 characters
        assertEquals(31, thirtyOneCharAddress.length());
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, thirtyOneCharAddress));
    }
}
