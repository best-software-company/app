package pjIII.simova;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import pjIII.simova.pojo.Tarefa;
import pjIII.simova.pojo.Usuario;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    private Button button_login, button_registro;
    private EditText email;
    private EditText senha;
    private String username;
    private String password;
    public static String ip = "35.247.234.136/hometasks/api/v1";
    private String baseUrl;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        button_login = (Button) findViewById(R.id.button);
        button_registro = (Button) findViewById(R.id.button2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(GONE);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    progressBar.setVisibility(VISIBLE);

                    username = email.getText().toString();
                    password = senha.getText().toString();


                    Service service = new Service();

                    AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(service);
                    execute.execute();
                } catch (Exception ex) {
                }
            }
        });

        button_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setMessage("Deseja realmente sair do aplicativo?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    /**
     * Open a new activity window.
     */
    private void goToTasksActivity() {
        Intent intent = new Intent(this, TasksActivity.class);
        startActivity(intent);
    }
    /**
     * Open a new activity window.
     */
    private void goToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public class ExecuteNetworkOperation extends AsyncTask<Void, Void, String> {

        private Service service;
        private String isValidCredentials;
        /**
         * Overload the constructor to pass objects to this class.
         */
        public ExecuteNetworkOperation(Service service) {
            this.service = service;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                isValidCredentials = service.authUser(username, password);
                Usuario usuario = new Usuario("String idUsuario", "String nome", "1909-02-02",
                        "String genero", 0, "String telefone",
                        "String senha", "String email", "String perfil", 0, null);
                service.registerUser(usuario);
                Tarefa tarefa = new Tarefa("String nome", "String descricao", "luluzinha", "Roque1", "aberta", "1902-02-02", 0);
                service.registrarTarefa(tarefa);
                } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            // Login Success
            if (isValidCredentials == "true"){
                progressBar.setVisibility(GONE);
                Toast.makeText(getApplicationContext(), "Bem vindo!", Toast.LENGTH_LONG).show();
                //goToTasksActivity();
            }else {// Login Failure
                progressBar.setVisibility(GONE);
                Toast.makeText(getApplicationContext(), "Falha no login", Toast.LENGTH_LONG).show();
            }
        }
    }
}
