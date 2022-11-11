package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.mobilx.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class OTPPage extends BaseTest {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Bildirim']")
    protected MobileElement bildirim;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='sms onay']")
    protected MobileElement smsonaypageTxt;
    @iOSXCUITFindBy(xpath = "//*[contains(@name,'giriniz')]")
    protected MobileElement tlfnTxt;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='öneri']")
    protected MobileElement clickOTPcode;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Onayla']")
    protected MobileElement OnaylaBtn;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Kodu Tekrar Gönder']")
    protected MobileElement KoduTekrarGonder;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='00:00']")
    protected MobileElement sayac;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]")
    public MobileElement OTPfield;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"CapsuleViewController\"]/XCUIElementTypeOther[3]")
    public MobileElement iOSURLField;
    @iOSXCUITFindBy(iOSNsPredicate = "type == \"XCUIElementTypeTextField\"")
    public MobileElement username;
    @iOSXCUITFindBy(iOSNsPredicate = "type == \"XCUIElementTypeSecureTextField\"")
    public MobileElement password;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Sign in\"]")
    public MobileElement signInbtn;



    public OTPPage isPhoneNumberCorrect(String phone) {
        waitForVisibility(tlfnTxt);
        String a = tlfnTxt.getText();
        Assert.assertEquals(a, phone + " nolu telefonunuza gelen kodu giriniz");
        return this;
    }

//    public OTPPage areWeOnOTPPage(){
//        waitForVisibility(smsonaypageTxt);
//        String a = smsonaypageTxt.getAttribute("label");
//        Assert.assertEquals(a,"sms onay");
//        return this;
//    }

    public OTPPage enterOTP() {
        click(clickOTPcode);
        return this;
    }

    public OTPPage clickOnayla() {
        click(OnaylaBtn);
        return this;
    }

    public OTPPage clickKoduTekrarGonder(){
        waitForClickable(KoduTekrarGonder);
        click(KoduTekrarGonder);
        click(OTPfield);
        return this;
    }

}
