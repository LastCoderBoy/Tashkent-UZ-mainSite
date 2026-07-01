package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import enums.SearchOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

@Epic("Funktsional testlar")
public class MainPageTests extends BaseTest {

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("Bosh sahifadagi 'Tumaningizni bilib oling' menyusi bosilganda mos sahifa ochilishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenKnowYourDistrictPage_WhenClicked() {
        KnowYourDistrictPage knowYourDistrictPage = mainPage.clickKnowYourDistrictMenu();

        Assert.assertTrue(knowYourDistrictPage.isPageLoaded(),
                "Know Your District page should be loaded with correct URL and header");
    }

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("'Yangiliklar' menyusi bosilganda sahifa, default tab va kontent elementlari to'g'ri chiqishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenNewsPage_WhenClicked() {
        NewsPage newsPage = mainPage.clickNewsMenu();

        Assert.assertTrue(newsPage.isPageLoaded(),
                "News page should be loaded with correct URL and header");

        // Default tab assertion - "Shahar yangiliklari" should be active by default
        Assert.assertEquals(newsPage.getActiveTabName(), "Shahar yangiliklari",
                "Default active news tab should be 'Shahar yangiliklari'");

        Assert.assertTrue(newsPage.getNewsCardCount() > 0,
                "Expected at least one news card to be displayed");

        Assert.assertTrue(newsPage.isPaginationDisplayed(),
                "Pagination should be displayed on the news listing page");

    }

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("'Imkoniyatlar' menyusi bosilganda mos sahifa ochilishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenOpportunitiesPage_WhenClicked() {
        OpportunitiesPage opportunitiesPage = mainPage.clickOpportunitiesMenu();

        Assert.assertTrue(opportunitiesPage.isPageLoaded(),
                "Opportunities page should be loaded with correct URL and header");
    }

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("'Korrupsiyaga qarshi kurashish' sahifasi ochilib, asosiy bloklar ko'rinishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenAnticorruptionPage_WhenClicked() {
        AnticorruptionPage anticorruptionPage = mainPage.clickAnticorruptionMenu();

        Assert.assertTrue(anticorruptionPage.isPageLoaded(),
                "Anticorruption page should be loaded with correct URL and header");

        Assert.assertEquals(anticorruptionPage.getActiveTabName(), "Tuzilma",
                "Default active tab should be 'Tuzilma'");

        Assert.assertTrue(anticorruptionPage.isPersonCardDisplayed(),
                "Person card should be displayed on anticorruption page");

        Assert.assertTrue(anticorruptionPage.isStructureImageDisplayed(),
                "Structure image should be displayed on anticorruption page");
    }

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("'Shahar' sahifasi ochilib, default tab, tavsif bloki va rasm mavjudligini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenCityPage_WhenClicked() {
        CityPage cityPage = mainPage.clickCityMenu();

        Assert.assertTrue(cityPage.isPageLoaded(), "City page should be loaded with correct URL and header");

        Assert.assertEquals(cityPage.getActiveTabName(), "Umumiy ma'lumot",
                "Default active tab should be 'Umumiy ma\\'lumot'");

        Assert.assertTrue(cityPage.isDescriptionBlockDisplayed(),
                "Description block should be displayed on city page");

        Assert.assertTrue(cityPage.isCityImageDisplayed(),
                "City image should be displayed on city page");
    }

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("'Hokimiyat haqida' sahifasi ochilib, asosiy tavsif va rasm bloklari ko'rinishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenAboutPage_WhenClicked() {
        AboutPage aboutPage = mainPage.clickAboutMenu();

        Assert.assertTrue(aboutPage.isPageLoaded(), "About page should be loaded with correct URL and header");

        Assert.assertEquals(aboutPage.getActiveTabName(), "Hokimiyat haqida",
                "Default active tab should be 'Hokimiyat haqida'");

        Assert.assertTrue(aboutPage.isDescriptionBlockDisplayed(),
                "Description block should be displayed on about page");

        Assert.assertTrue(aboutPage.isAboutImageDisplayed(),
                "About page image should be displayed");
    }

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("'Deputatlar' sahifasi ochilib, kartalar va pagination mavjudligini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenDeputiesPage_WhenClicked() {
        DeputiesPage deputiesPage = mainPage.clickDeputiesMenu();

        Assert.assertTrue(deputiesPage.isPageLoaded(), "Deputies page should be loaded with correct URL and header");

        Assert.assertEquals(deputiesPage.getActiveTabName(), "Shahar deputatlari",
                "Default active tab should be 'Shahar deputatlari'");

        Assert.assertTrue(deputiesPage.getDeputyCardCount() > 0,
                "Expected at least one deputy card to be displayed");

        Assert.assertTrue(deputiesPage.isPaginationDisplayed(),
                "Pagination should be displayed on deputies page");
    }

