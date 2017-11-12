package com.cs.trade;

public interface ISpotForwardTradeRules {

    public static final String CURRENT_DATE = "2016-10-09";

    public boolean isValidDate(Trade trade);
}
