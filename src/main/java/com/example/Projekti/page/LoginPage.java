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

public class LoginPage {

    private WebDriver driver = WebDriverFactory.getInstance().getDriver();
    private Actions actions = new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), 3);

    public LoginPage() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    public void navigateToLoginPage() {
        driver.navigate().to("https://www.germancomputers.al/login");
    }

    public void sendKeysEmail(String s) {
        email.sendKeys(s);
    }

    public void sendKeysPassword(String s) {
        password.sendKeys(s);
    }

    public void sendKeystoInputForm(String keys, String input) {
        switch (input) {
            case "Email":
                sendKeysEmail(keys);
                break;
            case "Password":
                sendKeysPassword(keys);
                break;
        }
    }

    public void checkLoginSuccess() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='top-nav']//*[text()='My Account']")));
    }

    @FindBy(css = ".register")
    public static WebElement registerButton;

    @FindBy(name = "email")
    public static WebElement email;

    @FindBy(name = "password")
    public static WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    public static WebElement loginButton;

    @FindBy(css = "label[for='remember']")
    public static WebElement remember;
}
