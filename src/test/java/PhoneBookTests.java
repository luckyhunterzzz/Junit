import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PhoneBookTests {
    PhoneBook sut;
    String group = "focusGroup";
    String phoneNumber = "8-888-888-88-88";
    String name = "Лена";

    @BeforeEach
    public void init() {
        sut = new PhoneBook();
    }

    @Test
    public void testAddGroup() {
        sut.addGroup(group);

        assertThat(sut.phoneBook, hasKey(group));
        assertThat(sut.phoneBook, hasEntry(group, new ArrayList<>()));
    }

    @Test
    public void testAddContactToGroup() {
        Contact contact = new Contact("8-888-888-88-88", "Лена");
        List<Contact> list = new ArrayList<>();
        list.add(contact);

        sut.addGroup(group);
        sut.addContactToGroup(contact, group);

        assertThat(sut.phoneBook, hasEntry(group, list));
    }

    @Test
    public void testAddContactToGroup_ContactExists() {
        Contact contact = new Contact("8-888-888-88-88", "Лена");
        List<Contact> list = new ArrayList<>();
        list.add(contact);

        sut.addGroup(group);
        sut.addContactToGroup(contact, group);
        sut.addContactToGroup(contact, group);//Попытка добавления уже существующего контакта

        assertThat(sut.phoneBook, hasEntry(group, list));
    }

    @Test
    public void testOrderGroup() {
        String group2 = "testGroup";
        sut.addGroup(group);
        sut.addGroup(group2);

        String expected = "Список групп:\n" + group + "\n" + group2 + "\n";

        assertThat(expected, equalTo(sut.orderGroup()));
    }

    @Test
    public void testGetContactFromGroup() {
        Contact contact1 = new Contact("8-888-888-88-88", "Лена");
        Contact contact2 = new Contact("8-444-444-44-44", "Петя");
        sut.addGroup(group);
        sut.addContactToGroup(contact1, group);
        sut.addContactToGroup(contact2, group);
        String expected = "Список контактов в группе " + group + ":\n"
                + "Лена 8-888-888-88-88\n"
                + "Петя 8-444-444-44-44\n";

        assertThat(expected, equalTo(sut.getContactsFromGroup(group)));
    }

    @Test
    public void testGetContactsFromGroup_isEmpty() {
        sut.addGroup(group);
        String expected = "Список контактов в группе focusGroup:\n" + "Список пуст\n";

        assertThat(expected, equalTo(sut.getContactsFromGroup(group)));
    }

    @Test
    public void testFindContactOnGroup() {
        Contact contact = new Contact(phoneNumber, name);
        sut.addGroup(group);
        sut.addContactToGroup(contact, group);
        String expected = "Контакт " + name + " есть в группе " + group;

        assertThat(expected, equalTo(sut.findContactOnGroup(name, group)));
    }

    @Test
    public void testFindContactOnGroup_isNot() {
        String name2 = "Петя";
        Contact contact = new Contact(phoneNumber, name);
        sut.addGroup(group);
        sut.addContactToGroup(contact, group);
        String expected = "Контакта " + name2 + " нет в группе " + group;

        assertThat(expected, equalTo(sut.findContactOnGroup(name2, group)));
    }

    @Test // №4
    public void testFindContactByNumber() {
        Contact contact = new Contact(phoneNumber, name);
        sut.addGroup(group);
        sut.addContactToGroup(contact, group);

        assertThat(sut.findContactByNumber(phoneNumber), hasToString(phoneNumber + " " + name));
    }

    @Test
    public void testFindContactByNumber_isNot() {
        String phoneNumberTest = "8-444-444-44-44";
        Contact contact = new Contact(phoneNumber, name);
        sut.addGroup(group);
        sut.addContactToGroup(contact, group);
        String expected = "Контакт по номеру " + phoneNumberTest + " не найден";

        assertThat(expected, equalTo(sut.findContactByNumber(phoneNumberTest)));
    }
}
