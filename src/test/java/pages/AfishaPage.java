package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.JavaScriptUtil;
import util.WaitUtils;

public class AfishaPage extends BasePage {

    private final By pageHeader = By.xpath("//h1/span[text()='Toshkent afishasi']");

    public AfishaPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.afisha.url"));
    }

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/afisha");
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }
}
