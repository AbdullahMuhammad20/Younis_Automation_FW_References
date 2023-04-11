package webGUI.Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import java.util.Properties;
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
            File fis = new File("src\\main\\java\\com\\task\\properties\\config.properties");
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

    public void waitingPresent(By element)
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
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
}
