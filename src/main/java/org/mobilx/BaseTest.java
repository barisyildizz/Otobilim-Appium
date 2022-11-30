package org.mobilx;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest {

    public static AppiumDriver driver;
    public static AppiumDriverLocalService service;
    protected static String platform;
    protected Properties properties;
    InputStream inputStream;

    public static WebDriverWait wait;


    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @BeforeSuite
    public void beforeSuite() {
        service = getAppiumServerDefault();
        service.start();

    }

    @AfterSuite
    public void afterSuite() {
        service.stop();
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {
        URL url;
        platform = platformName;
        properties = new Properties();
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        properties.load(inputStream);


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("deviceName", deviceName);

        switch (platformName) {
            case "Android":
                desiredCapabilities.setCapability("automationName", properties.getProperty("androidAutomationName"));
                desiredCapabilities.setCapability("appPackage", properties.getProperty("androidAppPackage"));
                desiredCapabilities.setCapability("appActivity", properties.getProperty("androidAppActivity"));
                String AndroidappURL = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator +
                        "apps" + File.separator + "mobilxandroid.apk";
                desiredCapabilities.setCapability("app", AndroidappURL);
                desiredCapabilities.setCapability("avd", properties.getProperty("avd"));
                desiredCapabilities.setCapability("udid", properties.getProperty("Androidudid"));
                desiredCapabilities.setCapability("avdLaunchTimeout", properties.getProperty("avdlaunchtimeout"));

                url = new URL(properties.getProperty("appiumURL"));
                driver = new AndroidDriver(url, desiredCapabilities);
                break;

//            case "iOS":
//                desiredCapabilities.setCapability("automationName", properties.getProperty("iOSAutomationName"));
//                String appURL = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator +
//                        "apps" + File.separator + "OtobilimTest_0.7.0.ipa";
//                desiredCapabilities.setCapability("app", appURL);
//                desiredCapabilities.setCapability("bundleId", properties.getProperty("iOSBundleID"));
//                desiredCapabilities.setCapability("udid", properties.getProperty("iOSudid"));
//                url = new URL(properties.getProperty("appiumURL"));
//                driver = new IOSDriver(url, desiredCapabilities);
//                break;

            case "iOS":
                desiredCapabilities.setCapability("browserstack.user", "timucintuncer_znuMY1");
                desiredCapabilities.setCapability("browserstack.key", "Rkm64pdBkV2skV8pF58K");
                desiredCapabilities.setCapability("app", "bs://c54b836fcf38ef52f49d47800aa71ab55d7a06bb");
                // desiredCapabilities.setCapability("device", "iPhone 14 Pro Max");
                //   desiredCapabilities.setCapability("os_version", "16");
                desiredCapabilities.setCapability("project", "Otobilim iOS");
                desiredCapabilities.setCapability("build", "Otobilim iOS Build 1");
                desiredCapabilities.setCapability("name", "Otobilim iOS Test");
                desiredCapabilities.setCapability("browserstack.local", "true");

                driver = new IOSDriver<IOSElement>(
                        new URL("http://hub-cloud.browserstack.com/wd/hub"), desiredCapabilities);
                break;

//            case "Android":
//                DesiredCapabilities caps = new DesiredCapabilities();
//                caps.setCapability("browserstack.user", "timucintuncer_znuMY1");
//                caps.setCapability("browserstack.key", "Rkm64pdBkV2skV8pF58K");
//                caps.setCapability("app", "bs://62aab837edad6302d2b0a72d125bd4e9aa94c593");
//                caps.setCapability("device", "Samsung Galaxy S21 Ultra");
//                caps.setCapability("os_version", "11.0");
//                caps.setCapability("project", "Otobilim Android");
//                caps.setCapability("build", "Otobilim Android Build 1");
//                caps.setCapability("name", "Otobilim Android Test");
//                caps.setCapability("browserstack.local", "true");
//
//
//                driver = new AndroidDriver<AndroidElement>(
//                        new URL("http://hub.browserstack.com/wd/hub"), caps);
//                break;

            default:
                throw new Exception("Invalid Platform! " + platformName);

        }

    }

    public void waitForVisibility(MobileElement e) {
        wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForClickable(MobileElement e) {
        wait = new WebDriverWait(driver, 20010);
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }

    public void click(MobileElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void sendKey(MobileElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(MobileElement e) {
        switch (platform) {
            case "iOS":
                return getAttribute(e, "label");
            case "Android":
                return getAttribute(e, "text");
        }
        return null;
    }

    public void acceptAlert() throws InterruptedException {
        switch (platform) {
            case "iOS":
                Thread.sleep(3000);
                driver.switchTo().alert().accept();
                break;
            case "Android":
                break;
        }

    }

    public void swipeLeft() {
        final int ANIMATION_TIME = 200;
        switch (platform) {
            case "Android":
                final int PRESS_TIME = 200;
                int edgeBorder = 10;
                PointOption pointOptionStart, pointOptionEnd;
                Dimension dims = driver.manage().window().getSize();
                pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                try {
                    new TouchAction(driver)
                            .press(pointOptionStart)
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                            .moveTo(pointOptionEnd)
                            .release().perform();
                } catch (Exception e) {
                    System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
                    return;
                }
                try {
                    Thread.sleep(ANIMATION_TIME);
                } catch (InterruptedException e) {

                }
                break;


//            case "Android":
//                PointOption pointOptionStart, pointOptionEnd;
//                pointOptionStart = PointOption.point(1391, 320);
//                pointOptionEnd = PointOption.point(57, 234);
//
//                try {
//                    new TouchAction(driver)
//                            .press(pointOptionStart)
//                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(700)))
//                            .moveTo(pointOptionEnd)
//                            .release().perform();
//                } catch (Exception e) {
//                    System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
//                    return;
//                }
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//
//                }
//                break;
            case "iOS":
                final HashMap<String, String> scrollObject = new HashMap<String, String>();
                scrollObject.put("direction", "left");
                try {
                    driver.executeScript("mobile:swipe", scrollObject);
                    Thread.sleep(ANIMATION_TIME);
                } catch (Exception e) {
                    System.err.println("mobileSwipeScreenIOS(): FAILED\n" + e.getMessage());
                    return;
                }
                break;
        }

    }

//    @AfterTest
//    public void tearDown() {
//        driver.quit();
//    }
}
