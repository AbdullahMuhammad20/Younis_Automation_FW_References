import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import webGUI.Pages.Base;

public class BaseTest extends Base
{

    public BaseTest(WebDriver driver)
    {
        super(driver);
    }

    @BeforeSuite
    public void BeforeSuite()
    {

    }

    @BeforeTest
    public void BeforeTest()
    {

    }

    @BeforeClass
    public void BeforeClass()
    {

    }

    @BeforeMethod
    public void BeforeMethod()
    {

    }

    @AfterMethod
    public void AfterMethod()
    {

    }

    @AfterClass
    public void AfterClass()
    {

    }

    @AfterTest
    public void AfterTest()
    {

    }


    @AfterSuite
    public void AfterSuite()
    {

    }
}
