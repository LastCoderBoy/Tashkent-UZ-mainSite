package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class KnowYourDistrictPage extends BasePage {

    private final By pageHeader = By.xpath("//h1");
    private final By visibleDistrictTabs = By.cssSelector("ul.navigation > li > button.btn");
    private final By viewMoreLink = By.cssSelector("li.flexMenu-viewMore a");
    private final By dropdownDistrictTabs = By.cssSelector(".more_dropdown li button.btn");
    private final By activeTab = By.cssSelector("ul.navigation button.btn.active");


    public KnowYourDistrictPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.know-your-district.url"));
    }

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/districts");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }

    // ─── District tabs ─────────────────

    /**
     * Returns the names of districts shown directly in the tab bar,
     */
    public List<String> getVisibleDistrictNames() {
        return findAll(visibleDistrictTabs).stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickDistrictTab(String districtName) {
        By locator = By.xpath(
                "//ul[contains(@class,'navigation')]/li/button[normalize-space()='" + districtName + "']");
        click(locator);
    }

    public String getActiveTabName() {
        return getText(activeTab);
    }

    /**
     * Total district count = visible tabs + dropdown tabs.
     * Should equal 12 for tashkent.uz. Used to assert no district
     * is missing/duplicated after a UI change.
     */
    public int getTotalDistrictCount() {
        int visibleCount = getVisibleDistrictNames().size();

        if (isMoreDropdownPresent()) {
            openMoreDistrictsDropdown();
            return visibleCount + getDropdownDistrictNames().size();
        }

        return visibleCount;
    }

    /**
     * Checks whether the "Barcha..." (View More) trigger is rendered at all.
     * Absent on wide viewports where every district tab fits in one row.
     */
    private boolean isMoreDropdownPresent() {
        return !findAll(viewMoreLink).isEmpty();
    }

    // ─── "Barcha..." dropdown (if screen size is smaller) ─────────────────

    public void openMoreDistrictsDropdown() {
        click(viewMoreLink);
    }

    /**
     * Returns district names hidden inside the "Barcha..." dropdown,
     * Call openMoreDistrictsDropdown() first.
     */
    public List<String> getDropdownDistrictNames() {
        return findAll(dropdownDistrictTabs).stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickDropdownDistrict(String districtName) {
        By locator = By.xpath(
                "//ul[contains(@class,'more_dropdown')]//button[normalize-space()='" + districtName + "']");
        click(locator);
    }
}
