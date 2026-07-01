package tests;

import config.ConfigReader;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

@Epic("Funksional Testing")
@Feature("Footer")
public class FooterLinksTest {

    private static final String BASE_URL = ConfigReader.getBaseUrl();

    @DataProvider(name = "footerLinks")
    public Object[][] footerLinks() {
        return new Object[][] {
                // ─── Yangiliklar ─────────────────────────────────────────
                {ConfigReader.get("tashkent.news.url"),                         "Yangiliklar"},
                {ConfigReader.get("tashkent.shows.url"),                        "Efirda Toshkent"},
                {ConfigReader.get("tashkent.listening.url"),               "Toshkent tinglaydi"},
                // ─── Korrupsiyaga qarshi ─────────────────────────────────
                {ConfigReader.get("tashkent.anticorruption.url"),               "Korrupsiyaga qarshi"},
                {ConfigReader.get("tashkent.anticorruption.articles.url"),      "Maqolalar"},
                {ConfigReader.get("tashkent.anticorruption.meetings.url"),      "Majlislar"},
                {ConfigReader.get("tashkent.anticorruption.acts.url"),          "Normativ-huquqiy hujjatlar"},
                // ─── Hokimiyat haqida ────────────────────────────────────
                {ConfigReader.get("tashkent.about.url"),                        "Hokimiyat haqida"},
                {ConfigReader.get("tashkent.about.governance.url"),             "Rahbariyat"},
                {ConfigReader.get("tashkent.about.deputies.url"),               "Xalq deputatlari Kengashi"},
                {ConfigReader.get("tashkent.about.central-office.url"),         "Markaziy apparat"},
                {ConfigReader.get("tashkent.about.organizations.url"),          "Tizim tashkilotlari"},
                {ConfigReader.get("tashkent.about.structure.url"),              "Tashkilot tuzilmasi"},
                {ConfigReader.get("tashkent.about.strategy.url"),               "Strategiya"},
                {ConfigReader.get("tashkent.about.symbols.url"),                "Davlat ramzlari"},
                {ConfigReader.get("tashkent.about.press.url"),                  "Matbuot xizmati"},
                {ConfigReader.get("tashkent.about.vacancies.url"),              "Bo'sh ish o'rinlari"},
                {ConfigReader.get("tashkent.about.meetings.url"),               "Majlislar - About"},
                {ConfigReader.get("tashkent.about.documents.url"),              "Hujjatlar"},
                {ConfigReader.get("tashkent.about.contacts.url"),               "Aloqa ma'lumotlari"},
                // ─── Shahar ──────────────────────────────────────────────
                {ConfigReader.get("tashkent.city.url"),                         "Shahar"},
                {ConfigReader.get("tashkent.salom.url"),                        "Salom Toshkent"},
                {ConfigReader.get("tashkent.city.districts.url"),               "Tumanlar"},
                // ─── Fuqarolik jamiyati ──────────────────────────────────
                {ConfigReader.get("tashkent.society.url"),                      "Fuqarolik jamiyati"},
                {ConfigReader.get("tashkent.society.public-council.url"),       "Jamoatchilik kengashi"},
                {ConfigReader.get("tashkent.society.meetings.url"),             "Majlislar - Society"},
                // ─── Boshqa ──────────────────────────────────────────────
                {ConfigReader.get("tashkent.portal-about.url"),                 "Toshkent portali haqida"},
                {ConfigReader.get("tashkent.projects.digital.url"),             "Loyihalar"},
                {ConfigReader.get("tashkent.services.url"),                     "Xizmatlar"},
                {ConfigReader.get("tashkent.opportunities.url"),                "Imkoniyatlar"},
                {ConfigReader.get("tashkent.afisha.url"),                       "Afisha"},
                {ConfigReader.get("tashkent.questions.url"),                    "Ko'p beriladigan savollar"},
        };
    }

    @Story("Footer ichki linklari 200 qaytarishi kerak")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "footerLinks")
    public void shouldReturn2xx_ForInternalFooterLink(String path, String linkLabel) {
        given()
                .redirects().follow(true)   // follow 301/302 redirects automatically
                .when()
                .head(BASE_URL + path)      // HEAD = no body downloaded, just status
                .then()
                .statusCode(lessThan(400)); // 2xx and 3xx are fine, 4xx/5xx are failures
    }

    @Story("Footer tashqi linki mavjud bo'lishi kerak")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void shouldReturn2xx_ForExternalFooterLink() {
        given()
                .redirects().follow(true)
                .when()
                .head("https://opendata.tashkent.uz")
                .then()
                .statusCode(lessThan(400));
    }
}
