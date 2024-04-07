package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public interface JsonParser {
    static String getJsonText(String url){
        String json;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new URI(url).toURL().openConnection().getInputStream()))) {
            json = reader.lines().collect(Collectors.joining());
        }
        catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
