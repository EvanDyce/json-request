import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class request {
    public static void main(String[] args) {
        request req = new request();

        JSONObject response = req.makeRequest("https://wger.de/api/v2/exerciseinfo/?language=2");
        System.out.println(response);
    }
    private JSONObject makeRequest(String url_string) {
        try {
            URL url = new URL(url_string);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            InputStream response = connection.getInputStream();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(response, "UTF-8"));
            return jsonObject;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new JSONObject();
    }
}
