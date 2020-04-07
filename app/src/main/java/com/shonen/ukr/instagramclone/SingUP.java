package com.shonen.ukr.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SingUP extends AppCompatActivity {

    private EditText edtSingUpEmail, edtSingUpUsername, edtSingUpPassword;
    private Button btnSingUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        edtSingUpEmail = findViewById(R.id.edtSingUpEmail);
        edtSingUpUsername = findViewById(R.id.edtSingUpUserName);
        edtSingUpPassword = findViewById(R.id.edtSingUpPassword);

        btnSingUp = findViewById(R.id.btnSingUp);
        btnLogIn = findViewById(R.id.btnLogIn);
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final ParseUser addNewUser = new ParseUser();
                    addNewUser.setEmail(edtSingUpEmail.getText().toString());
                    addNewUser.setUsername(edtSingUpUsername.getText().toString());
                    addNewUser.setPassword(edtSingUpPassword.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(SingUP.this);
                    progressDialog.setMessage("Singing up " + edtSingUpUsername.getText().toString());
                    progressDialog.show();
                    addNewUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SingUP.this, addNewUser.getUsername() + " was added", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            } else {
                                FancyToast.makeText(SingUP.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                } catch (Exception e) {
                    FancyToast.makeText(SingUP.this, "Please try again", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }

            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingUP.this, LogIn.class);
                startActivity(intent);
            }
        });
    }
}
