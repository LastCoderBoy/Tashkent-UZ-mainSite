package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.JavaScriptUtil;
import util.WaitUtils;

public class UsefulLinksPage extends BasePage {

    private final By pageHeader = By.xpath("//h1/span[text()='Foydali havolalar']");

    public UsefulLinksPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.useful-links.url"));
    }

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/useful-links");
    }
    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }

}
