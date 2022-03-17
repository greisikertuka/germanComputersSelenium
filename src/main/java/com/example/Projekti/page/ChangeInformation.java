package com.example.Projekti.page;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.Projekti.utils.WebDriverFactory;

import java.util.List;

public class ChangeInformation {

    private WebDriver driver=WebDriverFactory.getInstance().getDriver();
    private Actions actions=new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver() , 3);
    public ChangeInformation() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    public void changeData(List<String> list){
         firstName.clear();
         firstName.sendKeys(list.get(0));
         lastName.clear();
         lastName.sendKeys(list.get(1));
         submit.click();
         driver.get("https://www.germancomputers.al/account");
    }

    @FindBy(name = "first_name")
    public static WebElement firstName;

    @FindBy(name = "last_name")
    public static WebElement lastName;

    @FindBy(name = "email")
    public static WebElement email;

    @FindBy(xpath = "//button[contains(text(),'Save Changes')]")
    public static WebElement submit;

    @FindBy(xpath = "//div[@class='account-information clearfix']//a[@href='https://www.germancomputers.al/account/profile']")
    public static WebElement edit;

}
