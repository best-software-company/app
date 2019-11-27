package pjIII.simova;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

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
                return "invalid";
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    /*
    public String ex() {
        try {
            //StringBuilder urlString = new StringBuilder(baseUrl);
            URL url = new URL(baseUrl);

            Log.i("URL", String.valueOf(url));
            Log.i("MÉTODO", String.valueOf(httpMethod));
            Log.i("TOKEN", String.valueOf(Usuario.getToken()));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Authorization", Usuario.getToken());
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            connection.connect();

            Log.i("STATUS", String.valueOf(connection.getResponseCode()));
            Log.i("MSG", connection.getResponseMessage());

            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                Scanner scanner = new Scanner(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while (scanner.hasNext()) {
                    stringBuffer.append(scanner.nextLine());
                }
                JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                JSONArray jsonArray = (JSONArray) jsonObject.get("eventos");
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject2 = (JSONObject) jsonArray.get(j);
                    eventos.add(jsonObject2.getString("data"));
                    eventos.add(jsonObject2.getString("tipo"));
                    eventos.add(String.valueOf(jsonObject2.getInt("tipo_int")));

                }
                Usuario.setEventos(eventos);
                Log.i("LISTA DE EVENTOS >>>>>", String.valueOf(eventos));
                connection.disconnect();
                return "true";
            }
            if (connection.getResponseCode() == 401 || connection.getResponseCode() == 403) {
                connection.disconnect();
                return "invalid";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String exec() {
        try {
            if (httpMethod == "GET"){
                //StringBuilder urlString = new StringBuilder(baseUrl);
                URL url = new URL(baseUrl);

                Log.i("URL", String.valueOf(url));
                Log.i("MÉTODO", String.valueOf(httpMethod));
                Log.i("TOKEN", String.valueOf(Usuario.getToken()));

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(httpMethod);
                connection.setRequestProperty("Authorization", Usuario.getToken());
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-type", "application/json");
                connection.connect();

                Log.i("STATUS", String.valueOf(connection.getResponseCode()));
                Log.i("MSG", connection.getResponseMessage());

                if (connection.getResponseCode() == 200) {
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    Scanner scanner = new Scanner(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    while (scanner.hasNext()) {
                        stringBuffer.append(scanner.nextLine());
                    }
                    System.out.println(stringBuffer);
                    JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                    Usuario.setCelular_1(jsonObject.getString("celular_1"));
                    Usuario.setCelular_2(jsonObject.getString("celular_2"));
                    Usuario.setFone_res(jsonObject.getString("fone_residencial"));
                    Usuario.setFone_trab(jsonObject.getString("fone_trabalho"));
                    Log.i("CEL 1", Usuario.getCelular_1());
                    Log.i("CEL 2", Usuario.getCelular_2());
                    Log.i("FON 1", Usuario.getFone_res());
                    Log.i("FON 2", Usuario.getFone_trab());

                    connection.disconnect();
                    return "true";
                }
                if (connection.getResponseCode() == 401 || connection.getResponseCode() == 403) {
                    connection.disconnect();
                    return "invalid";

                }
            } if (httpMethod == "PUT"){
                URL url = new URL(baseUrl);

                Log.i("URL", String.valueOf(url));
                Log.i("MÉTODO", String.valueOf(httpMethod));
                Log.i("TOKEN", String.valueOf(Usuario.getToken()));

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(httpMethod);
                connection.setRequestProperty("Authorization", Usuario.getToken());
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-type", "application/json");
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("senha", Usuario.getSenha());
                jsonParam.put("celular_1", Usuario.getCelular_1());
                jsonParam.put("celular_2", Usuario.getCelular_2());
                jsonParam.put("fone_residencial", Usuario.getFone_res());
                jsonParam.put("fone_trabalho", Usuario.getFone_trab());

                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();
                connection.connect();

                Log.i("STATUS", String.valueOf(connection.getResponseCode()));
                Log.i("MSG", connection.getResponseMessage());

                if (connection.getResponseCode() == 200) {
                    connection.disconnect();
                    return "atualizado";
                }else{
                    connection.disconnect();
                    return "false";
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
    */
}
