package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.JavaScriptUtil;
import util.WaitUtils;

public class TashkentListeningPage extends BasePage {

    private final By pageHeader = By.xpath("//h1/span[text()='Toshkent tinglaydi']");

    public TashkentListeningPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.listening.url"));
    }

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/news/tashkent-listening");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }
}
