package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class DeputiesPage extends BasePage {

    // ─── Page header & navigation tabs ─────────────────────────────
    private final By pageHeader = By.cssSelector(".page-header h1");
    private final By cityDeputiesTab = By.cssSelector("ul.navigation a[href='/uz/deputies']");
    private final By districtDeputiesTab = By.cssSelector("ul.navigation a[href='/uz/deputies/district']");
    private final By activeTab = By.cssSelector("ul.navigation a.styles_active__s6Q4K");

    // ─── Deputies cards ────────────────────────────────────────────
    private final By deputyCards = By.cssSelector(".styles_person-card__uKreq");
    private final By deputyCardNames = By.cssSelector(".styles_top__T9r6M strong");
    private final By deputyCardDescriptions = By.cssSelector(".styles_top__T9r6M div");
    private final By deputyCardRoles = By.cssSelector(".styles_top__T9r6M p");
    private final By deputyCardEmails = By.cssSelector(".styles_bottom__SEztD span");
    private final By biographyButtons = By.cssSelector(".styles_bottom__SEztD button");

    // ─── Pagination ─────────────────────────────────────────────────
    private final By paginationContainer = By.cssSelector("ul.rc-pagination");
    private final By paginationItems = By.cssSelector("li.rc-pagination-item");
    private final By activePaginationItem = By.cssSelector("li.rc-pagination-item-active a");
    private final By nextPageButton = By.cssSelector("li.rc-pagination-next button");
    private final By prevPageButton = By.cssSelector("li.rc-pagination-prev button");

    public DeputiesPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.deputies.url"));
    }

    // =========== PAGE LEVEL GETTERS ===========

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/deputies");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }

    public String getActiveTabName() {
        return getText(activeTab);
    }

    // =========== TAB ACTIONS ===========

    public DeputiesPage clickCityDeputiesTab() {
        click(cityDeputiesTab);
        return new DeputiesPage(driver, jsUtil);
    }

    public DeputiesPage clickDistrictDeputiesTab() {
        click(districtDeputiesTab);
        return new DeputiesPage(driver, jsUtil);
    }

    // =========== DEPUTIES CARD DATA ===========

    public int getDeputyCardCount() {
        return findAll(deputyCards).size();
    }

    public List<String> getDeputyNames() {
        return findAll(deputyCardNames).stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getDeputyDescriptions() {
        return findAll(deputyCardDescriptions).stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .toList();
    }

    public List<String> getDeputyRoles() {
        return findAll(deputyCardRoles).stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getDeputyEmails() {
        return findAll(deputyCardEmails).stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .toList();
    }

    public void clickBiographyButton(int cardIndex) {
        findAll(biographyButtons).get(cardIndex).click();
    }

    // =========== PAGINATION ===========

    public boolean isPaginationDisplayed() {
        return find(paginationContainer).isDisplayed();
    }

    public int getVisiblePaginationItemCount() {
        return findAll(paginationItems).size();
    }

    public String getActivePageNumber() {
        return getText(activePaginationItem);
    }

    public DeputiesPage clickNextPage() {
        click(nextPageButton);
        return new DeputiesPage(driver, jsUtil);
    }

    public DeputiesPage clickPreviousPage() {
        click(prevPageButton);
        return new DeputiesPage(driver, jsUtil);
    }

    public void clickPageNumber(String pageNumber) {
        By locator = By.xpath("//ul[contains(@class,'rc-pagination')]//a[normalize-space()='" + pageNumber + "']");
        click(locator);
    }
}
