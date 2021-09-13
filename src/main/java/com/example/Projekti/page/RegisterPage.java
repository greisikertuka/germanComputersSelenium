package com.example.Projekti.page;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.Projekti.utils.WebDriverFactory;

public class RegisterPage {

    private WebDriver driver=WebDriverFactory.getInstance().getDriver();
    private Actions actions=new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver() , 3);

    public RegisterPage() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    @FindBy(xpath = "//button[contains(text(),'Register')]")
    public static WebElement registerButton;

    @FindBy(name = "first_name")
    public static WebElement firstName;

    @FindBy(name = "last_name")
    public static WebElement lastName;

    @FindBy(name = "email")
    public static WebElement email;

    @FindBy(name = "password")
    public static WebElement password;

    @FindBy(name = "password_confirmation")
    public static WebElement passwordConfirm;

    @FindBy(css = "[for='privacy']")
    public static WebElement privacyTerms;

    public void sendKeysFirstName(String s){
        firstName.sendKeys(s);
    }

    public void sendKeysLastName(String s){
        lastName.sendKeys(s);
    }

    public void sendKeysEmail(String s){
        email.sendKeys(s);
    }

    public void sendKeysPassword(String s){
        password.sendKeys(s);
    }

    public void sendKeysConfirmPassword(String s){
        passwordConfirm.sendKeys(s);
    }

    public void sendKeystoInputForm(String keys,String input){
        switch (input){
            case "First Name":sendKeysFirstName(keys);
            break;
            case "Last Name":sendKeysLastName(keys);
                break;
            case "Email":sendKeysEmail(keys);
                break;
            case "Password":sendKeysPassword(keys);
                break;
            case "Confirm Password":sendKeysConfirmPassword(keys);
                break;
        }
    }

    public void clickButton(String button){
        switch (button){
            case "Accept policy":
                wait.until(ExpectedConditions.elementToBeClickable(privacyTerms));
                privacyTerms.click();
                break;
            case "Register":
                wait.until(ExpectedConditions.elementToBeClickable(registerButton));
                registerButton.click();
                break;
        }
    }

    public void checkRegistrationSuccess(){
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success fade in']")));
    }
}
