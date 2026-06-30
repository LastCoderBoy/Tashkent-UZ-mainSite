package pages.primary;

import base.BasePage;
import enums.NavLink;
import enums.SearchOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.*;
import util.JavaScriptUtil;

import java.util.List;

public class MainPage extends BasePage {

    // TODO: fields to absorb html:
    // languages dropdown
    // "Ma'lumotnoma" section
    // "Salom Toshkent" section: "Afisha" section
    // "Geografik Portal" section

    private final By searchInput = By.id("q");
    private final By searchButton = By.xpath("//button[@type='submit' and @aria-label='Qidirish']");
    private final By searchDropdown = By.id("type");
    private final By afishaCards = By.xpath("//div[contains(@class, 'styles_afisha-card__7CQD_')]//a[@class='styles_title__sKnEi']");
    private final By newsViewMoreButton = By.xpath("//a[@href='/uz/news'][contains(text(),'Hammasini ko‘rish')]");
    private final By tashkentShowsViewMoreButton = By.xpath("//a[@href='/uz/news/tashkent-shows'][contains(text(),'Hammasini ko‘rish')]");

    public MainPage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
    }

    // ====================================
    //          NAVBAR SECTION
    //=====================================

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


    // ====================================
    //             SEARCH BAR
    //=====================================

    public void enterTextToSearchBar(String textToSearch) {
        jsUtil.scrollToElementJS(searchInput);
        set(searchInput, textToSearch);
    }

    public SearchResponsePage clickSearchButton() {
        jsUtil.scrollToElementJS(searchButton);
        click(searchButton);
        return new SearchResponsePage(driver, jsUtil);
    }

    public void selectSearchType(SearchOptions type) {
        jsUtil.scrollToElementJS(searchDropdown);
        click(searchDropdown);
        By optionLocator = getSearchOptionLocator(type.getValue());
        click(optionLocator);
    }

    // ====================================
    //          VIEW MORE BUTTONS
    //=====================================

    public NewsPage clickNewsViewMoreButton() {
        jsUtil.scrollToElementJS(newsViewMoreButton);
        click(newsViewMoreButton);
        return new NewsPage(driver, jsUtil);
    }

    public TashkentShowsPage clickTashkentShowsViewMoreButton() {
        jsUtil.scrollToElementJS(tashkentShowsViewMoreButton);
        click(tashkentShowsViewMoreButton);
        return new TashkentShowsPage(driver, jsUtil);
    }



    // ====================================
    //          AFISHA SECTION
    //=====================================

    public List<String> getAfishaCardTitles() {
        jsUtil.scrollToElementJS(afishaCards);
        return findAll(afishaCards)
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }



    // ====================================
    //          PRIVATE METHODS
    //=====================================

    private By getSearchOptionLocator(String optionName) {
        return By.xpath("//div[@role='option' and text()=\"" + optionName + "\"]");
    }

    private By getNavLocator(NavLink link){
        return By.cssSelector("ul.d_flex li a[href*='" + link.getPath() + "']");
    }
}
