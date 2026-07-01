package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.DateUtils;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

public class NewsPage extends BasePage {

    // ─── Page header & sub-navigation tabs ─────────────────────────────
    private final By pageHeader = By.cssSelector("h1");
    private final By cityNewsTab = By.cssSelector("a[href='/uz/news']");
    private final By districtNewsTab = By.cssSelector("a[href='/uz/news/district-news']");
    private final By emergencyNewsTab = By.cssSelector("a[href='/uz/news/emergency-news']");
    private final By activeNewsTab = By.cssSelector("ul.styles_news-navigation__hP9oY a.styles_active__s6Q4K");

    // ─── Filters ────────────────────────────────────────────────────
    private final By datePickerInput = By.cssSelector(".datepicker input.main");
    private final By dateRangePicker = By.cssSelector(".daterangepicker.show-calendar");
    private final By leftMonthLabel = By.cssSelector(".drp-calendar.left th.month");
    private final By rightMonthLabel = By.cssSelector(".drp-calendar.right th.month");
    private final By prevMonthButton = By.cssSelector(".drp-calendar.left th.prev.available");
    private final By nextMonthButton = By.cssSelector(".drp-calendar.right th.next.available");
    private final By applyDateRangeButton = By.cssSelector(".drp-buttons .applyBtn");
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
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.news.url"));
    }

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/news");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
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

    // ─── Date range filter ────────────────────────────────────────────

    public NewsPage applyDateRangeFilter(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be <= end date");
        }

        click(datePickerInput);
        find(dateRangePicker);

        selectDate(startDate);
        selectDate(endDate);

        // Capture the first old news card BEFORE clicking apply
        WebElement oldFirstCard = findAll(newsCards).get(0);

        click(applyDateRangeButton);

        // WAIT for the old card to be removed from the DOM (meaning the UI refreshed)
        WaitUtils.waitForStaleness(driver, oldFirstCard);

        // Wait for the new cards to be visible
        WaitUtils.waitForVisible(driver, newsCards);

        click(applyDateRangeButton);

        return this;
    }


    // ============== PAGINATION ==============

    public boolean isPaginationDisplayed() {
        try {
            return find(paginationContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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

    // ================= PRIVATE HELPERS =================

    private void selectDate(LocalDate date) {
        String calendarSide = ensureDateMonthIsVisible(date);
        By dayLocator = By.xpath("//div[contains(@class,'daterangepicker') and contains(@class,'show-calendar')]"
                + "//div[contains(@class,'drp-calendar') and contains(@class,'" + calendarSide + "')]"
                + "//td[contains(@class,'available') and not(contains(@class,'off')) and normalize-space()='"
                + date.getDayOfMonth() + "']");
        click(dayLocator);
    }

    private String ensureDateMonthIsVisible(LocalDate targetDate) {
        YearMonth targetMonth = YearMonth.from(targetDate);

        for (int i = 0; i < 24; i++) {
            YearMonth leftMonth = DateUtils.parseUzMonthYearLabel(getText(leftMonthLabel));
            YearMonth rightMonth = DateUtils.parseUzMonthYearLabel(getText(rightMonthLabel));

            if (targetMonth.equals(leftMonth)) {
                return "left";
            }
            if (targetMonth.equals(rightMonth)) {
                return "right";
            }
            if (targetMonth.isBefore(leftMonth)) {
                click(prevMonthButton);
                continue;
            }
            if (targetMonth.isAfter(rightMonth)) {
                click(nextMonthButton);
            }
        }

        throw new IllegalStateException("Target month is not visible in datepicker: " + targetDate);
    }
}
