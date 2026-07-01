package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.JavaScriptUtil;
import util.WaitUtils;

public class ServicesPage extends BasePage {
    private final By pageHeader = By.xpath("//h2[text()='Xizmatlar']");

    public ServicesPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.services.url"));
    }

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/services");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }

}
