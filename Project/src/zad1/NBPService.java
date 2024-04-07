package zad1;

import com.google.gson.Gson;

import java.util.List;

public class NBPService {
    private static NBP[] nbp;

    public static NBP[] getNBPRates(String url) {
        String json = JsonParser.getJsonText(url);
        nbp = new Gson().fromJson(json, NBP[].class);
        return nbp;
    }
    public static double findRateFromSpecificCountry(String url, String currencyCode){
        nbp = getNBPRates(url);
        for(NBPEntry entryCountry : nbp[0].rates){
            if(entryCountry.code.equals(currencyCode)){
                return entryCountry.mid;
            }
        }
        return 0;
    }
}

class NBP {
    List<NBPEntry> rates;
}

class NBPEntry {
    String currency;
    String code;
    double mid;
}

