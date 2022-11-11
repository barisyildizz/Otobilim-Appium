package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.mobilx.BaseTest;
import org.testng.Assert;

public class LoginPage extends BaseTest {
    @AndroidFindBy(id = "com.opet.mobilx:id/tv_header")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"mobil x\"")
    protected MobileElement uygulamaismi;
    @AndroidFindBy(id = "com.opet.mobilx:id/button_left")
    @iOSXCUITFindBy(accessibility = "Geri")
    protected MobileElement geriBtn;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"•giriş yap\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"● Giriş Yap\"")
    public MobileElement girisYapTab;

    @AndroidFindBy(id = "com.opet.mobilx:id/tv_login_text")
    @iOSXCUITFindBy(accessibility = "Otobilim'e kayıtlı cep telefonunuzu giriniz.")
    protected MobileElement loginTxt;

    @AndroidFindBy(id = "com.opet.mobilx:id/et_custom")
    @iOSXCUITFindBy(xpath = "//*[@value='Cep Telefonu']")
    protected MobileElement tlfnLocator;

    @AndroidFindBy(id = "com.opet.mobilx:id/btn_login")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Giriş Yap']")
    protected MobileElement loginBtn;

    @AndroidFindBy(id = "com.opet.mobilx:id/textinput_error")
    public MobileElement errorTxt;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"* Telefon numarası hatalı.\"]")
    public MobileElement tlfnhatali;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"* Bu alan boş bırakılamaz.\"]")
    public MobileElement bosbirakilamaz;

    @AndroidFindBy(id = "com.opet.mobilx:id/tv_message")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Kullanıcı bulunamadı\"")
    public MobileElement kullaniciyokTxt;


    public LoginPage isGirisYapTabActive() {
        switch (platform) {
            case "Android":
                String a = girisYapTab.getAttribute("content-desc");
                Assert.assertEquals(a, "•giriş yap");
                break;
            case "iOS":
                String b = girisYapTab.getAttribute("label");
                Assert.assertEquals(b, "● Giriş Yap");
                break;
        }

        return this;
    }

    public LoginPage isLoginTextDisplayedCorrectly() {
        switch (platform) {
            case "iOS":
                String a = getAttribute(loginTxt, "label");
                Assert.assertEquals(a, "Otobilim'e kayıtlı cep telefonunuzu giriniz.");
                System.out.println(a);
                break;
            case "Android":
                String b = getAttribute(loginTxt, "text");
                Assert.assertEquals(b, "Otobilim'e kayıtlı cep telefonunuzu giriniz.");
                System.out.println(b);
        }

        return this;
    }

    public LoginPage clickBackBtn() {
        click(geriBtn);
        return this;
    }

    public LoginPage enterUnregisteredPhoneNumber() {
        switch (platform) {
            case "Android":
                click(tlfnLocator);
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_2));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_0));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_9));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_1));
                driver.hideKeyboard();
                click(loginBtn);
                break;
            case "iOS":
                sendKey(tlfnLocator, "525088891");
                clickrandom();
                clickLoginBtn();
                break;
        }
        return null;
    }

    public LoginPage enterRegisteredPhoneNumber() {
        switch (platform) {
            case "Android":
                click(tlfnLocator);
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_2));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_0));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_9));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
                driver.hideKeyboard();
                click(loginBtn);
                break;
            case "iOS":
                sendKey(tlfnLocator, "525088895");
                clickrandom();
                loginBtn.click();
        }
        return null;
    }

    public LoginPage enterMissingPhoneNumber() {
        switch (platform) {
            case "Android":
                click(tlfnLocator);
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
                ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_2));
                driver.hideKeyboard();
                clickrandom();
                break;
            case "iOS":
                sendKey(tlfnLocator, "52");
                clickrandom();
                clickLoginBtn();

        }
        return null;
    }

    public void clickrandom() {
        TouchAction action = new TouchAction<>(driver);
        action.press(PointOption.point(856, 1209)).perform();

    }

    public LoginPage clickLoginBtn() {
        click(loginBtn);
        return this;
    }

    public LoginPage clickPhoneField() {
        click(tlfnLocator);
        clickrandom();
        return this;
    }


}
