package pages.primary;

import base.BasePage;
import enums.NavLink;
import enums.SearchOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.*;
import util.JavaScriptUtil;

public class MainPage extends BasePage {

    // TODO: fields to absorb html:
    // search option : languages dropdown
    // "Ma'lumotnoma" section
    // "Salom Toshkent" section: "Afisha" section
    // "Geografik Portal" section

    private final By searchInput = By.id("q");
    private final By searchButton = By.xpath("//button[@type='submit' and @aria-label='Qidirish']");
    private final By searchDropdown = By.id("type");


    public MainPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
    }

    // ========== NAVBAR CLICKS ==========

    public KnowYourDistrictPage clickKnowYourDistrictMenu() {
        click(getNavLocator(NavLink.DISTRICTS));
        return new KnowYourDistrictPage(driver, jsUtil);
    }

    public NewsPage clickNewsMenu() {
        click(getNavLocator(NavLink.NEWS));
        return new NewsPage(driver, jsUtil);
    }

    public OpportunitiesPage clickOpportunitiesMenu() {
        click(getNavLocator(NavLink.OPPORTUNITIES));
        return new OpportunitiesPage(driver, jsUtil);
    }

    public AnticorruptionPage clickAnticorruptionMenu() {
        click(getNavLocator(NavLink.ANTICORRUPTION));
        return new AnticorruptionPage(driver, jsUtil);
    }

    public CityPage clickCityMenu() {
        click(getNavLocator(NavLink.CITY));
        return new CityPage(driver, jsUtil);
    }

    public AboutPage clickAboutMenu() {
        click(getNavLocator(NavLink.ABOUT));
        return new AboutPage(driver, jsUtil);
    }

    public DeputiesPage clickDeputiesMenu() {
        click(getNavLocator(NavLink.DEPUTIES));
        return new DeputiesPage(driver, jsUtil);
    }

    public SocietyPage clickSocietyMenu() {
        click(getNavLocator(NavLink.SOCIETY));
        return new SocietyPage(driver, jsUtil);
    }


    // ================= SEARCH BAR =================

    public void enterTextToSearchBar(String textToSearch) {
        set(searchInput, textToSearch);
    }

    public SearchResponsePage clickSearchButton() {
        click(searchButton);
        return new SearchResponsePage(driver, jsUtil);
    }

    public void selectSearchType(SearchOptions type) {
        click(searchDropdown);
        By optionLocator = getSearchOptionLocator(type.getValue());
        click(optionLocator);
    }


    // ================= PRIVATE METHODS =================
    private By getSearchOptionLocator(String optionName) {
        return By.xpath("//div[@role='option' and text()=\"" + optionName + "\"]");
    }

    private By getNavLocator(NavLink link){
        return By.cssSelector("ul.d_flex li a[href*='" + link.getPath() + "']");
    }
}
