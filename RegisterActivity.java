package pjIII.simova;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

class RegisterActivity extends AppCompatActivity {
    private EditText mUser;
    private EditText mSenha;
    private EditText mName;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mNascimento;
    private String mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RadioButton mFeminino;
        final RadioButton mMasculino;

        mUser = (EditText) findViewById(R.id.editUser);
        mSenha = (EditText) findViewById(R.id.editPassword);
        mName = (EditText) findViewById(R.id.editName);
        mPhone = (EditText) findViewById(R.id.editTelefone);
        mEmail = (EditText) findViewById(R.id.editEmail);
        mNascimento = (EditText) findViewById(R.id.editData);
        mFeminino = (RadioButton) findViewById(R.id.checkBoxF);
        mMasculino = (RadioButton) findViewById(R.id.checkBoxM);

        mFeminino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFeminino.isChecked()) {
                    mGender = "feminino";
                }

            }

        });
        mMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMasculino.isChecked()) {
                    mGender = "masculino";
                }
            }
        });
    }
}
