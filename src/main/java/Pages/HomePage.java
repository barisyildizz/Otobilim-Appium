package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.mobilx.BaseTest;

public class HomePage extends BaseTest {

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"plus\"`]")
    protected MobileElement sirketTanimlabtn;

    public void createACustomer(){
        click(sirketTanimlabtn);
    }
}
