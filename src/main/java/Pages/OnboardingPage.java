package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.mobilx.BaseTest;
import org.testng.Assert;

import java.time.Duration;

public class OnboardingPage extends BaseTest {

//    @AndroidFindBy(id = "com.opet.mobilx:id/iv_launcher_logo")
//    @iOSXCUITFindBy(accessibility = "main_otobil_logo")
//    protected MobileElement logoImg;

//    @AndroidFindBy(id = "com.opet.mobilx:id/tv_launcher_msg")
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Hoş geldiniz, Mobil X ayrıcalıklarından yararlanmak için\"]")
//    protected MobileElement hosgeldinizTxt;

//    @AndroidFindBy(id = "com.opet.mobilx:id/btn_launcher_login")
//    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Giriş Yap\"`]")
//    protected MobileElement girisYapBtn;
//
//    @AndroidFindBy(id = "com.opet.mobilx:id/btn_launcher_register")
//    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Üye Ol\"`]")
//    protected MobileElement uyeOlBtn;
//
//    @AndroidFindBy(id = "com.opet.mobilx:id/tv_disclaimer")
//    @iOSXCUITFindBy(accessibility = "Mobil X, Opet Petrolcülük A.Ş.’ye aittir.")
//    protected MobileElement disclaimer;

    @AndroidFindBy(id = "tr.com.opet.otobilim.test:id/btn_login")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Giriş Yap\"]")
    protected MobileElement girisbtnonboarding;
    @AndroidFindBy(id = "tr.com.opet.otobilim.test:id/btn_sign_up")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Üye Ol\"]")
    protected MobileElement uyeolbtnboarding;
    @AndroidFindBy(id = "tr.com.opet.otobilim.test:id/tv_on_boarding_desc")
    @iOSXCUITFindBy(xpath = "//*[contains(@name,'.')]")
    public MobileElement descr;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Tek seferlik alım limiti ile araç depo dolum kontrolü sizde olsun.\"")
    public MobileElement iOSilkmesaj;
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Araçtan inmeden hızlı ödeme ve dönemsel fatura ile maliyet avantajı elinizde.\"")
    public MobileElement iOSikincimesaj;
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Kart bilgileriniz OPET tarafından saklanmaz, Masterpass alt yapısında saklanır.\"")
    public MobileElement iOSucuncumesaj;




    public LoginPage clickLogin() {
        click(girisbtnonboarding);
        return new LoginPage();
    }

    public RegisterPage clickRegister() {
        click(uyeolbtnboarding);
        return new RegisterPage();
    }

    //    public OnboardingPage isLogoDisplayed() {
//        Assert.assertTrue(true, String.valueOf(logoImg.isDisplayed()));
//        return this;
//    }
    public OnboardingPage isGirisYapandUyeOlButonDisplayed() {
        Assert.assertTrue(true, String.valueOf(girisbtnonboarding.isDisplayed()));
        String a = girisbtnonboarding.getText();
        System.out.println("Butonun adı " + a);
        Assert.assertTrue(true, String.valueOf(uyeolbtnboarding.isDisplayed()));
        String b = uyeolbtnboarding.getText();
        System.out.println("Butonun adı " + b);
        return this;
    }

//    public OnboardingPage isWelcomeMessageDisplayedCorrectly() {
//        String a = hosgeldinizTxt.getText();
//        System.out.println(a);
//        Assert.assertEquals(a,"Hoş geldiniz, Mobil X ayrıcalıklarından yararlanmak için");
//        return this;
//    }

//    public OnboardingPage isLoginBtnDisplayed() {
//        Assert.assertTrue(true, String.valueOf(girisYapBtn));
//        return this;
//    }
//
//    public OnboardingPage isRegisterBtnDisplayed() {
//        Assert.assertTrue(true, String.valueOf(uyeOlBtn));
//        return this;
//    }

//    public OnboardingPage isDisclaimerMsgDisplayedCorrectly() {
//        String a = disclaimer.getText();
//        Assert.assertEquals(a, "Mobil X, Opet Petrolcülük A.Ş.’ye aittir.");
//        return this;
//    }


}
