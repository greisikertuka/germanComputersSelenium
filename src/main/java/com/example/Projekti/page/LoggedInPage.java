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

import java.util.ArrayList;
import java.util.List;

public class LoggedInPage {

    private WebDriver driver=WebDriverFactory.getInstance().getDriver();
    private Actions actions=new Actions(driver);
    private static WebDriverWait wait = new WebDriverWait(WebDriverFactory.getInstance().getDriver() , 3);
    public LoggedInPage() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverFactory.getInstance().getDriver()), this);
    }

    public ArrayList<String> returnData(){
        String[]s =data.get(0).getText().split(" ");
        ArrayList<String> arr=new ArrayList<String>();
        arr.add(s[0]);
        arr.add(s[1]);
        arr.add(data.get(1).getText());
        return arr;
    }

    public void changeData(){
         wait.until(ExpectedConditions.elementToBeClickable(edit));
         edit.click();
    }

    @FindBy(xpath = "//div[@class='sidebar-menu']//a[@href='https://www.germancomputers.al/logout']")
    public static WebElement logoutButton;

    @FindBy(css = ".contact-information span")
    public static List<WebElement> data;

    @FindBy(xpath = "//div[@class='account-information clearfix']//a[@href='https://www.germancomputers.al/account/profile']")
    public static WebElement edit;

}
