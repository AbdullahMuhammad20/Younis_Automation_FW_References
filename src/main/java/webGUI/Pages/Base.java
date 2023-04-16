package webGUI.Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Base
{
    protected WebDriver driver;

    private WebDriverWait wait;

    public static Properties properties = new Properties();

    public Base(WebDriver driver)
    {
        this.driver = driver;

        try
        {
            File fis = new File("src\\main\\resources\\config.properties");
            FileInputStream file = new FileInputStream(fis);
            properties.load(file);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            throw new Error("file Not Found Exception");
        }
        catch (IOException ioException)
        {
            throw new Error("IO Exception");
        }
    }

    public void waitingPresent(By element,int seconds)
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickOn(By element, int seconds)
    {
        waitingPresent(element,seconds);
        driver.findElement(element).click();
    }

    protected void sendValuesToElement(String value,WebElement element, int seconds)
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    public void scrollToElement(By element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }

    public void scrollDown()
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,300)");
    }

    public void launchBrowser()
    {

        if (properties.getProperty("browser").equalsIgnoreCase("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        else if (properties.getProperty("browser").equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(properties.getProperty("webGUI-URL"));
    }

    // Start to handle functions to get all elements and make sure if list of products is not empty and
    public void selectRandomElement(By elementList)
    {
        List<WebElement> products = driver.findElements(elementList);
        int index = 0;
        if (products.size() > 1)
        {
            // Get random product from list between 1 and products variable size
            Random random = new Random();
            index = random.nextInt(products.size() - 1);
            for (int i=0;i<products.size();i++)
            {
                if (i==index)
                {
                    products.get(i).click();
                }
            }
        }
        else if (products.size() <1)
        {
            System.out.println("list of Elements is empty");
        }
    }
}
