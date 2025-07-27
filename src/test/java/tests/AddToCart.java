package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;

public class AddToCart {

    WebDriver driver;
    HomePage home;
    SearchResultsPage results;
    ProductPage product;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com");
        home = new HomePage(driver);
        results = new SearchResultsPage(driver);
        product = new ProductPage(driver);
    }

    @Test
    public void verifyAddToCart() {
        home.searchItem("book");
        results.clickFirstItem();
        String parentWindow = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }

        product.addToCart();

        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        By closeDialogBox= By.xpath("//button[@class='icon-btn lightbox-dialog__close' and @aria-label='Close overlay']");

        driver.findElement(closeDialogBox).click();

        product.verifyCartItemCount();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
