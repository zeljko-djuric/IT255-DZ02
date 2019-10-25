
package dz02;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DZ02 {

    public static void main(String[] args) {
 POST();
        try {
            URL url = new URL("http://89.216.56.107:8080/restfull/rest/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    
}
public static void POST() {

        Korisnik user = new Korisnik();
        user.setEmailConfirmed(false);
        user.setEmailConfirmationCode("11771177");
        user.setEmail("zeljko94@gmail.com");
        user.setPassword("*#1dsapaswqer~");
        user.setFullName("Zeljko Djuric");

        try {
            URL url = new URL("http://89.216.56.107:8080/restfull/rest/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Method", "POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(new Gson().toJson(user));
            pw.close();
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}