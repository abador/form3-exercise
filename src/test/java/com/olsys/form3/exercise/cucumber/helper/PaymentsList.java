package com.olsys.form3.exercise.cucumber.helper;

import java.io.Serializable;
import java.util.List;

public class PaymentsList implements Serializable {

    private List<Payment> payments;

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
