package com.example.Projekti.page;

import com.example.Projekti.utils.WebDriverFactory;
import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product {

    private WebDriver driver=WebDriverFactory.getInstance().getDriver();
    private Actions actions=new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver() , 3);

    public Product() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    @FindBy(xpath = "//a[@href='#reviews']")
    public static WebElement reviewsButton;

    @FindBy(xpath = "//div[@class='review-form clearfix']//*[@class='rating']/label[1]")
    public static WebElement fiveStarReview;

    @FindBy(xpath = "//button[contains(text(),'Add Review')]")
    public static WebElement addReviewButton;

    @FindBy(name = "reviewer_name")
    public static WebElement name;

    @FindBy(name = "comment")
    public static WebElement comment;

    public void sendKeysName(String s){
        name.sendKeys(s);
    }

    public void sendKeysComment(String s){
        comment.sendKeys(s);
    }

    public void sendKeystoInputForm(String keys,String input){
        switch (input){
            case "Name":sendKeysName(keys);
            break;
            case "Comment":sendKeysComment(keys);
                break;
        }
    }
    public void reviewSuccess(){
        //WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='alert alert-success fade in']")));
    }
}
