package co.com.sofka.page.login;

import co.com.sofka.model.login.LoginModel;
import co.com.sofka.page.common.CommonActionsOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends CommonActionsOnPages {

    private static final Logger LOGGER = Logger.getLogger(Login.class);
    private LoginModel loginModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.
    private final By username = By.id("txtUsername");
    private final By password = By.id("txtPassword");
    private final By login = By.id("btnLogin");

    //For Assertions test case.
    private final By assertionLoginSuccess = By.xpath("//*[@id=\"content\"]/div/div[1]/h1");
    private final By assertionLoginInvalid = By.id("spanMessage");

    public Login(WebDriver driver, LoginModel loginModel) {
        super(driver);
        this.loginModel = loginModel;
    }

    public Login(WebDriver driver, LoginModel loginModel, int secondsForExplicitWait) {

        super(driver, secondsForExplicitWait);

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
