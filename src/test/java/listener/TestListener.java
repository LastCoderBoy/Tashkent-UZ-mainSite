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
    public void onTestFailure(ITestResult result){
        Object instance = result.getInstance();

        if(instance instanceof BaseTest){
            WebDriver driver = ((BaseTest) instance).getDriver();
            if(driver != null){
                attachScreenshot(driver);
                attachPageSource(driver);
            }
        }

        log.error("Test failed: {}", result.getName());
        log.error("Test failed with exception: {}", result.getThrowable().getMessage());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("✅ PASSED : {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("⏭ SKIPPED: {}", result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("▶ RUNNING : {}", result.getName());
    }

    private void captureScreenshot(WebDriver driver, String testName) {
        try{
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";

            String fullPath = System.getProperty("user.dir") + "/reports/screenshots/" + fileName;
            File destination = new File(fullPath);
            destination.getParentFile().mkdirs(); // create directory if not exists

            FileHandler.copy(source, destination);
            log.info("Screenshot captured for failed test: {} at {}", testName, fullPath);

        } catch (IOException e){
            log.error("Failed to capture screenshot for test: {}", testName, e);
        }
    }

    // ─── Allure attachments ─────────────────────────────────────────

    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source on failure", type = "text/html")
    private byte[] attachPageSource(WebDriver driver) {
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}

