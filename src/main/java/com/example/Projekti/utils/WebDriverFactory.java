package com.example.Projekti.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    private static WebDriverFactory instance = new WebDriverFactory();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        WebDriver drv = (WebDriver) driver.get();
        if (null == drv) {
            driver.set(this.getBrowser());
        }

        return (WebDriver) driver.get();
    }

    public void quit() {
        WebDriver drv = (WebDriver) driver.get();
        if (null != drv) {
            drv.quit();
        }

        driver.remove();
    }

    public void closeWindow() {
        WebDriver drv = (WebDriver) driver.get();
        if (null != drv) {
            drv.close();
        }

    }

    private WebDriver getBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
        driver.manage().window().maximize();

        return driver;
    }
}
