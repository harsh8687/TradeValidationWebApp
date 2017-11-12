package com.cs.trade;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trade {

    private String customer;
    private String ccyPair;
    private String type;
    private String direction;
    private String tradeDate;
    private String amount1;
    private String amount2;
    private String rate;
    private String valueDate;
    private String legalEntity;
    private String trader;
    private String style;
    private String excerciseStartDate;
    private String expiryDate;
    private String premiumDate;
    private String deliveryDate;

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getExcerciseStartDate() {
        return excerciseStartDate;
    }

    public void setExcerciseStartDate(String excerciseStartDate) {
        this.excerciseStartDate = excerciseStartDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPremiumDate() {
        return premiumDate;
    }

    public void setPremiumDate(String premiumDate) {
        this.premiumDate = premiumDate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "customer='" + customer + '\'' +
                ", ccyPair='" + ccyPair + '\'' +
                ", type='" + type + '\'' +
                ", direction='" + direction + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", amount1='" + amount1 + '\'' +
                ", amount2='" + amount2 + '\'' +
                ", rate='" + rate + '\'' +
                ", valueDate='" + valueDate + '\'' +
                ", legalEntity='" + legalEntity + '\'' +
                ", trader='" + trader + '\'' +
                ", style='" + style + '\'' +
                ", excerciseStartDate='" + excerciseStartDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", premiumDate='" + premiumDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}