    @Feature("Navigatsiya")
    @Story("Navbar havolalari to'g'ri sahifani ochadi")
    @Description("'Fuqarolik jamiyati' sahifasi ochilib, default filter va mahalla kartalari chiqishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenSocietyPage_WhenClicked() {
        SocietyPage societyPage = mainPage.clickSocietyMenu();

        Assert.assertTrue(societyPage.isPageLoaded(), "Society page should be loaded with correct URL and header");

        Assert.assertEquals(societyPage.getActiveTabName(), "Mahalla",
                "Default active tab should be 'Mahalla'");

        Assert.assertEquals(societyPage.getSelectedDistrictTypeValue(), "Barcha tumanlar",
                "Default district filter value should be 'Barcha tumanlar'");

        Assert.assertTrue(societyPage.getMahallaCardCount() > 0,
                "Expected at least one mahalla card to be displayed");

        Assert.assertTrue(societyPage.isPaginationDisplayed(),
                "Pagination should be displayed on society page");
    }


    // =======================================
    //          SEARCH BAR TESTS
    // =======================================

    @Feature("Qidiruv")
    @Story("Qidiruv natijalari mos chiqadi")
    @Description("'Sayt bo'ylab' qidiruv turi bo'yicha bir nechta so'z uchun natijalar mavjudligini tekshiradi.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldShowSearchResults_WhenSaytBoylabOptionSelected(){
        String[] searchTerms = {"Toshkent", "Mahalla", "Konsert"};
        int totalSearchResults = 0;

        for (String term : searchTerms) {
            mainPage.selectSearchType(SearchOptions.SAYT_BOYLAB);
            mainPage.enterTextToSearchBar(term);
            SearchResponsePage searchResponsePage = mainPage.clickSearchButton();

            int resultsForTerm = searchResponsePage.getTotalSearchResults();
            totalSearchResults += resultsForTerm;

            // go back to MainPage before the next iteration starts
            mainPage = searchResponsePage.clickSiteLogo();
        }

        Assert.assertTrue(totalSearchResults > 0, "Expected at least one search result for the 'Sayt Boylab' option");
    }

    @Feature("Qidiruv")
    @Story("Qidiruv natijalari mos chiqadi")
    @Description("'Afisha' qidiruv turi bo'yicha tanlangan nomlar uchun natija va relevatlikni tekshiradi.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldShowSearchResults_WhenAfishaOptionSelected(){
        List<String> afishaCardTitles = mainPage.getAfishaCardTitles();

        for(int i=0; i<3; i++){
            String title = afishaCardTitles.get(i);

            mainPage.selectSearchType(SearchOptions.AFISHA);
            mainPage.enterTextToSearchBar(title);
            SearchResponsePage searchResponsePage = mainPage.clickSearchButton();

            int resultsForTitle = searchResponsePage.getTotalSearchResults();

            // each iteration is its own checkpoint - fails immediately, names the culprit
            Assert.assertTrue(resultsForTitle > 0,
                    "Expected at least one search result for Afisha title: '" + title + "'");

            System.out.println("qqqqq: " + searchResponsePage.getResultContents());

            Assert.assertTrue(searchResponsePage.anyResultContains(title),
                    "Expected search results to be relevant to: '" + title + "', but none of the titles/descriptions matched. Got titles: "
                            + searchResponsePage.getResultContents());

            mainPage = searchResponsePage.clickSiteLogo();
        }
    }

    // =======================================
    //        VIEW MORE BUTTON TESTS
    // =======================================

    @Feature("Navigatsiya")
    @Story("View More tugmalari to'g'ri sahifaga yo'naltiradi")
    @Description("Yangiliklar bo'limidagi 'Batafsil' tugmasi to'g'ri sahifani ochishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldRedirectNewsPage_WhenViewMoreButtonClicked(){
        NewsPage newsPage = mainPage.clickNewsViewMoreButton();
        Assert.assertTrue(newsPage.isPageLoaded(),
                "News page should be loaded with correct URL and header");
    }

    @Feature("Navigatsiya")
    @Story("View More tugmalari to'g'ri sahifaga yo'naltiradi")
    @Description("Efirda Toshkent bo'limidagi 'Batafsil' tugmasi to'g'ri sahifani ochishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldRedirectTashkentShowsPage_WhenViewMoreButtonClicked(){
        TashkentShowsPage tashkentShowsPage = mainPage.clickTashkentShowsViewMoreButton();
        Assert.assertTrue(tashkentShowsPage.isPageLoaded(),
                "Tashkent Shows page should be loaded with correct URL and header");
    }

