package com.cs.trade;

public interface IGeneralTradeRules {

    public boolean isValidValueDate(Trade trade);

    public boolean isValidCpty(Trade trade);

    public boolean isValidCurrency(Trade trade);
}