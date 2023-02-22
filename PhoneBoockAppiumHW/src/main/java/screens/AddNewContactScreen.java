package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidOptions;


import models.Contact;
import org.openqa.selenium.Alert;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(id="com.sheygam.contactapp:id/inputName")
    MobileElement nameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputLastName")
    MobileElement lastNameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputEmail")
    MobileElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPhone")
    MobileElement phoneEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputAddress")
    MobileElement addressEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputDesc")
    MobileElement descriptionEditText;
    @FindBy(id="com.sheygam.contactapp:id/createBtn") //create button
    MobileElement createButton;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;

    public AddNewContactScreen fillContactForm(Contact contact){
        should(nameEditText,10);
        type1(nameEditText,contact.getName());
        type1(lastNameEditText, contact.getLastName());
        type1(emailEditText, contact.getEmail());
        type1(phoneEditText, contact.getPhone());
        type1(addressEditText, contact.getAddress());
        type1(descriptionEditText, contact.getDescription());
        return this;
    }
    public ContactListScreen submitContactForm(){

        createButton.click();
        return new ContactListScreen(driver);
    }
    public AddNewContactScreen submitContactFormNegative(){

        createButton.click();
        return this;
    }

    public AddNewContactScreen isAnErrorMessage(String text){
        Alert alert = new WebDriverWait(driver,10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }

    public ContactListScreen navigateToContactList(){

         driver.navigate().back();

         return new ContactListScreen(driver);
    }
    public void pause(int time) throws InterruptedException {
        Thread.sleep(1000);

    }
}
