package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class SocietyPage extends BasePage {

    // ─── Page header & navigation tabs ─────────────────────────────
    private final By pageHeader = By.cssSelector(".page-header h1");
    private final By mahallaTab = By.cssSelector("ul.navigation a[href='/uz/society']");
    private final By publicCouncilTab = By.cssSelector("ul.navigation a[href='/uz/society/public-council']");
    private final By meetingsTab = By.cssSelector("ul.navigation a[href='/uz/society/meetings']");
    private final By activeTab = By.cssSelector("ul.navigation a.styles_active__s6Q4K");

    // ─── Filters ────────────────────────────────────────────────────
    private final By districtTypeDropdown = By.id("react-select-district_type-input");
    private final By districtTypePlaceholder = By.id("react-select-district_type-placeholder");

    // ─── Mahalla cards ──────────────────────────────────────────────
    private final By mahallaCards = By.cssSelector(".styles_mahalla-card__Xc6GY");
    private final By mahallaNames = By.cssSelector(".styles_mahalla-card__Xc6GY strong");
    private final By mahallaCaptions = By.cssSelector(".styles_mahalla-card__Xc6GY .styles_caption__7yYNY");
    private final By mahallaPhoneLinks = By.cssSelector(".styles_mahalla-card__Xc6GY a[href^='tel:']");

    // ─── Pagination ─────────────────────────────────────────────────
    private final By paginationContainer = By.cssSelector("ul.rc-pagination");
    private final By paginationItems = By.cssSelector("li.rc-pagination-item");
    private final By activePaginationItem = By.cssSelector("li.rc-pagination-item-active a");
    private final By nextPageButton = By.cssSelector("li.rc-pagination-next button");
    private final By prevPageButton = By.cssSelector("li.rc-pagination-prev button");

    public SocietyPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.society.url"));
    }

    // =========== PAGE LEVEL GETTERS ===========

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/society");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }

    public String getActiveTabName() {
        return getText(activeTab);
    }

    public String getSelectedDistrictTypeValue() {
        return getText(districtTypePlaceholder);
    }

    // =========== TAB ACTIONS ===========

    public SocietyPage clickMahallaTab() {
        click(mahallaTab);
        return new SocietyPage(driver, jsUtil);
    }

    public SocietyPage clickPublicCouncilTab() {
        click(publicCouncilTab);
        return new SocietyPage(driver, jsUtil);
    }

    public SocietyPage clickMeetingsTab() {
        click(meetingsTab);
        return new SocietyPage(driver, jsUtil);
    }

    // =========== FILTERS ===========

    public void openDistrictTypeDropdown() {
        click(districtTypeDropdown);
    }

    // =========== MAHALLA CARD DATA ===========

    public int getMahallaCardCount() {
        return findAll(mahallaCards).size();
    }

    public List<String> getMahallaNames() {
        return findAll(mahallaNames).stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getMahallaCaptions() {
        return findAll(mahallaCaptions).stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .toList();
    }

    public List<String> getMahallaPhoneNumbers() {
        return findAll(mahallaPhoneLinks).stream()
                .map(WebElement::getText)
                .toList();
    }

    // =========== PAGINATION ===========

    public boolean isPaginationDisplayed() {
        return !findAll(paginationContainer).isEmpty();
    }

    public int getVisiblePaginationItemCount() {
        return findAll(paginationItems).size();
    }

    public String getActivePageNumber() {
        return getText(activePaginationItem);
    }

    public SocietyPage clickNextPage() {
        click(nextPageButton);
        return new SocietyPage(driver, jsUtil);
    }

    public SocietyPage clickPreviousPage() {
        click(prevPageButton);
        return new SocietyPage(driver, jsUtil);
    }

    public void clickPageNumber(String pageNumber) {
        By locator = By.xpath("//ul[contains(@class,'rc-pagination')]//a[normalize-space()='" + pageNumber + "']");
        click(locator);
    }
}
