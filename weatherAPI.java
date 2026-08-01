import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class weatherAPI {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter City Name : ");
        String city = sc.nextLine();

        try {
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String format = URLEncoder.encode("Weather : %C, Temperature : %t\nThank you!", StandardCharsets.UTF_8);
            String apiUrl = "https://wttr.in/" + encodedCity + "?format=" + format;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String result = response.body();
            String[] data = result.split(",");

                if (data.length >= 2) {
                    System.out.println("\n-----WEATHER DETAILS-----");
                    System.out.println(data[0].trim());
                    System.out.println(data[1].trim());
                } else {
                    System.out.println(result);
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
