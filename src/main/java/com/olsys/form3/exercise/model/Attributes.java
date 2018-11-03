package com.olsys.form3.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Attributes {

    @NotNull
    private String amount;

    @NotNull
    @JsonProperty("beneficiary_party")
    private Object beneficiaryParty;

    @NotNull
    @JsonProperty("charges_information")
    private Object chargesInformation;

    @NotNull
    private String currency;

    @NotNull
    @JsonProperty("debtor_party")
    private Object debtorParty;

    @NotNull
    @JsonProperty("end_to_end_reference")
    private String endToEndReference;

    @NotNull
    private Object fx;

    @NotNull
    @JsonProperty("numeric_reference")
    private String numericReference;

    @NotNull
    @JsonProperty("payment_id")
    private String paymentId;

    @NotNull
    @JsonProperty("payment_purpose")
    private String paymentPurpose;

    @NotNull
    @JsonProperty("payment_scheme")
    private String paymentScheme;

    @NotNull
    @JsonProperty("payment_type")
    private String paymentType;

    @NotNull
    @JsonProperty("processing_date")
    private String processingDate;

    @NotNull
    private String reference;

    @NotNull
    @JsonProperty("scheme_payment_sub_type")
    private String schemePaymentSubType;

    @NotNull
    @JsonProperty("scheme_payment_type")
    private String schemePaymentType;

    @NotNull
    @JsonProperty("sponsor_party")
    private Object sponsorParty;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Object getBeneficiaryParty() {
        return beneficiaryParty;
    }

    public void setBeneficiaryParty(Object beneficiaryParty) {
        this.beneficiaryParty = beneficiaryParty;
    }

    public Object getChargesInformation() {
        return chargesInformation;
    }

    public void setChargesInformation(Object chargesInformation) {
        this.chargesInformation = chargesInformation;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getDebtorParty() {
        return debtorParty;
    }

    public void setDebtorParty(Object debtorParty) {
        this.debtorParty = debtorParty;
    }

    public String getEndToEndReference() {
        return endToEndReference;
    }

    public void setEndToEndReference(String endToEndReference) {
        this.endToEndReference = endToEndReference;
    }

    public Object getFx() {
        return fx;
    }

    public void setFx(Object fx) {
        this.fx = fx;
    }

    public String getNumericReference() {
        return numericReference;
    }

    public void setNumericReference(String numericReference) {
        this.numericReference = numericReference;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getPaymentScheme() {
        return paymentScheme;
    }

    public void setPaymentScheme(String paymentScheme) {
        this.paymentScheme = paymentScheme;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSchemePaymentSubType() {
        return schemePaymentSubType;
    }

    public void setSchemePaymentSubType(String schemePaymentSubType) {
        this.schemePaymentSubType = schemePaymentSubType;
    }

    public String getSchemePaymentType() {
        return schemePaymentType;
    }

    public void setSchemePaymentType(String schemePaymentType) {
        this.schemePaymentType = schemePaymentType;
    }

    public Object getSponsorParty() {
        return sponsorParty;
    }

    public void setSponsorParty(Object sponsorParty) {
        this.sponsorParty = sponsorParty;
    }
}
