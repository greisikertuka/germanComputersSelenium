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

import java.util.List;

public class HomePage {

    private WebDriver driver = WebDriverFactory.getInstance().getDriver();
    private Actions actions = new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), 3);

    public HomePage() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    public void navigateToHomePage() {
        driver.navigate().to("https://www.germancomputers.al/");
    }

    public void hover(String s) {
        String s1 = "//ul[@class='dropdown-menu vertical-mega-menu']//*[contains(text(),'Telefona')]";
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s1)));
        actions.moveToElement(element).perform();
    }

    public void clickSubCategory(String subCategory) {
        String s = "//ul[@class='dropdown-menu vertical-mega-menu']//*[contains(text(),'" + subCategory + "')]";
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s)));
        element.click();
    }

    public void clickButton(String button) {
        switch (button) {
            case "Log in":
                wait.until(ExpectedConditions.elementToBeClickable(login));
                login.click();
                break;
            case "Dyqani":
                wait.until(ExpectedConditions.elementToBeClickable(dyqaniButton));
                dyqaniButton.click();
                break;
        }
    }


    @FindBy(xpath = "//*[@class='top-nav-right pull-right']/ul/li/a")
    public static List<WebElement> navbarLinks;

    @FindBy(xpath = "//a[@href='https://www.germancomputers.al/contact']")
    public static WebElement contact;

    @FindBy(xpath = "//a[@href='https://www.germancomputers.al/compare']")
    public static WebElement compare;

    @FindBy(xpath = "//a[@href='https://www.germancomputers.al/login']")
    public static WebElement login;

    @FindBy(xpath = "//*[@class='dropdown-menu vertical-mega-menu']/li/a")
    public static List<WebElement> categories;

    @FindBy(xpath = "//div[@class='user-cart pull-right']")
    public static WebElement shoppingCart;

    @FindBy(xpath = "//div[@class='search-box hidden-sm hidden-xs']/input")
    public static WebElement searchInput;

    @FindBy(xpath = "//div[@class='search-box hidden-sm hidden-xs']//button")
    public static WebElement searchButton;

    @FindBy(xpath = "//a[@href='https://www.germancomputers.al/products']")
    public static WebElement dyqaniButton;

}
