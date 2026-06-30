package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class NewsPage extends BasePage {

    // ─── Page header & sub-navigation tabs ─────────────────────────────
    private final By pageHeader = By.cssSelector("h1");
    private final By cityNewsTab = By.cssSelector("a[href='/uz/news']");
    private final By districtNewsTab = By.cssSelector("a[href='/uz/news/district-news']");
    private final By emergencyNewsTab = By.cssSelector("a[href='/uz/news/emergency-news']");
    private final By activeNewsTab = By.cssSelector("ul.styles_news-navigation__hP9oY a.styles_active__s6Q4K");

    // ─── Filters ────────────────────────────────────────────────────
    private final By datePickerInput = By.cssSelector(".datepicker input.main");
    private final By districtFilterDropdown = By.id("react-select-district_id-input");
    private final By selectedDistrictValue = By.cssSelector(".css-1qgtt0i-singleValue");

    // ─── News cards ─────────────────────────────────────────────────
    private final By newsCards = By.cssSelector(".styles_news-card__yDQOK");
    private final By newsCardTitles = By.cssSelector(".styles_title__sKnEi");
    private final By newsCardDates = By.cssSelector(".styles_tag___PmBo");
    private final By newsCardLinks = By.cssSelector("a.styles_img__2BVhZ");

    // ─── Pagination ─────────────────────────────────────────────────
    private final By paginationContainer = By.cssSelector("ul.rc-pagination");
    private final By paginationItems = By.cssSelector("li.rc-pagination-item");
    private final By activePaginationItem = By.cssSelector("li.rc-pagination-item-active a");
    private final By nextPageButton = By.cssSelector("li.rc-pagination-next button");
    private final By prevPageButton = By.cssSelector("li.rc-pagination-prev button");

    public NewsPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, "/uz/news");
    }

    // =========== PAGE LEVEL GETTERS ===========

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageHeaderText() {
        return getText(pageHeader);
    }

    public String getActiveTabName() {
        return getText(activeNewsTab);
    }

    // Sub-navigation actions (each returns this page, content reloads)

    public NewsPage clickCityNewsTab() {
        click(cityNewsTab);
        return new NewsPage(driver, jsUtil);
    }

    public NewsPage clickDistrictNewsTab() {
        click(districtNewsTab);
        return new NewsPage(driver, jsUtil);
    }

    public NewsPage clickEmergencyNewsTab() {
        click(emergencyNewsTab);
        return new NewsPage(driver, jsUtil);
    }

    // ============== NEWS CARD DATA ==============

    public int getNewsCardCount() {
        return findAll(newsCards).size();
    }

    public List<String> getNewsTitles() {
        return findAll(newsCardTitles).stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getNewsDates() {
        return findAll(newsCardDates).stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Opens the first news card by clicking its image/title link.
     * Returns a new NewsPage instance pointed at the detail URL -
     * a dedicated NewsDetailPage should replace this once that page is mapped.
     */
    public void clickFirstNewsCard() {
        click(newsCardLinks);
    }

    // ─── District filter (react-select dropdown) ─────────────────────

    public String getSelectedDistrictFilterValue() {
        return getText(selectedDistrictValue);
    }

    public void openDistrictFilterDropdown() {
        click(districtFilterDropdown);
    }


    // ============== PAGINATION ==============

    public boolean isPaginationDisplayed() {
        return !findAll(paginationContainer).isEmpty();
    }

    public int getVisiblePaginationItemCount() {
        return findAll(paginationItems).size();
    }

    public String getActivePageNumber() {
        return getText(activePaginationItem);
    }

    public NewsPage clickNextPage() {
        click(nextPageButton);
        return new NewsPage(driver, jsUtil);
    }

    public NewsPage clickPreviousPage() {
        click(prevPageButton);
        return new NewsPage(driver, jsUtil);
    }

    public void clickPageNumber(String pageNumber) {
        By locator = By.xpath(
                "//ul[contains(@class,'rc-pagination')]//a[normalize-space()='" + pageNumber + "']");
        click(locator);
    }
}
