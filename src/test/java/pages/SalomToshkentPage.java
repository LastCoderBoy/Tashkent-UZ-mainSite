package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.JavaScriptUtil;
import util.WaitUtils;

public class SalomToshkentPage extends BasePage {

    private final By pageHeader = By.xpath("//h1/span[text()='Shahar']");
    private final By activeTab = By.cssSelector("ul.navigation.small a.styles_active__s6Q4K");

    public SalomToshkentPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.salom.url"));
    }

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/city/salom-tashkent");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }

    public String getActiveTabName() {
        return getText(activeTab);
    }
}
