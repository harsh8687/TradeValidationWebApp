package com.cs.trade;

import java.time.LocalDate;

public class SpotForwardTradeRules extends GeneralTradeRules implements ISpotForwardTradeRules, ITradeValidator {

    @Override
    public boolean isValidDate(Trade trade) {
        boolean isValid = true;
        if (trade.getValueDate() != null) {
            LocalDate valueDate = LocalDate.parse(trade.getValueDate());
            LocalDate currentDate = LocalDate.parse(CURRENT_DATE);
            if (ProductType.Spot.equals(ProductType.valueOf(trade.getType()))) {
                if (valueDate.isAfter(currentDate)) {
                    isValid = false;
                    errors.add("Value date should be before current date");
                }

            } else if (ProductType.Forward.equals(ProductType.valueOf(trade.getType()))) {
                if (!valueDate.isAfter(currentDate)) {
                    errors.add("Value date should be after current date");
                    isValid = false;
                }
            }
        } else {
            isValid = false;
            errors.add("Value date not available");
        }

        return isValid;
    }

    @Override
    public boolean isValidTrade(Trade trade) {
        return super.isValidTrade(trade) & isValidDate(trade);
    }

    @Override
    public TradeResult validateTrade(Trade trade) {
        return new TradeResult(trade.toString(), errors, this.isValidTrade(trade));
    }
}