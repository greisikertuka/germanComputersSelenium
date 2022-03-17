package com.example.Projekti.page;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.Projekti.utils.WebDriverFactory;

import java.util.ArrayList;

public class XiaomiPhones {

    private WebDriver driver = WebDriverFactory.getInstance().getDriver();
    private Actions actions = new Actions(driver);
    private int counter = 0;
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver(), 3);

    public XiaomiPhones() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    public void goToXiaomiPhonesPage() {
        driver.get("https://www.germancomputers.al/products?category=xiaomi");
    }

    public void hover(int index) {
        String xpath = "//div[@class='grid-products separator']/a[" + index + "]";
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        actions.moveToElement(element).perform();
    }

    public void hoverToShoppingCart() {
        WebElement shoppingCardButton = wait.until(ExpectedConditions.visibilityOf(shoppingCart));
        actions.moveToElement(shoppingCardButton).perform();
    }

    public void ViewShoppingCart() {
        viewShoppingCart.click();
    }

    public void checkButtons() {
        ArrayList<WebElement> arr = new ArrayList<>(driver.findElements(By.xpath("//div[@class='mini-cart-buttons text-center']/a")));
        Assert.assertEquals(arr.get(0).getText().contains("View Cart"), true);
        Assert.assertEquals(arr.get(1).getText().contains("Checkout"), true);
    }

    public void addToCart(int index) {
        hover(index);
        String xpath = "//div[@class='grid-products separator']/a[" + index + "]//button[contains(text(),'Add to Cart')]";
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success fade in']")));
        counter++;
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='alert alert-success fade in']/a"))));
        driver.findElement(By.xpath("//div[@class='alert alert-success fade in']/a")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='alert alert-success fade in']")));
    }

    public void checkCounter() {
        String s = driver.findElement(By.cssSelector(".cart-count")).getText();
        int n = Integer.parseInt(s);
        Assert.assertEquals(n, counter);
    }

    @FindBy(xpath = "//span[@class='cart-label']/../..")
    private static WebElement shoppingCart;

    @FindBy(xpath = "//a[@class='btn btn-primary btn-view-cart']")
    private static WebElement viewShoppingCart;

}
