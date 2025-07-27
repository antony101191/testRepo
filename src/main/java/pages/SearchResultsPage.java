package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {
        WebDriver driver;
        public SearchResultsPage(WebDriver driver) {
            this.driver = driver;
        }
        By firstItem = By.xpath("//ul[@class='srp-results srp-list clearfix'][1]/li[1]/descendant::a[1]/following::img[1]");
        public void clickFirstItem() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(firstItem)).click();
        }
    }

