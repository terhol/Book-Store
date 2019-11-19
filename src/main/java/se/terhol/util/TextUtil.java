package se.terhol.util;

public class TextUtil {

    public String sanitize(String textToSanitize){
        return textToSanitize.replaceAll("\\s+", " ");

    }
}
