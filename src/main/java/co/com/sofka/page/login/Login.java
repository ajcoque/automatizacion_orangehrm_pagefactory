package co.com.sofka.page.login;

import co.com.sofka.model.login.LoginModel;
import co.com.sofka.page.common.CommonActionsOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class Login extends CommonActionsOnPages {

    private static final Logger LOGGER = Logger.getLogger(Login.class);
    private LoginModel loginModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.
    @FindBy(id = "txtUsername")
    @CacheLookup
    private WebElement username;

    @FindBy(id = "txtPassword")
    @CacheLookup
    private WebElement password;

    @FindBy(id = "btnLogin")
    @CacheLookup
    private WebElement login;

    //For Assertions test case.
    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/h1")
    @CacheLookup
    private WebElement assertionLoginSuccess;

    @FindBy(id = "spanMessage")
    @CacheLookup
    private WebElement assertionLoginInvalid;


    public Login(WebDriver driver, LoginModel loginModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.loginModel = loginModel;
    }

    public Login(WebDriver driver, LoginModel loginModel, int secondsForExplicitWait) {

        super(driver, secondsForExplicitWait);
        pageFactoryInitElement(driver, this);

        if (loginModel == null)
            LOGGER.warn(MODEL_NULL_MESSAGE);

        this.loginModel = loginModel;

    }

    //Page functions.

    public void fillLoginForm() {
        try {
            scrollTo(username);
            withExplicitWaitClear(username);
            withExplicitWaitTypeInto(username, loginModel.getUsername());

            scrollTo(password);
            withExplicitWaitClear(password);
            withExplicitWaitTypeInto(password, loginModel.getPassword());

            scrollTo(login);
            clickOn(login);

        } catch (Exception exception) {
            LOGGER.warn(exception.getMessage());
        }
    }

    public String isLoginDone() {
        return getText(assertionLoginSuccess).trim();
    }

    public String isLoginFail() {
        return getText(assertionLoginInvalid).trim();
    }

}
