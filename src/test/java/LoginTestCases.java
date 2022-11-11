import Pages.HomePage;
import Pages.LoginPage;
import Pages.OTPPage;
import Pages.OnboardingPage;
import org.mobilx.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTestCases extends BaseTest {

    OnboardingPage onboardingPage;
    LoginPage loginPage;
    OTPPage otpPage;
    HomePage homePage;

    @BeforeMethod()
    public void beforeMethod(Method m) {
        onboardingPage = new OnboardingPage();
        loginPage = new LoginPage();
        otpPage = new OTPPage();
        homePage = new HomePage();
        System.out.println("\n" + "********** starting test:" + m.getName() + "**********" + "\n");
        driver.closeApp();
        driver.launchApp();
    }

    @Test(priority = 1)
    public void isLoginPageLoadedSuccessfully() {
        onboardingPage.clickLogin();
        loginPage.
                isGirisYapTabActive().
                isLoginTextDisplayedCorrectly();
    }

    @Test(priority = 2)
    public void LoginwithRegisteredPhoneNumber() throws InterruptedException {
        onboardingPage.clickLogin();
        loginPage.
                enterRegisteredPhoneNumber();
        otpPage.
                //areWeOnOTPPage().
                        isPhoneNumberCorrect("+905525088895").
                enterOTP().
                clickOnayla().
                acceptAlert();
        homePage.createACustomer();



    }

    @Test(priority = 3)
    public void CheckKoduTekrarGonder() {
        onboardingPage.clickLogin();
        loginPage.
                enterRegisteredPhoneNumber();
        otpPage.
                //areWeOnOTPPage().
                        isPhoneNumberCorrect("+905525088895").
                enterOTP().
                clickKoduTekrarGonder().
                enterOTP();
    }


    @Test(priority = 4)
    public void LoginwithUnregisteredPhoneNumber() {
        onboardingPage.clickLogin();
        loginPage.
                enterUnregisteredPhoneNumber();
        switch (platform) {
            case "Android":
                String a = loginPage.kullaniciyokTxt.getText();
                Assert.assertEquals(a, "Kullanıcı bulunamadı");
            case "iOS":
                String b = loginPage.kullaniciyokTxt.getAttribute("label");
                Assert.assertEquals(b, "Kullanıcı bulunamadı");
        }
    }

    @Test(priority = 5)
    public void leavePhoneNumberEmpty() {
        onboardingPage.clickLogin();
        loginPage.
                clickPhoneField().
                clickLoginBtn();
        switch (platform) {
            case "Android":
                String a = loginPage.errorTxt.getText();
                Assert.assertEquals(a, "* Bu alan boş bırakılamaz.");
            case "iOS":
                String b = loginPage.bosbirakilamaz.getAttribute("label");
                Assert.assertEquals(b, "* Bu alan boş bırakılamaz.");
        }
    }

    @Test(priority = 6)
    public void enterMissingDigitPhone() {
        onboardingPage.clickLogin();
        loginPage.
                enterMissingPhoneNumber();
        switch (platform) {
            case "Android":
                String a = loginPage.errorTxt.getText();
                Assert.assertEquals(a, "* Telefon numarası hatalı.");
            case "iOS":
                String b = loginPage.tlfnhatali.getAttribute("label");
                Assert.assertEquals(b, "* Telefon numarası hatalı.");
        }
    }
}
