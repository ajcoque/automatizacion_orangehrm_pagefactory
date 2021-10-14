package co.com.sofka.model.admin.job.paygrades;

import co.com.sofka.model.admin.job.paygrades.currency.CurrencyModel;

public class PayGradesModel extends CurrencyModel {

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
