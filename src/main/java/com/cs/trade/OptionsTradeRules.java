package com.cs.trade;

import java.time.LocalDate;

public class OptionsTradeRules extends GeneralTradeRules implements IOptionsTradeRules, ITradeValidator {

    public enum Style {
        AMERICAN,
        EUROPEAN
    }

    @Override
    public boolean isValidStyle(Trade trade) {
        boolean isValid = true;
        try {
            Style.valueOf(trade.getStyle());
        } catch (IllegalArgumentException e) {
            isValid = false;
            errors.add("Invalid style");
        }
        return isValid;
    }

    @Override
    public boolean isValidDate(Trade trade) {
        boolean isValid = true;

        LocalDate expiryDate = null;
        if (trade.getExpiryDate() != null) {
            expiryDate = LocalDate.parse(trade.getExpiryDate());
        } else {
            isValid = false;
            errors.add("Expiry date not available");
        }
        if (trade.getTradeDate() != null && trade.getExcerciseStartDate() != null && trade.getExpiryDate() != null) {

            LocalDate tradeDate = LocalDate.parse(trade.getTradeDate());
            LocalDate exerciseDate = LocalDate.parse(trade.getExcerciseStartDate());

            if (Style.AMERICAN.equals(Style.valueOf(trade.getStyle())) &&
                    !(exerciseDate.isAfter(tradeDate) && exerciseDate.isBefore(expiryDate))) {
                isValid = false;
                errors.add("For American style Options, exercise date should be after trade date but before expiry date");

            }
        } else {
            isValid = false;
            errors.add("Value date and exercise date are expected to be available");
        }
        if (trade.getExpiryDate() != null && trade.getPremiumDate() != null && trade.getDeliveryDate() != null) {

            LocalDate premiumDate = LocalDate.parse(trade.getPremiumDate());
            LocalDate deliveryDate = LocalDate.parse(trade.getDeliveryDate());

            if (!(expiryDate.isBefore(deliveryDate) && premiumDate.isBefore(deliveryDate))) {
                isValid = false;
                errors.add("Expiry date and premium date should be before delivery date");
            }
        } else {
            isValid = false;
            errors.add("Delivery date and premium date are expected to be available");
        }
        return isValid;
    }

    @Override
    public boolean isValidTrade(Trade trade) {
        return super.isValidTrade(trade) & isValidStyle(trade) && isValidDate(trade);
    }

    @Override
    public TradeResult validateTrade(Trade trade) {
        return new TradeResult(trade.toString(), errors, this.isValidTrade(trade));
    }
}