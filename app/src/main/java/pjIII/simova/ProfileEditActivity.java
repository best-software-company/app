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

import java.io.Serializable;

import pjIII.simova.pojo.Usuario;

public class ProfileEditActivity extends AppCompatActivity {
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

        final Usuario usuario = (Usuario) this.getIntent().getSerializableExtra("User");
        System.out.println("DEPOIS " + usuario.toString());
        mUser = (EditText) findViewById(R.id.editUser);
        mUser.setText(usuario.getIdUsuario());

        mSenha = (EditText) findViewById(R.id.editPassword);
        mSenha.setText(usuario.getSenha());
        System.out.println(usuario.getSenha());

        mName = (EditText) findViewById(R.id.editName);
        mName.setText(usuario.getNome());

        mPhone = (EditText) findViewById(R.id.editTelefone);
        mPhone.setText(usuario.getTelefone());

        mEmail = (EditText) findViewById(R.id.editEmail);
        mEmail.setText(usuario.getEmail());

        mNascimento = (EditText) findViewById(R.id.editData);
        mNascimento.setText(usuario.getData());

        mFeminino = (RadioButton) findViewById(R.id.feminino);
        mMasculino = (RadioButton) findViewById(R.id.masculino);
        mOutro = (RadioButton) findViewById(R.id.outro);

        if(usuario.getGenero() == "Feminino")
            mFeminino.setChecked(true);

        else if(usuario.getGenero() == "Masculino")
            mMasculino.setChecked(true);
        else
            mOutro.setChecked(true);

        mRegistro = (Button) findViewById(R.id.buttonRegistrar);
        mRegistro.setText("Atualizar");

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
                usuario.setIdUsuario(mUser.getText().toString());
                usuario.setSenha(mSenha.getText().toString());
                usuario.setNome(mName.getText().toString());
                usuario.setTelefone(mPhone.getText().toString());
                usuario.setEmail(mEmail.getText().toString());
                usuario.setData(mNascimento.getText().toString());
                usuario.setGenero(mGender);

                AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... voids) {
                        Service service = new Service();
                        return service.updateUser(usuario);
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