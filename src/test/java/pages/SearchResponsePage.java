package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.primary.MainPage;
import util.JavaScriptUtil;

public class SearchResponsePage extends BasePage {

    private final By siteLogo = By.xpath("//img[@alt='Toshkent shahar hokimiyati rasmiy sayti']");
    private final By totalSearchResults = By.xpath("//span[contains(text(), 'natija topildi')]");


    public SearchResponsePage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
    }

    public String getTotalSearchResultsText() {
        return getText(totalSearchResults);
    }

    public int getTotalSearchResults() {
        String totalResultsText = getTotalSearchResultsText();
        String[] parts = totalResultsText.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public MainPage clickSiteLogo() {
        jsUtil.scrollToElementJS(siteLogo);
        click(siteLogo);
        return new MainPage(driver, jsUtil);
    }
}
