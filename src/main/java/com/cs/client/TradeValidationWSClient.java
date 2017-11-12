package com.cs.client;

import com.cs.trade.TradeResult;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;

public class TradeValidationWSClient {
    public static void main(String[] argv) throws IOException {

        ClassLoader classLoader = TradeValidationWSClient.class.getClassLoader();
        File jsonFile = new File(classLoader.getResource("tradeData.json").getFile());
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder jsonContents = new StringBuilder();
        try {
            is = new FileInputStream(jsonFile);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                jsonContents.append(line);
            }

        } finally {
            if (is != null) {
                is.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (br != null) {
                br.close();
            }
        }

        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/trades");
        List<TradeResult> results = resource.type(MediaType.APPLICATION_JSON).put(new GenericType<List<TradeResult>>() {
        }, jsonContents.toString());

        for (TradeResult result : results) {
            System.out.println("*****************************************************************************************");
            System.out.println(result.getTradeInfo());
            System.out.println("Errors: ");
            if (result.getErrors() != null) {
                for (String error : result.getErrors()) {
                    System.out.println(error);
                }
            }
            System.out.println("Is trade valid? " + result.isValid());
            System.out.println("*****************************************************************************************");
        }
        System.out.println();
    }
}