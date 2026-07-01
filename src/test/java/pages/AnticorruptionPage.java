package pages;

import base.BasePage;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JavaScriptUtil;
import util.WaitUtils;

import java.util.List;

public class AnticorruptionPage extends BasePage {

    // ─── Page header & navigation tabs ─────────────────────────────
    private final By pageHeader = By.cssSelector(".page-header h1");
    private final By structureTab = By.cssSelector("ul.navigation.small a[href='/uz/anticorruption']");
    private final By articlesTab = By.cssSelector("ul.navigation.small a[href='/uz/anticorruption/articles']");
    private final By meetingsTab = By.cssSelector("ul.navigation.small a[href='/uz/anticorruption/meetings']");
    private final By actsTab = By.cssSelector("ul.navigation.small a[href='/uz/anticorruption/acts']");
    private final By videosTab = By.cssSelector("ul.navigation.small a[href='/uz/anticorruption/videos']");
    private final By activeTab = By.cssSelector("ul.navigation.small a.styles_active__s6Q4K");

    // ─── Person card ───────────────────────────────────────────────
    private final By personCard = By.cssSelector(".styles_person-card__uKreq");
    private final By personName = By.cssSelector(".styles_top__T9r6M strong");
    private final By personRole = By.cssSelector(".styles_top__T9r6M p");
    private final By phoneLinks = By.cssSelector(".styles_bottom__SEztD a[href^='tel:']");
    private final By emailLink = By.cssSelector(".styles_bottom__SEztD a[href^='mailto:']");
    private final By contactItems = By.cssSelector(".styles_bottom__SEztD li");
    private final By telegramLink = By.cssSelector(".styles_social-links__nCOb7 a[href*='t.me']");
    private final By facebookLink = By.cssSelector(".styles_social-links__nCOb7 a[href*='facebook.com']");

    // ─── Description & structure block ────────────────────────────
    private final By descriptionText = By.cssSelector(".styles_description__T4QZx");
    private final By structureHeader = By.cssSelector(".styles_structure-header__S_pL6 strong");
    private final By downloadPdfButton = By.cssSelector(".styles_structure-header__S_pL6 button");
    private final By structureImage = By.cssSelector(".styles_structure-block__lKbfq img");

    public AnticorruptionPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
        WaitUtils.waitForUrlContains(driver, ConfigReader.get("tashkent.anticorruption.url"));
    }

    // =========== PAGE LEVEL GETTERS ===========

    public boolean isPageLoaded() {
        return isHeaderDisplayed() && getPageUrl().contains("/uz/anticorruption");
    }

    public boolean isHeaderDisplayed() {
        return find(pageHeader).isDisplayed();
    }

    public String getActiveTabName() {
        return getText(activeTab);
    }

    // =========== TAB ACTIONS ===========

    public AnticorruptionPage clickStructureTab() {
        click(structureTab);
        return new AnticorruptionPage(driver, jsUtil);
    }

    public AnticorruptionPage clickArticlesTab() {
        click(articlesTab);
        return new AnticorruptionPage(driver, jsUtil);
    }

    public AnticorruptionPage clickMeetingsTab() {
        click(meetingsTab);
        return new AnticorruptionPage(driver, jsUtil);
    }

    public AnticorruptionPage clickActsTab() {
        click(actsTab);
        return new AnticorruptionPage(driver, jsUtil);
    }

    public AnticorruptionPage clickVideosTab() {
        click(videosTab);
        return new AnticorruptionPage(driver, jsUtil);
    }

    // =========== PERSON CARD DATA ===========

    public boolean isPersonCardDisplayed() {
        return !findAll(personCard).isEmpty();
    }

    public String getPersonName() {
        return getText(personName);
    }

    public String getPersonRole() {
        return getText(personRole);
    }

    public List<String> getPhoneNumbers() {
        return findAll(phoneLinks).stream()
                .map(WebElement::getText)
                .toList();
    }

    public String getEmailAddress() {
        return findAll(emailLink).stream()
                .findFirst()
                .map(e -> e.getAttribute("href").replaceFirst("^mailto:", ""))
                .orElse("");
    }

    public String getAddress() {
        List<WebElement> items = findAll(contactItems);
        return items.isEmpty() ? "" : items.get(items.size() - 1).getText();
    }

    public String getTelegramUrl() {
        return findAll(telegramLink).stream()
                .findFirst()
                .map(e -> e.getAttribute("href"))
                .orElse("");
    }

    public String getFacebookUrl() {
        return findAll(facebookLink).stream()
                .findFirst()
                .map(e -> e.getAttribute("href"))
                .orElse("");
    }

    // =========== DESCRIPTION & STRUCTURE ===========

    public String getDescriptionText() {
        return getText(descriptionText);
    }

    public String getStructureHeaderText() {
        return getText(structureHeader);
    }

    public boolean isStructureImageDisplayed() {
        return !findAll(structureImage).isEmpty();
    }

    public void clickDownloadPdfButton() {
        click(downloadPdfButton);
    }
}
