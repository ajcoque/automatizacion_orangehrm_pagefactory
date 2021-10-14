package co.com.sofka.page.paygrades;

import co.com.sofka.model.admin.job.paygrades.PayGradesModel;
import co.com.sofka.page.common.CommonActionsOnPages;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class PayGrades extends CommonActionsOnPages {

    private static final Logger LOGGER = Logger.getLogger(PayGrades.class);
    private PayGradesModel payGradesModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    @FindBy(id = "menu_admin_viewAdminModule")
    //@CacheLookup
    private WebElement linkTextAdmin;

    @FindBy(id = "menu_admin_Job")
    //@CacheLookup
    private WebElement linkTextJob;

    @FindBy(id = "menu_admin_viewPayGrades")
    //@CacheLookup
    private WebElement linkTextPayGrades;

    @FindBy(id = "menu_admin_viewAdminModule")
    @CacheLookup
    private WebElement linkTextAdmin2;

    @FindBy(id = "menu_admin_Job")
    @CacheLookup
    private WebElement linkTextJob2;

    @FindBy(id = "menu_admin_viewPayGrades")
    @CacheLookup
    private WebElement linkTextPayGrades2;

    //For input test cases.
    @FindBy(id = "btnAdd")
    @CacheLookup
    private WebElement buttonAdd;

    @FindBy(id = "payGrade_name")
    @CacheLookup
    private WebElement inputName;

    @FindBy(id = "btnSave")
    @CacheLookup
    private WebElement buttonSave;

    @FindBy(id = "btnAddCurrency")
    @CacheLookup
    private WebElement buttonAddCurrency;

    @FindBy(id = "payGradeCurrency_currencyName")
    @CacheLookup
    private WebElement inputNameCurrency;

    @FindBy(id = "payGradeCurrency_minSalary")
    @CacheLookup
    private WebElement inputMinSalary;

    @FindBy(id = "payGradeCurrency_maxSalary")
    @CacheLookup
    private WebElement inputMaxSalary;

    @FindBy(id = "btnSaveCurrency")
    @CacheLookup
    private WebElement buttonSaveCurrency;

    @FindBy(xpath = "/html/body/div[4]/ul/li")
    @CacheLookup
    private WebElement selectNameCurrency;


    //For Assertions test case.
    //locate the new added pay grade
    @FindBy(linkText = "Grade A")
    @CacheLookup
    private WebElement assertionLinkTextNewPayGrade;

    @FindBy(linkText = "Colombian Peso")
    @CacheLookup
    private WebElement assertionNewPayGradeCurrency;

    @FindBy(xpath = "//*[@id=\"tblCurrencies\"]/tbody/tr/td[2]/a")
    @CacheLookup
    private WebElement assertionNameCurrency;


    public PayGrades(WebDriver driver, PayGradesModel payGradesModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.payGradesModel = payGradesModel;
    }

    public PayGrades(WebDriver driver, PayGradesModel payGradesModel, int secondsForExplicitWait) {

        super(driver, secondsForExplicitWait);
        pageFactoryInitElement(driver, this);

        if (payGradesModel == null)
            LOGGER.warn(MODEL_NULL_MESSAGE);

        this.payGradesModel = payGradesModel;

    }

    //Page functions.
    public void fillPayGradeForm() {
        try {
            scrollTo(buttonAdd);
            clickOn(buttonAdd);

            scrollTo(inputName);
            withExplicitWaitClear(inputName);
            withExplicitWaitTypeInto(inputName, payGradesModel.getName());

            scrollTo(buttonSave);
            clickOn(buttonSave);

        } catch (Exception exception) {
            LOGGER.warn(exception.getMessage());
        }
    }

    public void fillCurrencyForm() {
        try {
            scrollTo(buttonAddCurrency);
            clickOn(buttonAddCurrency);

            scrollTo(inputNameCurrency);
            withExplicitWaitClear(inputNameCurrency);
            withExplicitWaitTypeInto(inputNameCurrency, payGradesModel.getNameCurrency());

            actionMove(selectNameCurrency);
            clickOn(selectNameCurrency);

            scrollTo(inputMinSalary);
            withExplicitWaitClear(inputMinSalary);
            withExplicitWaitTypeInto(inputMinSalary, payGradesModel.getMinimumSalary());

            scrollTo(inputMaxSalary);
            withExplicitWaitClear(inputMaxSalary);
            withExplicitWaitTypeInto(inputMaxSalary, payGradesModel.getMaximumSalary());

            scrollTo(buttonSaveCurrency);
            clickOn(buttonSaveCurrency);

        } catch (Exception exception) {
            LOGGER.warn(exception.getMessage());
        }
    }

    public void moveToSection() {
        scrollTo(linkTextAdmin);
        actionMove(linkTextAdmin);
        actionMove(linkTextJob);
        actionMove(linkTextPayGrades);
        clickOn(linkTextPayGrades);
    }

    public List<String> isRegistrationCurrencyDone() {
        List<String> submitedFormResult = new ArrayList<>();
        submitedFormResult.add(getText(assertionNameCurrency).trim());
        //submitedFormResult.add(getText(assertionMinSalary).trim());
        //submitedFormResult.add(getText(assertionMaxSalary).trim());
        return submitedFormResult;
    }

    public String isRegistrationPayGradeDone() {
        return (getText(assertionLinkTextNewPayGrade).trim());
    }

}
