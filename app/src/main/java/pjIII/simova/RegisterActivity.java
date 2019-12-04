package pjIII.simova;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

//RegistroActivity.java
import androidx.appcompat.app.AppCompatActivity;

import pjIII.simova.pojo.Usuario;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUser;
    private EditText mSenha;
    private EditText mName;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mNascimento;
    private String mGender;
    private Button mRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
        final RadioButton mFeminino;
        final RadioButton mMasculino;
        final RadioButton mOutro;
        final Usuario newUser = new Usuario();

        mUser = (EditText) findViewById(R.id.editUser);
        mSenha = (EditText) findViewById(R.id.editPassword);
        mName = (EditText) findViewById(R.id.editName);
        mPhone = (EditText) findViewById(R.id.editTelefone);
        mEmail = (EditText) findViewById(R.id.editEmail);
        mNascimento = (EditText) findViewById(R.id.editData);
        mFeminino = (RadioButton) findViewById(R.id.feminino);
        mMasculino = (RadioButton) findViewById(R.id.masculino);
        mOutro = (RadioButton) findViewById(R.id.outro);
        mRegistro = (Button) findViewById(R.id.buttonRegistrar);

        mFeminino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFeminino.isChecked()) {
                    mGender = "Feminino";
                }
            }

        });
        mMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMasculino.isChecked()) {
                    mGender = "Masculino";
                }
            }
        });

        mOutro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMasculino.isChecked()) {
                    mGender = "Outro";
                }
            }
        });

        mRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser.setIdUsuario(mUser.getText().toString());
                newUser.setSenha(mSenha.getText().toString());
                newUser.setNome(mName.getText().toString());
                newUser.setTelefone(mPhone.getText().toString());
                newUser.setEmail(mEmail.getText().toString());
                newUser.setData(mNascimento.getText().toString());
                newUser.setGenero(mGender);
                newUser.setIdCasa(0);
                newUser.setPontos(0);
                newUser.setPerfil("");

                AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... voids) {
                        Service service = new Service();
                        return service.registerUser(newUser);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if (s == "true") {
                            Toast.makeText(getApplicationContext(), "Usuário Cadastrado", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else if (s == "false"){
                            Toast.makeText(getApplicationContext(), "Falha no login", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                        }
                    }
                };

                task.execute();

            }
        });

    }

}
