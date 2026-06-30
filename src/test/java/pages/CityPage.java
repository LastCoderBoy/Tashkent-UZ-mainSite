package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class CityPage extends BasePage {

    // ─── Page header & navigation tabs ─────────────────────────────
    private final By pageHeader = By.cssSelector(".page-header h1");
    private final By generalInfoTab = By.cssSelector("ul.navigation.small a[href='/uz/city']");
    private final By salomTashkentTab = By.cssSelector("ul.navigation.small a[href='/uz/city/salom-tashkent']");
    private final By districtsTab = By.cssSelector("ul.navigation.small a[href='/uz/city/districts']");
    private final By activeTab = By.cssSelector("ul.navigation.small a.styles_active__s6Q4K");

    // ─── Main content ───────────────────────────────────────────────
    private final By descriptionBlock = By.cssSelector(".styles_description__1gypI");
    private final By descriptionParagraphs = By.cssSelector(".styles_description__1gypI p");
    private final By cityImage = By.cssSelector(".styles_description__1gypI img");

    public CityPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, "/uz/city");
    }

    // =========== PAGE LEVEL GETTERS ===========

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageHeaderText() {
        return getText(pageHeader);
    }

    public String getActiveTabName() {
        return getText(activeTab);
    }

    // =========== TAB ACTIONS ===========

    public CityPage clickGeneralInfoTab() {
        click(generalInfoTab);
        return new CityPage(driver, jsUtil);
    }

    public CityPage clickSalomTashkentTab() {
        click(salomTashkentTab);
        return new CityPage(driver, jsUtil);
    }

    public CityPage clickDistrictsTab() {
        click(districtsTab);
        return new CityPage(driver, jsUtil);
    }

    // =========== CONTENT GETTERS ===========

    public boolean isDescriptionBlockDisplayed() {
        return !findAll(descriptionBlock).isEmpty();
    }

    public List<String> getDescriptionParagraphs() {
        return findAll(descriptionParagraphs).stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .toList();
    }

    public String getDescriptionText() {
        return String.join(" ", getDescriptionParagraphs());
    }

    public boolean isCityImageDisplayed() {
        return !findAll(cityImage).isEmpty();
    }
}
