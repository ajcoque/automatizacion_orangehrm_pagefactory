package co.com.sofka.stepdefinition.paygrade;

import co.com.sofka.model.admin.job.paygrades.PayGradesModel;

import co.com.sofka.page.paygrades.PayGrades;
import co.com.sofka.stepdefinition.common.LoginSuccess;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class PayGradesCucumberStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(PayGradesCucumberStepDefinition.class);
    private PayGradesModel payGradesModel;
    private PayGrades payGrades;
    private LoginSuccess loginSuccess;

    private static final String ASSERTION_EXCEPTION_MESSAGE = "El aplicativo guarda los datos del grado de pago";
    private static final String ASSERTION_EXCEPTION_MESSAGE_CURRENCY = "El aplicativo no guarda los datos de la moneda";

    @Given("el administrador del sistema se encuentra en la seccion de Pay Grade")
    public void elAdministradorDelSistemaSeEncuentraEnLaSeccionDePayGrade() {
        try {
            generalSetUp();
            loginSuccess = new LoginSuccess();
            loginSuccess.successLogin(driver).fillLoginForm();
            dataConfigurationPayGrade();
            payGrades = new PayGrades(driver, payGradesModel, TEN_SECONDS.getValue());
            loginSuccess.successLogin(driver);
            payGrades.moveToSection();

        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @When("el administrador ingresa un name de grado de pago NO existente y presiona en el boton agregar,adicionalmente agrega los datos de la moneda: nombre, salario minimo y maximo y presiona guardar")
    public void elAdministradorIngresaUnNameDeGradoDePagoNOExistenteYPresionaEnElBotonAgregarAdicionalmenteAgregaLosDatosDeLaMonedaNombreSalarioMinimoYMaximoYPresionaGuardar() {
        try {

            payGrades.fillPayGradeForm();
            payGrades.fillCurrencyForm();
            Assertions.assertEquals(
                    expectedCurrency(),
                    payGrades.isRegistrationCurrencyDone(),
                    ASSERTION_EXCEPTION_MESSAGE_CURRENCY
            );
            payGrades.moveToSection();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @Then("el sistema debera mostrar los datos de grado de pago guardados en el sistema.")
    public void elSistemaDeberaMostrarLosDatosDeGradoDePagoGuardadosEnElSistema() {
        try {
            Assertions.assertEquals(
                    payGradesModel.getName(),
                    payGrades.isRegistrationPayGradeDone(),
                    ASSERTION_EXCEPTION_MESSAGE
            );
            quitDriver();
        } catch (Exception exception) {
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    private void dataConfigurationPayGrade() {
        payGradesModel = new PayGradesModel();
        payGradesModel.setName("Grade A");
        payGradesModel.setNameCurrency("Colombian Peso");
        payGradesModel.setMinimumSalary("1000000");
        payGradesModel.setMaximumSalary("10000000");
    }

    private List<String> expectedCurrency() {
        List<String> submitedFormResult = new ArrayList<String>();
        submitedFormResult.add(payGradesModel.getNameCurrency());
        //submitedFormResult.add(payGradesModel.getMinimumSalary());
        //submitedFormResult.add(payGradesModel.getMaximumSalary());
        return submitedFormResult;
    }


}
