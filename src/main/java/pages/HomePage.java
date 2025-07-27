package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    By searchBox = By.xpath("//input[@id='gh-ac']");
    By searchBtn = By.xpath("//span[text()='Search']");
    public void searchItem(String item) {
        driver.findElement(searchBox).sendKeys(item);
        driver.findElement(searchBtn).click();
    }

}
