package co.com.sofka.stepdefinition.common;

import co.com.sofka.model.login.LoginModel;
import co.com.sofka.page.login.Login;
import co.com.sofka.stepdefinition.setup.WebUI;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class LoginSuccess extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(LoginSuccess.class);
    private LoginModel loginModel;
    private Login login;

    public Login successLogin(WebDriver driver) {
        try {
            dataConfigurationSuccess();
            login = new Login(driver, loginModel, TEN_SECONDS.getValue());
            login.fillLoginForm();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
        return login;
    }

    private void dataConfigurationSuccess(){
        loginModel = new LoginModel();
        loginModel.setUsername("admin");
        loginModel.setPassword("admin123");
    }

}
