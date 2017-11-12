package com.cs.trade;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class GeneralTradeRules implements IGeneralTradeRules, ITradeValidator {

    protected List<String> errors = new ArrayList<>();

    public enum Counterparty {
        PLUTO1,
        PLUTO2
    }

    @Override
    public boolean isValidValueDate(Trade trade) {
        boolean isValid = true;
        if (trade.getValueDate() != null && trade.getTradeDate() != null) {
            LocalDate valueDate = LocalDate.parse(trade.getValueDate());
            LocalDate tradeDate = LocalDate.parse(trade.getTradeDate());
            if (valueDate.isBefore(tradeDate)) {
                isValid = false;
                errors.add("Value date cannot be before trade date");

            }
            if (DayOfWeek.SATURDAY.equals(valueDate.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(valueDate.getDayOfWeek())) {
                isValid = false;
                errors.add("Value date cannot fall on the weekends for currency");
            }
        } else {
            isValid = false;
            errors.add("Value date and trade date must be available");
        }
        return isValid;
    }

    @Override
    public boolean isValidCpty(Trade trade) {
        boolean isValid = true;
        try {
            Counterparty.valueOf(trade.getCustomer());
        } catch (IllegalArgumentException e) {
            isValid = false;
            errors.add("Invalid Customer");
        }
        return isValid;
    }

    @Override
    public boolean isValidCurrency(Trade trade) {
        boolean isValid = true;
        String currency = trade.getCcyPair().substring(0, 3);
        try {
            Currency.getInstance(trade.getCcyPair().substring(0, 3));
            Currency.getInstance(trade.getCcyPair().substring(3, trade.getCcyPair().length()));
        } catch (IllegalArgumentException e) {
            isValid = false;
            errors.add("Invalid currency");
        }
        return isValid;
    }

    @Override
    public boolean isValidTrade(Trade trade) {
        return isValidCpty(trade) & isValidCurrency(trade) & isValidValueDate(trade);
    }

    @Override
    public TradeResult validateTrade(Trade trade) {
        return new TradeResult(trade.toString(), errors, this.isValidTrade(trade));
    }
}