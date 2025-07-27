package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    By addToCart = By.xpath("//span[text()='Add to cart']");

    public void addToCart() {
        driver.findElement(addToCart).click();
    }
    public void verifyCartItemCount() {
        WebElement cartBadge = driver.findElement(By.xpath("//span[@class='badge gh-badge']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        String itemCount = cartBadge.getText();  // Should be "1"

        if (itemCount.contains("1")) {
            System.out.println(" Test Passed: Shopping cart contains 1 item.");
        } else {
            System.out.println(" Test Failed: Expected 1 item but found " + itemCount);
        }
    }
}
