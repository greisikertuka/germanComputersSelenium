package com.example.Projekti;

import com.example.Projekti.page.*;
import com.example.Projekti.utils.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTest {
    private WebDriver driver;
    private ChangeInformation changeInformation = new ChangeInformation();
    private HomePage homePage = new HomePage();
    private LoggedInPage loggedInPage = new LoggedInPage();
    private LoginPage loginPage = new LoginPage();
    private Product product = new Product();
    private RegisterPage registerPage = new RegisterPage();
    private Shop shop = new Shop();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private XiaomiPhones xiaomiPhones = new XiaomiPhones();
    private String email;
    private String password;
    private WebDriverWait wait;

    @BeforeAll
    public void setUp() {
        driver = WebDriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
        homePage.navigateToHomePage();
        wait = new WebDriverWait(driver, 4);
        email = "greisikertuka@gmail.com";
        password = "Letsgo123";
    }

    @AfterAll
    public void tearUp() {
        driver.quit();
    }

    @Order(1)
    @Test
    public void checkElements() throws InterruptedException {
        //Navigation buttons
        String[] s = {"Contact", "Compare(0)", "LogIn"};
        ArrayList<String> expectedResults = new ArrayList<>(Arrays.asList(s));
        wait.until(ExpectedConditions.visibilityOfAllElements(homePage.navbarLinks));
        List<WebElement> list = homePage.navbarLinks;
        ArrayList<String> actualResults = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            actualResults.add(list.get(i).getText().replace(" ", ""));
        }
        Assertions.assertEquals(actualResults, expectedResults);

        //Check categories
        actualResults = new ArrayList<>();

        wait.until(ExpectedConditions.visibilityOfAllElements(homePage.navbarLinks));
        list = homePage.categories;

        for (int i = 0; i < list.size(); i++) {
            actualResults.add(list.get(i).getText().replace(" ", ""));
        }

        String[] str = {"Telefona", "Tableta", "Laptope", "Kompjutera", "Monitore&Projektore",
                "Periferike", "Hardware", "Printer&POS", "Networking"};

        expectedResults = new ArrayList<>(Arrays.asList(str));

        System.out.println(actualResults);
        System.out.println(expectedResults);

        for (int i = 0; i < expectedResults.size(); i++) {
            Assertions.assertEquals(expectedResults.get(i), actualResults.get(i));
        }

        Assertions.assertEquals(expectedResults.size(), actualResults.size() - 2);

    }


    @Order(2)
    @Test
    public void register() {
        homePage.clickButton("Log in");
        loginPage.registerButton.click();
        registerPage.sendKeystoInputForm("Greisi", "First Name");
        registerPage.sendKeystoInputForm("Kertuka", "Last Name");
        registerPage.sendKeystoInputForm(email, "Email");
        registerPage.sendKeystoInputForm(password, "Password");
        registerPage.sendKeystoInputForm(password, "Confirm Password");
        registerPage.clickButton("Accept policy");
        registerPage.clickButton("Register");
        registerPage.checkRegistrationSuccess();
    }

    @Order(3)
    @Test
    public void login() {
        homePage.clickButton("Log in");
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.loginButton.click();
        loginPage.checkLoginSuccess();
        String[] s = {"Greisi", "Kertuka", email};
        ArrayList<String> expectedResults = new ArrayList<>(Arrays.asList(s));
        ArrayList<String> actualResults = loggedInPage.returnData();
        Assertions.assertEquals(actualResults, expectedResults);
        loggedInPage.logoutButton.click();
    }


    //Change login data
    @Order(4)
    @Test
    public void changeLoginData() {
        homePage.clickButton("Log in");
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.loginButton.click();
        String[] s1 = {"Wout", "Weghorst", "433@gmail.com"};
        List<String> list1 = Arrays.asList(s1);
        loggedInPage.changeData();
        changeInformation.changeData(list1);
        String[] s2 = {"Greisi", "Kertuka", email};
        List<String> list2 = Arrays.asList(s2);
        loggedInPage.changeData();
        changeInformation.changeData(list2);
        loggedInPage.logoutButton.click();
    }

    @Order(5)
    @Test
    public void checkShoppingCartElements() {
        //Kontrollojme nqs ruhen sic duhet elementet qe shtojme te shopping cart
        homePage.hover("Telefona");
        homePage.clickSubCategory("Xiaomi");
        xiaomiPhones.addToCart(1);
        xiaomiPhones.addToCart(2);
        xiaomiPhones.checkCounter();
        //Kontrolli nqs shfaqen butonat te Shopping cart
        xiaomiPhones.hoverToShoppingCart();
        xiaomiPhones.checkButtons();
    }

    @Order(6)
    @Test
    public void shoppingCartActions() {
        //Check Shopping Cart Price
        xiaomiPhones.goToXiaomiPhonesPage();
        xiaomiPhones.addToCart(1);
        xiaomiPhones.hoverToShoppingCart();
        xiaomiPhones.ViewShoppingCart();
        shoppingCart.checkPrices();
        xiaomiPhones.goToXiaomiPhonesPage();
        xiaomiPhones.hoverToShoppingCart();
        xiaomiPhones.ViewShoppingCart();
        shoppingCart.UpdateQuantity("increment");
    }

    @Order(7)
    @Test
    public void feedback() {
        homePage.clickButton("Dyqani");
        shop.clickFirstItem();
        product.reviewsButton.click();
        product.sendKeystoInputForm("Greisi", "Name");
        product.sendKeystoInputForm("Produkt i shkelqyer", "Comment");
        product.addReviewButton.click();
        product.reviewSuccess();
    }

}
