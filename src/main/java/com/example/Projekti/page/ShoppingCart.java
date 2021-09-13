package com.example.Projekti.page;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.Projekti.utils.WebDriverFactory;

import java.util.List;

public class ShoppingCart {

    private WebDriver driver=WebDriverFactory.getInstance().getDriver();
    private Actions actions=new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver() , 3);

    public ShoppingCart() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    public void IncrementQuantity(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(incrementQuantity));
        element.click();
    }

    public void DecrementQuantity(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(decrementQuantity));
        element.click();
    }

    public void UpdateQuantity(String s){
        switch (s){
            case "increment": IncrementQuantity();
                break;
            case "decrement": DecrementQuantity();
                break;
        }
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(updateFirst));
        element.click();
        wait.until(ExpectedConditions.visibilityOf(quantityUpdate));
        wait.until(ExpectedConditions.elementToBeClickable(closeQuantityUpdate));
        closeQuantityUpdate.click();
        checkPrices();
    }

    public void checkPrices(){
        double sum=0.0;
        String s;
        for(int i=0;i<prices.size();i++){
            s=prices.get(i).getText();
            s=s.substring(1,s.length());
            s=s.replace(",","");
            sum+=Double.parseDouble(s);
        }
        String tot=total.getText();
        tot=tot.substring(1,tot.length());
        tot=tot.replace(",","");
        double shuma=Double.parseDouble(tot);
        final double DELTA = 1e-15;
        Assert.assertEquals(shuma,sum,DELTA);
    }

    @FindBy(xpath="//table/tbody/tr/td/label[contains(text(),'Total')]/following-sibling::span")
    private static List<WebElement> prices;

    @FindBy(xpath = "//div[@class='cart-total']//span[contains(text(),'Total')]/span")
    private static WebElement total;

    @FindBy(xpath = "//table//tr[1]//button[@data-original-title='Remove']")
    private static WebElement deleteFirst;

    @FindBy(xpath = "//table//tr[1]//button[@class='btn-update']")
    private static WebElement updateFirst;

    @FindBy(xpath = "//table//tr[1]//button[@class='btn btn-number btn-plus']")
    private static WebElement incrementQuantity;

    @FindBy(xpath = "//table//tr[1]//button[@class='btn btn-number btn-minus']")
    private static WebElement decrementQuantity;

    @FindBy(xpath = "//div[@class='alert alert-success fade in' or contains(text(),'Quantity has been updated')]")
    private static WebElement quantityUpdate;

    @FindBy(xpath = "//div[@class='alert alert-success fade in' or contains(text(),'Quantity has been updated')]/a")
    private static WebElement closeQuantityUpdate;

}
