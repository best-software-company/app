package pjIII.simova;
import android.util.Log;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Base64;
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

    private String getJson(InputStream inputStream){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            Scanner scanner = new Scanner(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while (scanner.hasNext()) {
                stringBuffer.append(scanner.nextLine());
            }
            System.out.println("STRING BUFFER >>>>>>>" +stringBuffer);
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection prepareCon(String path, String method){
        try{
            String urlString = baseUrl.concat(path);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            if(token !=null)
                connection.setRequestProperty("token", token);
            connection.setRequestMethod(method);
            connection.setConnectTimeout(5000);
            if (method != "GET")
                connection.setDoOutput(true);
            connection.setDoInput(true);
            return connection;
        }catch (Exception e){}

        return null;
    }

    public String authUser(String idUsuario, String senha) {
        try {
            String urlString = "login/";

            String credential = idUsuario.concat(":");
            credential = credential.concat(senha);

            HttpURLConnection connection = prepareCon(urlString,"POST");

            String encode = Base64.encodeToString(credential.getBytes(),Base64.NO_WRAP);
            connection.setRequestProperty("Authorization","Basic "+ encode);

            connection.connect();

            Log.i("STATUS", String.valueOf(connection.getResponseCode()));
            Log.i("MSG", connection.getResponseMessage());

            connection.disconnect();

            if (connection.getResponseCode() == 200) {
                JSONObject jsonObject = new JSONObject(getJson(connection.getInputStream()));
                token = jsonObject.getString("token");
                Log.i("TOKEN", token);
                return "true";
            }
            if (connection.getResponseCode() == 401 || connection.getResponseCode() == 403) {
                JSONObject jsonObject = new JSONObject(getJson(connection.getInputStream()));
                return jsonObject.getString("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String registerUser(Usuario usuario) {
        try {
            HttpURLConnection connection = prepareCon("users/","POST");

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
                JSONObject jsonObject = new JSONObject(getJson(connection.getInputStream()));
                return jsonObject.getString("error");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }


    public String registerTask(Tarefa tarefa) {
        try {

            HttpURLConnection connection = prepareCon("tasks/","POST");

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
                JSONObject jsonObject = new JSONObject(getJson(connection.getInputStream()));
                return jsonObject.getString("error");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }


    public String getTasks(Tarefa tarefa) {
        try {

            HttpURLConnection connection = prepareCon("tasks/","POST");

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
                JSONObject jsonObject = new JSONObject(getJson(connection.getInputStream()));
                return jsonObject.getString("error");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }


    public Usuario[] getUsers(String login) {
        try {

            String path = "users/".concat(login);
            HttpURLConnection connection = prepareCon(path,"GET");

            connection.connect();

            Log.i("STATUS", String.valueOf(connection.getResponseCode()));
            Log.i("MSG", connection.getResponseMessage());

            if (connection.getResponseCode() == 200) {
                Gson gson = new Gson();
                Usuario[] usuarios = gson.fromJson(getJson(connection.getInputStream()), Usuario[].class);
                return usuarios;

            }
            if (connection.getResponseCode() == 404) {
                return null;
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
