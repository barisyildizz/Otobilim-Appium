import Pages.OnboardingPage;
import org.mobilx.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class OnboardingTestCases extends BaseTest {


    OnboardingPage onboardingPage;

    @BeforeMethod
    public void beforeMethod(Method m) {
        onboardingPage = new OnboardingPage();
        System.out.println("\n" + "********** starting test:" + m.getName() + "**********" + "\n");
        driver.closeApp();
        driver.launchApp();
    }

    @Test(priority = 1)
    public void swipeAndVerifyOnboardingMessages() {
        switch (platform) {
            case "Android":
                String a = getText(onboardingPage.descr);
                Assert.assertEquals(a, "Tek seferlik alım limiti ile araç depo dolum kontrolü sizde olsun.");
                System.out.println("İlk sayfadaki metin: " + a);
                swipeLeft();
                String b = getText(onboardingPage.descr);
                Assert.assertEquals(b, "Araçtan inmeden hızlı ödeme ve dönemsel fatura ile maliyet avantajı elinizde.");
                System.out.println("İkinci sayfadaki metin: " + b);
                swipeLeft();
                String c = getText(onboardingPage.descr);
                Assert.assertEquals(c, "Kart bilgileriniz OPET tarafından saklanmaz, Masterpass alt yapısında saklanır.");
                System.out.println("Üçüncü sayfadaki metin: " + c);
                break;
            case "iOS":
                String d = getText(onboardingPage.iOSilkmesaj);
                Assert.assertEquals(d, "Tek seferlik alım limiti ile araç depo dolum kontrolü sizde olsun.");
                System.out.println("İlk sayfadaki metin: " + d);
                swipeLeft();
                String e = getText(onboardingPage.iOSikincimesaj);
                Assert.assertEquals(e, "Araçtan inmeden hızlı ödeme ve dönemsel fatura ile maliyet avantajı elinizde.");
                System.out.println("İkinci sayfadaki metin: " + e);
                swipeLeft();
                String f = getText(onboardingPage.iOSucuncumesaj);
                Assert.assertEquals(f, "Kart bilgileriniz OPET tarafından saklanmaz, Masterpass alt yapısında saklanır.");
                System.out.println("Üçüncü sayfadaki metin: " + f);
        }
        //onboardingPage.isGirisYapandUyeOlButonDisplayed();
    }

    @Test(priority = 2)
    public void goToLoginPage() {
        onboardingPage.
                clickLogin();
    }

    @Test(priority = 3)
    public void goToRegisterPage() {
        onboardingPage.clickRegister();

    }

}
