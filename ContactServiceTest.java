import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Verifies that the ContactService class meets all three contact service
 * requirements: adding contacts with a unique ID, deleting contacts per
 * contact ID, and updating a contact's firstName, lastName, phone, and
 * address fields per contact ID.
 */
class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    // ---- add ----

    @Test
    void testAddContactStoresContactByUniqueId() {
        Contact contact = new Contact("1111111111", "John", "Smith", "1234567890", "123 Main Street");

        service.addContact(contact);

        assertEquals(contact, service.getContact("1111111111"));
    }

    @Test
    void testAddContactWithDuplicateIdIsRejected() {
        Contact original = new Contact("1111111111", "John", "Smith", "1234567890", "123 Main Street");
        Contact duplicateId = new Contact("1111111111", "Jane", "Doe", "0987654321", "456 Oak Avenue");
        service.addContact(original);

        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicateId));
        // the original contact should be untouched
        assertEquals("John", service.getContact("1111111111").getFirstName());
    }

    @Test
    void testAddNullContactIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    // ---- delete ----

    @Test
    void testDeleteContactRemovesItById() {
        Contact contact = new Contact("2222222222", "Alice", "Jones", "5555555555", "789 Pine Road");
        service.addContact(contact);

        service.deleteContact("2222222222");

        assertNull(service.getContact("2222222222"));
    }

    @Test
    void testDeleteContactWithUnknownIdIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("0000000000"));
    }

    // ---- update ----

    @Test
    void testUpdateFirstNameById() {
        Contact contact = new Contact("3333333333", "Bob", "Brown", "1112223333", "111 Elm Street");
        service.addContact(contact);

        service.updateContact("3333333333", "Robert", null, null, null);

        Contact updated = service.getContact("3333333333");
        assertEquals("Robert", updated.getFirstName());
        assertEquals("Brown", updated.getLastName());
        assertEquals("1112223333", updated.getPhone());
        assertEquals("111 Elm Street", updated.getAddress());
    }

    @Test
    void testUpdateLastNameById() {
        Contact contact = new Contact("4444444444", "Carol", "White", "2223334444", "222 Maple Street");
        service.addContact(contact);

        service.updateContact("4444444444", null, "Black", null, null);

        assertEquals("Black", service.getContact("4444444444").getLastName());
    }

    @Test
    void testUpdatePhoneById() {
        Contact contact = new Contact("5555555555", "Dave", "Green", "3334445555", "333 Cedar Street");
        service.addContact(contact);

        service.updateContact("5555555555", null, null, "9998887777", null);

        assertEquals("9998887777", service.getContact("5555555555").getPhone());
    }

    @Test
    void testUpdateAddressById() {
        Contact contact = new Contact("6666666666", "Eve", "Adams", "4445556666", "444 Birch Street");
        service.addContact(contact);

        service.updateContact("6666666666", null, null, null, "555 Spruce Avenue");

        assertEquals("555 Spruce Avenue", service.getContact("6666666666").getAddress());
    }

    @Test
    void testUpdateAllFieldsAtOnce() {
        Contact contact = new Contact("7777777777", "Frank", "Miller", "6667778888", "666 Willow Street");
        service.addContact(contact);

        service.updateContact("7777777777", "Francis", "Millerson", "1231231234", "777 Aspen Court");

        Contact updated = service.getContact("7777777777");
        assertEquals("Francis", updated.getFirstName());
        assertEquals("Millerson", updated.getLastName());
        assertEquals("1231231234", updated.getPhone());
        assertEquals("777 Aspen Court", updated.getAddress());
        // the contact ID must never change
        assertEquals("7777777777", updated.getContactId());
    }

    @Test
    void testUpdateContactWithUnknownIdIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                service.updateContact("9999999999", "Test", null, null, null));
    }

    @Test
    void testUpdateWithInvalidFieldValueIsRejected() {
        Contact contact = new Contact("8888888888", "Grace", "Hall", "1212121212", "888 Poplar Street");
        service.addContact(contact);

        // "ThisNameIsWayTooLong" is 20 characters, which violates the firstName
        // length requirement and should be rejected without changing the contact.
        assertThrows(IllegalArgumentException.class, () ->
                service.updateContact("8888888888", "ThisNameIsWayTooLong", null, null, null));
        assertEquals("Grace", service.getContact("8888888888").getFirstName());
    }
}
