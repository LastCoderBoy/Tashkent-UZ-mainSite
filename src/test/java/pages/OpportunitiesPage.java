package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class OpportunitiesPage extends BasePage {

    private final By pageHeader = By.cssSelector("h2");
    private final By resultsCounter = By.cssSelector(".style_title__f_Rek");

    // ─── Search ─────────────────────────────────────────────────────
    private final By searchInput = By.id("q");
    private final By searchButton = By.cssSelector("button.btn.primary[type='submit']");

    // ─── Opportunity cards ──────────────────────────────────────────
    private final By opportunityCards = By.cssSelector(".styles_opportunities-card__pXEnu");
    private final By opportunityCardQuestions = By.cssSelector(".styles_tag___PmBo");
    private final By opportunityCardDescriptions = By.cssSelector(".styles_opportunities-card__pXEnu strong");

    // ─── Pagination ─────────────────────────────────────────────────
    private final By paginationContainer = By.cssSelector("ul.rc-pagination");
    private final By activePaginationItem = By.cssSelector("li.rc-pagination-item-active a");
    private final By nextPageButton = By.cssSelector("li.rc-pagination-next button");

    // ─── Filter sidebar - accordion panel titles ─────────────────────
    private final By accordionPanels = By.cssSelector(".style_accordion-panel__zH1tJ");
    private final By cancelFiltersButton = By.cssSelector("button.style_error__4pDsS");

    // ─── Filter sidebar - specific known filters (id attributes are stable) ──
    private final By ageSlider = By.cssSelector("#age .ant-slider-handle");
    private final By genderRadioGroup = By.id("gender");
    private final By disabilityRadioGroup = By.id("disability");
    private final By citizenshipDropdown = By.id("react-select-citizenship_id-input");
    private final By marriageStatusDropdown = By.id("react-select-relationship_id-input");
    private final By childrenRadioGroup = By.id("children");
    private final By educationDropdown = By.id("react-select-education_id-input");
    private final By hasCarRadioGroup = By.id("has_car");
    private final By hasRealEstateRadioGroup = By.id("has_real_estate");
    private final By hasCriminalRecordRadioGroup = By.id("has_criminal_record");
    private final By hasPetsRadioGroup = By.id("has_pets");
    private final By hasPensionRadioGroup = By.id("has_pension");
    private final By notebookDropdown = By.id("react-select-list_id-input");
    private final By sectorTags = By.cssSelector(".style_checkbox-panel__Jx2Ky .ant-tag");
    private final By sectorCounter = By.cssSelector(".style_checkbox-panel__Jx2Ky .counter");

    public OpportunitiesPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, "/uz/opportunities");
    }

    // ============ PAGE LEVEL GETTERS ============

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageHeaderText() {
        return getText(pageHeader);
    }

    /**
     * Returns raw text like "Siz uchun 256 ta imkoniyat" -
     * use extractResultsCount() to parse the number out of it.
     */
    public String getResultsCounterText() {
        return getText(resultsCounter);
    }

    /**
     * Parses the numeric result count out of "Siz uchun 256 ta imkoniyat".
     * Useful to assert this number matches getOpportunityCount() * totalPages,
     * or simply changes after applying a filter.
     */
    public int extractResultsCount() {
        String text = getResultsCounterText();
        String digitsOnly = text.replaceAll("[^0-9]", "");
        return digitsOnly.isEmpty() ? 0 : Integer.parseInt(digitsOnly);
    }

    // ─── Search ─────────────────────────────────────────────────────

    public OpportunitiesPage searchOpportunities(String query) {
        set(searchInput, query);
        click(searchButton);
        return new OpportunitiesPage(driver, jsUtil);
    }

    // ─── Opportunity cards ──────────────────────────────────────────

    public int getOpportunityCardCount() {
        return findAll(opportunityCards).size();
    }

    public List<String> getOpportunityQuestions() {
        return findAll(opportunityCardQuestions).stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickOpportunityCard(String questionText) {
        By locator = By.xpath(
                "//div[@class='styles_tag___PmBo default' and normalize-space()='" + questionText + "']/ancestor::a");
        click(locator);
    }

    // ─── Pagination ─────────────────────────────────────────────────

    public boolean isPaginationDisplayed() {
        return !findAll(paginationContainer).isEmpty();
    }

    public String getActivePageNumber() {
        return getText(activePaginationItem);
    }

    public OpportunitiesPage clickNextPage() {
        click(nextPageButton);
        return new OpportunitiesPage(driver, jsUtil);
    }

    // ─── Filters - generic helpers ────────────────────────────────────

    /**
     * Total number of filter accordion panels rendered in the sidebar.
     * Sanity check - should stay 14 unless the backend adds/removes a filter.
     */
    public int getFilterPanelCount() {
        return findAll(accordionPanels).size();
    }

    /**
     * Expands a filter accordion panel by its visible title,
     * e.g. "Yosh", "Jinsi", "Sohalar".
     */
    public void expandFilterPanel(String filterTitle) {
        By locator = By.xpath(
                "//div[contains(@class,'style_accordion-title__YtI1R') and normalize-space()='" + filterTitle + "']");
        click(locator);
    }

    public OpportunitiesPage clickCancelFilters() {
        click(cancelFiltersButton);
        return new OpportunitiesPage(driver, jsUtil);
    }

    // ─── Filters - radio groups (Gender, Disability, Children, Car, etc.) ──

    /**
     * Generic radio selector for the yes/no style filters that share the
     * same DOM shape: <div id="..."><label><input value="yes|no">...
     * Pass the group's By locator and "yes" or "no".
     */
    public void selectRadioOption(By radioGroupLocator, String value) {
        WebElement group = find(radioGroupLocator);
        WebElement option = group.findElement(By.cssSelector("input[value='" + value + "']"));
        jsUtil.clickJS(By.cssSelector("#" + group.getAttribute("id") + " input[value='" + value + "']"));
    }

    public void selectGender(String value) {
        selectRadioOption(genderRadioGroup, value);
    }

    public void selectDisability(String value) {
        selectRadioOption(disabilityRadioGroup, value);
    }

    public void selectHasChildren(String value) {
        selectRadioOption(childrenRadioGroup, value);
    }

    public void selectHasCar(String value) {
        selectRadioOption(hasCarRadioGroup, value);
    }

    public void selectHasRealEstate(String value) {
        selectRadioOption(hasRealEstateRadioGroup, value);
    }

    public void selectHasCriminalRecord(String value) {
        selectRadioOption(hasCriminalRecordRadioGroup, value);
    }

    public void selectHasPets(String value) {
        selectRadioOption(hasPetsRadioGroup, value);
    }

    public void selectHasPension(String value) {
        selectRadioOption(hasPensionRadioGroup, value);
    }

    // ─── Filters - react-select dropdowns ─────────────────────────────

    public void openCitizenshipDropdown() {
        click(citizenshipDropdown);
    }

    public void openMarriageStatusDropdown() {
        click(marriageStatusDropdown);
    }

    public void openEducationDropdown() {
        click(educationDropdown);
    }

    public void openNotebookDropdown() {
        click(notebookDropdown);
    }

    // ─── Filters - age slider ─────────────────────────────────────────

    /**
     * Returns the current aria-valuenow of the age slider handle (1-70).
     * Dragging an Ant Design slider via Selenium requires Actions with
     * keyboard arrows (ARROW_RIGHT/LEFT) rather than drag-and-drop,
     * since the handle is keyboard-operable (role="slider").
     */
    public String getAgeSliderValue() {
        return find(ageSlider).getAttribute("aria-valuenow");
    }

    // ─── Filters - sector tags (multi-select, max 3) ──────────────────

    public void clickSectorTag(String sectorName) {
        By locator = By.xpath(
                "//span[contains(@class,'ant-tag-checkable') and normalize-space()='" + sectorName + "']");
        click(locator);
    }

    /**
     * Returns text like "0/3" - the sector tag selection counter.
     * Useful to assert the 3-tag selection limit is enforced.
     */
    public String getSectorCounterText() {
        return getText(sectorCounter);
    }

    public int getAvailableSectorTagCount() {
        return findAll(sectorTags).size();
    }
}