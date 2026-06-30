package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.primary.MainPage;
import util.JavaScriptUtil;

import java.util.List;

public class SearchResponsePage extends BasePage {

    private final By siteLogo = By.xpath("//img[@alt='Toshkent shahar hokimiyati rasmiy sayti']");
    private final By totalSearchResults = By.xpath("//span[contains(text(), 'natija topildi')]");
    private final By resultBlocks = By.cssSelector(".styles_block__zGNm1");
    private final By resultContents = By.cssSelector(".styles_result__grWJ8 > a");


    public SearchResponsePage(WebDriver driver, JavaScriptUtil jsUtil) {
        super(driver, jsUtil);
    }

    public String getTotalSearchResultsText() {
        return getText(totalSearchResults);
    }

    public int getTotalSearchResults() {
        String totalResultsText = getTotalSearchResultsText();
        String[] parts = totalResultsText.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public MainPage clickSiteLogo() {
        jsUtil.scrollToElementJS(siteLogo);
        click(siteLogo);
        return new MainPage(driver, jsUtil);
    }

    // ================================================
    //              RESULT CONTENT
    // ================================================

    public int getResultBlockCount() {
        return findAll(resultBlocks).size();
    }

    public List<String> getResultContents() {
        return findAll(resultContents).stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Checks whether the search term appears in EITHER the title or the
     * description of at least one result. Case-insensitive, since the
     * site doesn't guarantee exact-case matching in search relevance.
     */
    public boolean anyResultContains(String searchTerm) {
        String term = searchTerm.toLowerCase();

        return getResultContents().stream()
                .anyMatch(title -> title.toLowerCase().contains(term));
    }
}
