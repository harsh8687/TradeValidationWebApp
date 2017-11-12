package com.cs.trade;

public interface IOptionsTradeRules {

    public boolean isValidStyle(Trade trade);

    public boolean isValidDate(Trade trade);
}
