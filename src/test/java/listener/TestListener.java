package listener;

import base.BaseTest;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();

        if (instance instanceof BaseTest baseTest) {
            WebDriver driver = baseTest.getDriver();
            if (driver != null) {
                attachScreenshot(driver);
                attachPageSource(driver);
            }
        }

        log.error("❌ FAILED  : {}", result.getName());
        log.error("   Reason  : {}", result.getThrowable().getMessage());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("✅ PASSED : {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("⏭ SKIPPED: {}", result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("▶ RUNNING : {}", result.getName());
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source on failure", type = "text/html")
    private byte[] attachPageSource(WebDriver driver) {
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}

