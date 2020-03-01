package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

public class URLDemo {
    public static void main(String[] args) throws IOException {
        //Tradycyjna metoda
        URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A?format=json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String json = reader.readLine();
        System.out.println(json);
        //można też od razu przekazać openSteam z url
        //JsonReader jsonReader = Json.createReader(url.openStream());
        JsonReader jsonReader = Json.createReader(new StringReader(json));

        //System.out.println(jsonReader.readArray().getJsonObject(0).getString("effectiveDate"));
        List<CurrencyRate> currencies = new ArrayList<>();
        JsonArray arr = jsonReader.readArray().getJsonObject(0).getJsonArray("rates");
        arr.forEach(obj -> {
            JsonObject rate = obj.asJsonObject();
            String currency = rate.getString("currency");
            String code = rate.getString("code");
            BigDecimal mid = rate.getJsonNumber("mid").bigDecimalValue();
            CurrencyRate currencyRate = new CurrencyRate(currency, code, mid);
            currencies.add(currencyRate);
        });

        System.out.println(currencies);
    }
}
