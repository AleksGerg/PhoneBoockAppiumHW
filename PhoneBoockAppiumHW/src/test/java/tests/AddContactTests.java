package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContactTests extends AppiumConfig {

    @BeforeMethod
    public void precondition(){
        new AuthenticationScreen(driver)
                // .fillLoginRegistrationForm(Auth.builder().email("john@gmail.com").password("Aa12345!").build())
                .fillLoginRegistrationForm(Auth.builder().email("ili@gmail.com").password("IliB$123456").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }

    @Test(enabled = false)
    public void addNewContactSuccess(){
        // if contacts>6 ---> delete all
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Bart")
                .lastName("Simpson")
                .email("bart"+i+"@mail.com")
                .phone("1234512345"+i)
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByNameLastName(contact.getName(),contact.getLastName())
                .isContactAddedByPhone(contact.getPhone())
                .logout();
    }

    @Test
    public void addNewContactEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Simpson")
                .email("bart@mail.com")
                .phone("123451234500")
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isAnErrorMessage("name=must not be blank")
                .navigateToContactList()
                .logout();



    }
    @Test (enabled = false) //HW 17.02(
    public void addNewContactEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Bart")
                .email("bart"+"@mail.com")
                .phone("1234512345")
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isAnErrorMessage("lastName=must not be blank");
                //.navigateToContactList();
              //  .logout();

    }

}
