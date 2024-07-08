package exam01;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {
    public static void main(String[] args) {
        String searchTerm = "Java Programming";
        try {
            String encodedSearchTerm = URLEncoder.encode(searchTerm, "UTF-8");
            String url = "https://example.com/search?q=" + encodedSearchTerm;
            System.out.println("Encoded URL: " + url);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error encoding: " + e.getMessage());
        }
    }
}
