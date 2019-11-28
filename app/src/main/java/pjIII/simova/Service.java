package pjIII.simova;
import android.util.Log;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import pjIII.simova.pojo.Tarefa;
import pjIII.simova.pojo.Usuario;

/**
 * Created by user on 12/8/17.
 */

public class Service{

    private static String baseUrl = "http://35.247.234.136/hometasks/api/v1/";
    private static String token;
    private String error;

    public Service() {
        // This is important. The application may break without this line.
        System.setProperty("jsse.enableSNIExtension", "false");
    }


    /**
     * Make the call to the Rest API and return its response as a string.
     *
     * @return String
     */

    private JSONObject getJson(InputStream inputStream){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            Scanner scanner = new Scanner(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while (scanner.hasNext()) {
                stringBuffer.append(scanner.nextLine());
            }
            System.out.println("STRING BUFFER >>>>>>>" +stringBuffer);
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String authUser(String idUsuario, String senha) {
        try {
            String urlString = baseUrl.concat("login/");

            //TODO pass login info to http body
            urlString = urlString.concat(idUsuario);
            urlString = urlString.concat(":");
            urlString = urlString.concat(senha);

            URL url = new URL(urlString);

            Log.i("URL", String.valueOf(url));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("idUsuario", idUsuario);
            jsonParam.put("senha", senha);

            Log.i("JSON", jsonParam.toString());

            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            connection.connect();

            Log.i("STATUS", String.valueOf(connection.getResponseCode()));
            Log.i("MSG", connection.getResponseMessage());

            if (connection.getResponseCode() == 200) {
                JSONObject jsonObject = this.getJson(connection.getInputStream());
                token = jsonObject.getString("token");
                Log.i("TOKEN", token);
                return "true";
            }
            if (connection.getResponseCode() == 401 || connection.getResponseCode() == 403) {
                JSONObject jsonObject = this.getJson(connection.getInputStream());
                return jsonObject.getString("error");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String registerUser(Usuario usuario) {
        try {
            String urlString = baseUrl.concat("users/");

            URL url = new URL(urlString);

            Log.i("URL", String.valueOf(url));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String usuarioString = gson.toJson(usuario);

            Log.i("JSON", usuarioString);

            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(usuarioString);

            os.flush();
            os.close();

            connection.connect();

            Log.i("STATUS", String.valueOf(connection.getResponseCode()));
            Log.i("MSG", connection.getResponseMessage());

            if (connection.getResponseCode() == 200) {
                return "true";
            }
            if (connection.getResponseCode() == 400 || connection.getResponseCode() == 404) {
                JSONObject jsonObject = this.getJson(connection.getInputStream());
                return jsonObject.getString("error");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }


    public String registrarTarefa(Tarefa tarefa) {
        try {
            String urlString = baseUrl.concat("tasks/");

            URL url = new URL(urlString);

            Log.i("URL", String.valueOf(url));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("token", token);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String usuarioString = gson.toJson(tarefa);

            Log.i("JSON", usuarioString);

            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(usuarioString);

            os.flush();
            os.close();

            connection.connect();

            Log.i("STATUS", String.valueOf(connection.getResponseCode()));
            Log.i("MSG", connection.getResponseMessage());

            if (connection.getResponseCode() == 201) {
                return "true";
            }
            if (connection.getResponseCode() == 400 || connection.getResponseCode() == 404) {
                JSONObject jsonObject = this.getJson(connection.getInputStream());
                return jsonObject.getString("error");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
}
