package webGUI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webGUI.locators.HomeLocators;

public class HomePage extends Base
{

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    HomeLocators loc = new HomeLocators();

    public void clickOnRegister()
    {
        clickOn(loc.registerLink,10);
    }


    public void clickOnLogin()
    {
        clickOn(loc.loginLink,10);
    }





}
