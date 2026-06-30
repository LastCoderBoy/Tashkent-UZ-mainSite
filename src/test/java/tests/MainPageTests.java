package tests;

import base.BaseTest;
import enums.SearchOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class MainPageTests extends BaseTest {

    @Test
    public void shouldOpenKnowYourDistrictPage_WhenClicked() {
        KnowYourDistrictPage knowYourDistrictPage = mainPage.clickKnowYourDistrictMenu();

        String pageUrl = knowYourDistrictPage.getPageUrl();

        // URL assertion - confirms navigation actually happened
        Assert.assertTrue(pageUrl.contains("/uz/districts"),
                "URL should contain /uz/districts after clicking nav link");

        // Header assertion - confirms correct page loaded, not just correct URL
        Assert.assertEquals(knowYourDistrictPage.getPageHeaderText(), "Tumaningizni bilib oling",
                "Page header text mismatch");

        // Tabs assertion - confirms all 12 districts are present
        Assert.assertEquals(knowYourDistrictPage.getTotalDistrictCount(), 12,
                "Expected 12 districts total (visible + dropdown)");
    }

    @Test
    public void shouldOpenNewsPage_WhenClicked() {
        NewsPage newsPage = mainPage.clickNewsMenu();

        String pageUrl = newsPage.getPageUrl();

        Assert.assertTrue(pageUrl.contains("/uz/news"),
                "URL should contain /uz/news after clicking nav link");

        Assert.assertEquals(newsPage.getPageHeaderText(), "TOSHKENTDAN GAPIRAMIZ",
                "Page header text mismatch");

        // Default tab assertion - "Shahar yangiliklari" should be active by default
        Assert.assertEquals(newsPage.getActiveTabName(), "Shahar yangiliklari",
                "Default active news tab should be 'Shahar yangiliklari'");

        Assert.assertTrue(newsPage.getNewsCardCount() > 0,
                "Expected at least one news card to be displayed");

        Assert.assertTrue(newsPage.isPaginationDisplayed(),
                "Pagination should be displayed on the news listing page");

    }

    @Test
    public void shouldOpenOpportunitiesPage_WhenClicked() {
        OpportunitiesPage opportunitiesPage = mainPage.clickOpportunitiesMenu();

        Assert.assertTrue(opportunitiesPage.getPageUrl().contains("/uz/opportunities"),
                "URL should contain /uz/opportunities after clicking nav link");

        Assert.assertEquals(opportunitiesPage.getPageHeaderText(), "Shahar aholisi uchun imkoniyatlar",
                "Page header text mismatch");

    }

    @Test
    public void shouldOpenAnticorruptionPage_WhenClicked() {
        AnticorruptionPage anticorruptionPage = mainPage.clickAnticorruptionMenu();

        Assert.assertTrue(anticorruptionPage.getPageUrl().contains("/uz/anticorruption"),
                "URL should contain /uz/anticorruption after clicking nav link");

        Assert.assertEquals(anticorruptionPage.getPageHeaderText(), "Korrupsiyaga qarshi kurashish",
                "Page header text mismatch");

        Assert.assertEquals(anticorruptionPage.getActiveTabName(), "Tuzilma",
                "Default active tab should be 'Tuzilma'");

        Assert.assertTrue(anticorruptionPage.isPersonCardDisplayed(),
                "Person card should be displayed on anticorruption page");

        Assert.assertTrue(anticorruptionPage.isStructureImageDisplayed(),
                "Structure image should be displayed on anticorruption page");
    }

    @Test
    public void shouldOpenCityPage_WhenClicked() {
        CityPage cityPage = mainPage.clickCityMenu();

        Assert.assertTrue(cityPage.getPageUrl().contains("/uz/city"),
                "URL should contain /uz/city after clicking nav link");

        Assert.assertEquals(cityPage.getPageHeaderText(), "Shahar",
                "Page header text mismatch");

        Assert.assertEquals(cityPage.getActiveTabName(), "Umumiy ma'lumot",
                "Default active tab should be 'Umumiy ma\\'lumot'");

        Assert.assertTrue(cityPage.isDescriptionBlockDisplayed(),
                "Description block should be displayed on city page");

        Assert.assertTrue(cityPage.isCityImageDisplayed(),
                "City image should be displayed on city page");
    }

    @Test
    public void shouldOpenAboutPage_WhenClicked() {
        AboutPage aboutPage = mainPage.clickAboutMenu();

        Assert.assertTrue(aboutPage.getPageUrl().contains("/uz/about"),
                "URL should contain /uz/about after clicking nav link");

        Assert.assertEquals(aboutPage.getPageHeaderText(), "Toshkent shahar hokimiyati haqida",
                "Page header text mismatch");

        Assert.assertEquals(aboutPage.getActiveTabName(), "Hokimiyat haqida",
                "Default active tab should be 'Hokimiyat haqida'");

        Assert.assertTrue(aboutPage.isDescriptionBlockDisplayed(),
                "Description block should be displayed on about page");

        Assert.assertTrue(aboutPage.isAboutImageDisplayed(),
                "About page image should be displayed");
    }

    @Test
    public void shouldOpenDeputiesPage_WhenClicked() {
        DeputiesPage deputiesPage = mainPage.clickDeputiesMenu();

        Assert.assertTrue(deputiesPage.getPageUrl().contains("/uz/deputies"),
                "URL should contain /uz/deputies after clicking nav link");

        Assert.assertEquals(deputiesPage.getPageHeaderText(), "Xalq deputatlari Kengashi",
                "Page header text mismatch");

        Assert.assertEquals(deputiesPage.getActiveTabName(), "Shahar deputatlari",
                "Default active tab should be 'Shahar deputatlari'");

        Assert.assertTrue(deputiesPage.getDeputyCardCount() > 0,
                "Expected at least one deputy card to be displayed");

        Assert.assertTrue(deputiesPage.isPaginationDisplayed(),
                "Pagination should be displayed on deputies page");
    }

    @Test
    public void shouldOpenSocietyPage_WhenClicked() {
        SocietyPage societyPage = mainPage.clickSocietyMenu();

        Assert.assertTrue(societyPage.getPageUrl().contains("/uz/society"),
                "URL should contain /uz/society after clicking nav link");

        Assert.assertEquals(societyPage.getPageHeaderText(), "Fuqarolik jamiyati",
                "Page header text mismatch");

        Assert.assertEquals(societyPage.getActiveTabName(), "Mahalla",
                "Default active tab should be 'Mahalla'");

        Assert.assertEquals(societyPage.getSelectedDistrictTypeValue(), "Barcha tumanlar",
                "Default district filter value should be 'Barcha tumanlar'");

        Assert.assertTrue(societyPage.getMahallaCardCount() > 0,
                "Expected at least one mahalla card to be displayed");

        Assert.assertTrue(societyPage.isPaginationDisplayed(),
                "Pagination should be displayed on society page");
    }


    // ==============================
    //      SEARCH BAR TESTS
    // ==============================

    @Test
    public void shouldShowSearchResults_WhenTextProvided(){
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

        Assert.assertTrue(totalSearchResults > 0, "Expected at least one search result");
    }

}
