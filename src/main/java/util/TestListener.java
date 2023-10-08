package util;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            takeScreenshot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        saveLogs(result.getMethod().getConstructorOrMethod().getName());
    }


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }

    @Attachment(value = "Stacktrace", type = "text/plain")
    public static String saveLogs(String message) {
        return message;
    }
}
