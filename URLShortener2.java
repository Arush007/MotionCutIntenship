import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLShortener2 {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the long URL to shorten:");
            String longUrl = reader.readLine();

            String accessToken = "735719eaaf5e8d48d423f972ba1a62c43521e919";
            String shortUrl = shortenUrl(longUrl, accessToken);

            if (shortUrl != null) {
                System.out.println("Shortened URL: " + shortUrl);
            } else {
                System.out.println("URL shortening failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String shortenUrl(String longUrl, String accessToken) throws IOException {
        String apiUrl = "https://api-ssl.bitly.com/v4/shorten";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        // Create the JSON request body
        String jsonInputString = "{\"long_url\": \"" + longUrl + "\"}";

        // Write the JSON request body to the connection
        connection.getOutputStream().write(jsonInputString.getBytes("UTF-8"));

        int responseCode = connection.getResponseCode();

        // Print the complete response for debugging purposes
        System.out.println("Bitly API Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Print the complete response for debugging purposes
        System.out.println("Bitly API Response: " + response.toString());

        if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK) {
            // Parse the JSON response to get the shortened URL
            int startIndex = response.indexOf("id") + 6;
            int endIndex = response.indexOf("\"", startIndex);
            return response.substring(startIndex, endIndex);
        } else {
            // Print the error response for debugging purposes
            System.out.println("Bitly API Error Response: " + response.toString());
            return null;
        }
    }
}

