package com.cs.trade;

public interface ITradeValidator {
    public boolean isValidTrade(Trade trade);

    public TradeResult validateTrade(Trade trade);
}
