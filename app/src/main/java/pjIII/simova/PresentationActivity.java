package pjIII.simova;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import pjIII.simova.pojo.Usuario;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class PresentationActivity extends AppCompatActivity {

    private Button button_login, button_registro;
    private EditText email;
    private EditText senha;
    private String username;
    private String password;
    public static String ip = "35.247.234.136/hometasks/api/v1";
    private String baseUrl;
    private ProgressBar progressBar;

    private void setUser(){
        AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                Service service = new Service();
                return service.getUser();
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (true) {
                    progressBar.setVisibility(GONE);
                    Toast.makeText(getApplicationContext(), "Bem vindo!", Toast.LENGTH_LONG).show();
                    goToMainActivity();
                }
                else{
                    progressBar.setVisibility(GONE);
                    Toast.makeText(getApplicationContext(), "Falha no login.", Toast.LENGTH_LONG).show();
                }
            }
        };

        task.execute();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        email = (EditText) findViewById(R.id.login);
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

                    AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... voids) {
                            Service service1 = new Service();
                            String result = service1.authUser(username,password);
                            return result;
                        }
                        @Override
                        protected void onPostExecute(String result){
                            super.onPostExecute(result);
                            if (result == "true") {
                                setUser();
                            }
                            else if (result == "false"){
                                progressBar.setVisibility(GONE);
                                Toast.makeText(getApplicationContext(), "Falha no login.", Toast.LENGTH_LONG).show();
                            }else{
                                progressBar.setVisibility(GONE);
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            }
                        }

                    };

                    task.execute();

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

    private void goToRegisterActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
