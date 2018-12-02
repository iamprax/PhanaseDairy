package in.prax.phanasedairy.model;

public class CustomerModel {
    String customer_name;
    String customer_mobile_number;

    public CustomerModel(String customer_name, String customer_mobile_number) {
        this.customer_name = customer_name;
        this.customer_mobile_number = customer_mobile_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_mobile_number() {
        return customer_mobile_number;
    }

    public void setCustomer_mobile_number(String customer_mobile_number) {
        this.customer_mobile_number = customer_mobile_number;
    }
}
