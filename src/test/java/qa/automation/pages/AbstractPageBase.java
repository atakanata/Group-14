package qa.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.automation.utilities.BrowserUtils;
import qa.automation.utilities.Driver;

public abstract class AbstractPageBase extends HomePage{
    protected WebDriver driver = Driver.getDriver();

    protected WebDriverWait wait = new WebDriverWait(driver,15);

    @FindBy(css="#user-menu > a")
    protected WebElement currentUser;

    @FindBy(xpath = "//div[contains(text(),'You do not have permission to perform this action.')]")
    protected WebElement accessDeniedMessage;

    public WebElement getAccessDeniedMessage(){
        return accessDeniedMessage;
    }

    @FindBy(className = "oro-subtitle")
    protected WebElement menuHeader;

    public WebElement getMenuHeader(){
        return menuHeader;
    }



    public String getCurrentUserName(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }

    @FindBy(tagName ="h1" )
    protected WebElement pageLogo;

    public String getPageLogo(){
        return pageLogo.getText();
    }


    public AbstractPageBase(){
        PageFactory.initElements(driver,this);
    }

    public void navigateTo(String tabName, String moduleName){
        String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'"+tabName+"')]";
        String moduleXpath = "//span[@class='title title-level-2' and text()='" + moduleName + "']";


        WebElement tabElement = driver.findElement(By.xpath(tabNameXpath));
        WebElement moduleElement = driver.findElement(By.xpath(moduleXpath));

        Actions actions = new Actions(driver);
        BrowserUtils.wait(4);
        actions.moveToElement(tabElement).
                pause(2000).
                click(moduleElement).
                build().perform();

        BrowserUtils.wait(4);
    }
}

