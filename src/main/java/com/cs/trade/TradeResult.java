package com.cs.trade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class TradeResult {

    @XmlElement(name = "TradeInfo")
    private String tradeInfo;

    @XmlElement(name = "Errors")
    private List<String> errors;

    @XmlElement(name = "IsValid")
    private boolean isValid;

    public TradeResult() {
    }

    public TradeResult(String tradeInfo, List<String> errors, boolean isValid) {
        this.tradeInfo = tradeInfo;
        this.errors = errors;
        this.isValid = isValid;
    }

    public String getTradeInfo() {
        return tradeInfo;
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean isValid() {
        return isValid;
    }
}