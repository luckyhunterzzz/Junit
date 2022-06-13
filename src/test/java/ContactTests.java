import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContactTests {
    String name = "Лена";
    String phoneNumber = "8-888-888-88-88";

    @Test // №5
    public void testEquals() {
        String name2 = "Петя";
        String phoneNumber2 = "8-888-888-88-88";
        Contact contact1 = new Contact(phoneNumber, name);
        Contact contact2 = new Contact(phoneNumber2, name2);

        boolean result = contact1.equals(contact2);

        assertThat(true, is(result));
    }

    @Test
    public void testToString() {
        Contact contact = new Contact(phoneNumber, name);

        String result = contact.toString();

        assertThat(result, hasToString(name + " " + phoneNumber));
    }
}