    @Feature("Navigatsiya")
    @Story("View More tugmalari to'g'ri sahifaga yo'naltiradi")
    @Description("Toshkent tinglaydi bo'limidagi 'Batafsil' tugmasi to'g'ri sahifani ochishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldRedirectTashkentListeningPage_WhenViewMoreButtonClicked(){
        TashkentListeningPage tashkentListeningPage = mainPage.clickTashkentListeningViewMoreButton();
        Assert.assertTrue(tashkentListeningPage.isPageLoaded(),
                "Tashkent Listening page should be loaded with correct URL and header");
    }

    @Feature("Navigatsiya")
    @Story("View More tugmalari to'g'ri sahifaga yo'naltiradi")
    @Description("Afisha bo'limidagi 'Batafsil' tugmasi to'g'ri sahifani ochishini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldRedirectAfishaPage_WhenViewMoreButtonClicked(){
        AfishaPage afishaPage = mainPage.clickAfishaViewMoreButton();
        Assert.assertTrue(afishaPage.isPageLoaded(),
                "Afisha page should be loaded with correct URL and header");
    }

    @Feature("Navigatsiya")
    @Story("View More tugmalari to'g'ri sahifaga yo'naltiradi")
    @Description("Salom Toshkent bo'limidagi 'Batafsil' tugmasi to'g'ri sahifani ochib, default tab holatini tekshiradi.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldRedirectSalomToshkentPage_WhenViewMoreButtonClicked(){
        SalomToshkentPage salomTashkentPage = mainPage.clickSalomToshkentViewMoreButton();

        Assert.assertTrue(salomTashkentPage.isPageLoaded(),
                "Salom Toshkent page should be loaded with correct URL and header");

        Assert.assertEquals(salomTashkentPage.getActiveTabName(), "Salom Toshkent",
                "Default active tab should be 'Salom Toshkent'");
    }

    @Test
    public void shouldRedirectServicesPage_WhenViewMoreButtonClicked(){
        ServicesPage servicesPage = mainPage.clickServicesViewMoreButton();

        Assert.assertTrue(servicesPage.isPageLoaded(),
                "Services page should be loaded with correct URL and header");
    }

    @Test
    public void shouldRedirectUsefulLinksPage_WhenViewMoreButtonClicked(){
        UsefulLinksPage usefulLinksPage = mainPage.clickUsefulLinksViewMoreButton();

        Assert.assertTrue(usefulLinksPage.isPageLoaded(),
                "Useful Apps page should be loaded with correct URL and header");
    }

    // =======================================
    //      MA'LUMOTNOMA SECTION TESTS
    // =======================================

    @Test
    public void shouldUyJoyServiceBeAvailable_WhenClicked(){
        mainPage.clickUyJoyService();
        boolean uyJoyServicePopup = mainPage.isServiceWhichRequireAuthDisplayed();

        Assert.assertTrue(uyJoyServicePopup, "Uy-joy service should be available");
    }

    @Test
    public void shouldQaysiMahallaServiceBeAvailable_WhenClicked(){
        mainPage.clickQaysiMahallaService();
        boolean qaysiMahallaServicePopup = mainPage.isServiceWhichRequireMapDisplayed();

        Assert.assertTrue(qaysiMahallaServicePopup, "Qaysi mahalla service should be available");
    }

    @Test
    public void shouldQayerdaIshlashimniBilishServiceBeAvailable_WhenClicked(){
        mainPage.clickQayerdaIshlashimniBilishService();
        boolean qayerdaIshlashimniBilishServicePopup = mainPage.isServiceWhichRequireAuthDisplayed();

        Assert.assertTrue(qayerdaIshlashimniBilishServicePopup, "Qayerda ishlashimni bilish service should be available");
    }

    @Test
    public void shouldAtrofimizdagiNarsalarServiceBeAvailable_WhenClicked(){
        mainPage.clickAtrofimizdagiNarsalarService();
        boolean atrofimizdagiNarsalarServicePopup = mainPage.isServiceWhichRequireMapDisplayed();

        Assert.assertTrue(atrofimizdagiNarsalarServicePopup, "Atrofimizdagi narsalar service should be available");
    }

    @Test
    public void shouldPochtaIndeksServiceBeAvailable_WhenClicked(){
        mainPage.clickPochtaIndeksService();
        boolean pochtaIndeksServicePopup = mainPage.isServiceWhichRequireMapDisplayed();

        Assert.assertTrue(pochtaIndeksServicePopup, "Pochta indeks service should be available");
    }


}
