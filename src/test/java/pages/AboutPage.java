package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class AboutPage extends BasePage {

    // ─── Page header & navigation tabs ─────────────────────────────
    private final By pageHeader = By.cssSelector(".page-header h1");
    private final By aboutTab = By.cssSelector("ul.navigation.general a[href='/uz/about']");
    private final By governanceTab = By.cssSelector("ul.navigation.general a[href='/uz/about/governance']");
    private final By structureTab = By.cssSelector("ul.navigation.general a[href='/uz/about/structure']");
    private final By strategyTab = By.cssSelector("ul.navigation.general a[href='/uz/about/strategy']");
    private final By symbolsTab = By.cssSelector("ul.navigation.general a[href='/uz/about/symbols']");
    private final By pressTab = By.cssSelector("ul.navigation.general a[href='/uz/about/press']");
    private final By vacanciesTab = By.cssSelector("ul.navigation.general a[href='/uz/about/vacancies']");
    private final By meetingsTab = By.cssSelector("ul.navigation.general a[href='/uz/about/meetings']");
    private final By documentsTab = By.cssSelector("ul.navigation.general a[href='/uz/about/documents']");
    private final By contactsTab = By.cssSelector("ul.navigation.general a[href='/uz/about/contacts']");
    private final By activeTab = By.cssSelector("ul.navigation.general a.styles_active__s6Q4K");

    // ─── Main content ───────────────────────────────────────────────
    private final By pageTitle = By.cssSelector(".style_left-side__LqYLi .style_secondary-title__8QJ8g");
    private final By descriptionBlock = By.cssSelector(".style_description__mq7qZ");
    private final By descriptionParagraphs = By.cssSelector(".style_description__mq7qZ p");
    private final By aboutImage = By.cssSelector(".style_img__SRHVk img");

    public AboutPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, "/uz/about");
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

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    // =========== TAB ACTIONS ===========

    public AboutPage clickAboutTab() {
        click(aboutTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickGovernanceTab() {
        click(governanceTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickStructureTab() {
        click(structureTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickStrategyTab() {
        click(strategyTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickSymbolsTab() {
        click(symbolsTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickPressTab() {
        click(pressTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickVacanciesTab() {
        click(vacanciesTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickMeetingsTab() {
        click(meetingsTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickDocumentsTab() {
        click(documentsTab);
        return new AboutPage(driver, jsUtil);
    }

    public AboutPage clickContactsTab() {
        click(contactsTab);
        return new AboutPage(driver, jsUtil);
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

    public boolean isAboutImageDisplayed() {
        return !findAll(aboutImage).isEmpty();
    }
}
