package com.cs.service;

import com.cs.trade.*;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class TradeValidationWS {

    @GET
    @Path("/serverStatus")
    @Produces(MediaType.TEXT_PLAIN)
    public String isServerRunning() {
        return "The server is available";
    }

    @PUT
    @Path("/trades")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TradeResult> validateTrade(String jsonContents) throws IOException {
        Trade[] trades = null;
        try {
            trades = (new ObjectMapper()).readValue(jsonContents, Trade[].class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        List<TradeResult> results = new ArrayList<>(trades.length);
        for (Trade trade : trades) {
            ITradeValidator validator;
            TradeResult result = null;
            try {
                switch (ProductType.valueOf(trade.getType())) {
                    case Forward:
                        validator = new SpotForwardTradeRules();
                        result = validator.validateTrade(trade);
                        break;
                    case Spot:
                        validator = new SpotForwardTradeRules();
                        result = validator.validateTrade(trade);
                        break;
                    case VanillaOption:
                        validator = new OptionsTradeRules();
                        result = validator.validateTrade(trade);
                        break;
                }
            } catch (IllegalArgumentException e) {
                List<String> errors = new ArrayList<>(1);
                errors.add("Invalid Product type");
                result = new TradeResult(trade.toString(), errors, false);
            }
            results.add(result);
        }
        return results;
    }
}