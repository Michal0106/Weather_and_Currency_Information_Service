package zad1;

import com.google.gson.Gson;
import java.util.Map;

public class CurrencyRateService {

    public static Map<String,Double> getRates(String url){
        String json = JsonParser.getJsonText(url);
        Rate rate = new Gson().fromJson(json, Rate.class);
        return rate.conversion_rates;
    }
}

class Rate {
    Map<String,Double> conversion_rates;
}
