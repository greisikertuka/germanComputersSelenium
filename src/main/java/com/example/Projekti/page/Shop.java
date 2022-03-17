package com.example.Projekti.page;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.Projekti.utils.WebDriverFactory;

public class Shop {

    private WebDriver driver = WebDriverFactory.getInstance().getDriver();
    private Actions actions = new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), 3);

    public Shop() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    public void clickFirstItem() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(firstItem));
        element.click();
    }

    @FindBy(xpath = "//div[@class='grid-products separator']/a[1]")
    public static WebElement firstItem;

}
