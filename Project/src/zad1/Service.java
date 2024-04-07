/**
 *
 *  @author Mróz Michał S27673
 *
 */

package zad1;

import javafx.scene.Scene;
import javafx.scene.web.WebView;

import java.util.*;

public class Service {
    private String apiKey;
    private String url;
    private Locale countryCode;
    public Service(String country) {
        countryCode = getCountryCode(country);
    }
    public String getWeather(String town) {
        try {
            apiKey = "e6024bf091597aed041cd4920239cd99";
            url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s", town, countryCode, apiKey);
            return JsonParser.getJsonText(url);
        } catch (Exception e){
            System.out.println("Failed to fetch weather data: " + e.getMessage());
            return null;
        }
    }
    public Double getRateFor(String currency) {
        try {
            apiKey = "e3e1e751fa0030a02e20626c";
            url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s",apiKey,currency);

            String rootCountryCurrency = getCurrencyCode(countryCode);
            Map<String, Double> rates = CurrencyRateService.getRates(url);

            return rates.get(rootCountryCurrency);
        } catch (Exception e) {
            System.out.println("Failed to fetch exchange rate: " + e.getMessage());
            return null;
        }
    }
    public Double getNBPRate() {
        String[] urls = {
                "http://api.nbp.pl/api/exchangerates/tables/a/?format=json",
                "http://api.nbp.pl/api/exchangerates/tables/b/?format=json",
                "http://api.nbp.pl/api/exchangerates/tables/c/?format=json"
        };
        for (String url : urls) {
            double rate = NBPService.findRateFromSpecificCountry(url, getCurrencyCode(countryCode));
            if (rate != 0) return rate;
        }

        return 1.0;
    }
    public Locale getCountryCode(String country){
        Map<String, Locale> map = new HashMap<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            map.put(locale.getDisplayCountry(Locale.ENGLISH), locale);
        }
        return map.get(country);
    }
    public String getCurrencyCode(Locale countryCode){
        return Currency.getInstance(countryCode).getCurrencyCode();
    }
    public void setCountryCode(String country) {
        this.countryCode = getCountryCode(country);
    }
}
