package pages.primary;

import base.BasePage;
import config.ConfigReader;
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
    private final By popupAuthText = By.xpath("//strong[normalize-space()='Shaxsiy kabinetga kirish']");
    private final By popupMapTitle = By.xpath("//div[@class='style_map-content__Q4dqu']//strong");

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
        By newsViewMoreButton = getViewMoreButtonLocator(ConfigReader.get("tashkent.news.url"));
        jsUtil.scrollToElementJS(newsViewMoreButton);
        click(newsViewMoreButton);
        return new NewsPage(driver, jsUtil);
    }

    public TashkentShowsPage clickTashkentShowsViewMoreButton() {
        By tashkentShowsViewMoreButton = getViewMoreButtonLocator(ConfigReader.get("tashkent.shows.url"));
        jsUtil.scrollToElementJS(tashkentShowsViewMoreButton);
        click(tashkentShowsViewMoreButton);
        return new TashkentShowsPage(driver, jsUtil);
    }

    public TashkentListeningPage clickTashkentListeningViewMoreButton() {
        By tashkentListeningViewMoreButton = getViewMoreButtonLocator(ConfigReader.get("tashkent.listening.url"));
        jsUtil.scrollToElementJS(tashkentListeningViewMoreButton);
        click(tashkentListeningViewMoreButton);
        return new TashkentListeningPage(driver, jsUtil);
    }

    public AfishaPage clickAfishaViewMoreButton() {
        By afishaViewMoreButton = getViewMoreButtonLocator(ConfigReader.get("tashkent.afisha.url"));
        jsUtil.scrollToElementJS(afishaViewMoreButton);
        click(afishaViewMoreButton);
        return new AfishaPage(driver, jsUtil);
    }

    public SalomToshkentPage clickSalomToshkentViewMoreButton(){
        By salomToshkentViewMoreButton = getViewMoreButtonLocator(ConfigReader.get("tashkent.salom.url"));
        jsUtil.scrollToElementJS(salomToshkentViewMoreButton);
        click(salomToshkentViewMoreButton);
        return new SalomToshkentPage(driver, jsUtil);
    }

    public ServicesPage clickServicesViewMoreButton(){
        By servicesViewMoreButton = getViewMoreButtonLocator(ConfigReader.get("tashkent.services.url"));
        jsUtil.scrollToElementJS(servicesViewMoreButton);
        click(servicesViewMoreButton);
        return new ServicesPage(driver, jsUtil);
    }

    public UsefulLinksPage clickUsefulLinksViewMoreButton(){
        By usefulLinksViewMoreButton = getViewMoreButtonLocator(ConfigReader.get("tashkent.useful-links.url"));
        jsUtil.scrollToElementJS(usefulLinksViewMoreButton);
        click(usefulLinksViewMoreButton);
        return new UsefulLinksPage(driver, jsUtil);
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
    //          SERVICES SECTION
    //=====================================

    public void clickUyJoyService() {
        By uyJoyServiceLocator = getHelpServicesLocator("Uy-joy");
        jsUtil.scrollToElementJS(uyJoyServiceLocator);
        click(uyJoyServiceLocator);
    }

    public void clickQaysiMahallaService() {
        By qaysiMahallaServiceLocator = getHelpServicesLocator("Qaysi mahalla");
        jsUtil.scrollToElementJS(qaysiMahallaServiceLocator);
        click(qaysiMahallaServiceLocator);
    }

    public void clickQayerdaIshlashimniBilishService() {
        By qayerdaIshlashimniBilishServiceLocator = getHelpServicesLocator("Qayerda ishlashimni bilish");
        jsUtil.scrollToElementJS(qayerdaIshlashimniBilishServiceLocator);
        click(qayerdaIshlashimniBilishServiceLocator);
    }
    public void clickAtrofimizdagiNarsalarService() {
        By atrofimizdagiNarsalarServiceLocator = getHelpServicesLocator("Atrofimizdagi narsalar");
        jsUtil.scrollToElementJS(atrofimizdagiNarsalarServiceLocator);
        click(atrofimizdagiNarsalarServiceLocator);
    }

    public void clickPochtaIndeksService(){
        By pochtaIndeksServiceLocator = getHelpServicesLocator("Pochta indeksini aniqlash");
        jsUtil.scrollToElementJS(pochtaIndeksServiceLocator);
        click(pochtaIndeksServiceLocator);
    }

    public boolean isServiceWhichRequireMapDisplayed() {
        return getText(popupMapTitle).contains("Xaritada");
    }

    // By default, auth required to use the Service, that's why the presence of authCard is checked
    // Will be used in "Uy-Joy" and "Qayerda ishlashimni bilish"
    public boolean isServiceWhichRequireAuthDisplayed() {
        return find(popupAuthText).isDisplayed();
    }




    // ====================================
    //          PRIVATE METHODS
    //=====================================

    private By getHelpServicesLocator(String serviceName) {
        return By.xpath("//section[@aria-labelledby='help-services-title']//strong[contains(text(), '" + serviceName + "')]");
    }


    private By getSearchOptionLocator(String optionName) {
        return By.xpath("//div[@role='option' and text()=\"" + optionName + "\"]");
    }

    private By getNavLocator(NavLink link){
        return By.cssSelector("ul.d_flex li a[href*='" + link.getPath() + "']");
    }

    private By getViewMoreButtonLocator(String url){
        return By.xpath("//a[@href='" + url + "'][contains(text(),'Hammasini ko‘rish')]");
    }
}
