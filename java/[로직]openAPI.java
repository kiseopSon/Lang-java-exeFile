import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class openAPI {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("strtYymm","UTF-8") + "=" + URLEncoder.encode("201601", "UTF-8")); /*조회기간 1년이내*/
        urlBuilder.append("&" + URLEncoder.encode("endYymm","UTF-8") + "=" + URLEncoder.encode("201601", "UTF-8")); /*조회기간 1년이내*/
        urlBuilder.append("&" + URLEncoder.encode("hsSgn","UTF-8") + "=" + URLEncoder.encode("1001999090", "UTF-8")); /*품목코드 참조*/
        urlBuilder.append("&" + URLEncoder.encode("cntyCd","UTF-8") + "=" + URLEncoder.encode("US", "UTF-8")); /*국가코드 참조*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}