package Ignite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GutenbergPages {


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://gutenberg.org/");
        System.out.println(driver.getTitle());

        //Search By quick search
        WebElement menu = driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[2]/a"));
        Actions action = new Actions(driver);
        action.moveToElement(menu).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath(" //a[contains(text(),'Bookshelves')]")).click();
        List<WebElement> Category = driver.findElements(By.tagName("a"));
        System.out.println("Total Category---:" + Category.size());
        for (WebElement a : Category) {
            System.out.println(a.getText());

        }

        driver.findElement(By.linkText("Animal")).click();
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);

        //We will execute the  search

        WebElement searchfield = driver.findElement(By.id("menu-book-search"));
        searchfield.sendKeys("OSCAR");
        ClickOn(driver, driver.findElement(By.name("submit_search")), 40);
        List<WebElement> suggetion = driver.findElements(By.tagName("a"));
        System.out.println("Total related search------:" + suggetion.size());
        for (WebElement a : suggetion) {
            System.out.println(a.getText());
        }

    }

    public static void ClickOn(WebDriver driver, WebElement locator, int timeout) {
        new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
        locator.click();
    }
}
