package com.shonen.ukr.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SinUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSN, edtPasswordSN, edtUserNameLI, edtPasswordLI;
    private Button btnSingUp, btnLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup_login_activity);

        edtUserNameSN = findViewById(R.id.edtUserName);
        edtPasswordSN = findViewById(R.id.edtUserPassword);
        edtUserNameLI = findViewById(R.id.edtUsenNameLogIn);
        edtPasswordLI = findViewById(R.id.edtUserPassLogIn);

        btnSingUp = findViewById(R.id.singUpBtn);
        btnLogIn = findViewById(R.id.logInBtn);

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser addUser = new ParseUser();
                addUser.setUsername(edtUserNameSN.getText().toString());
                addUser.setPassword(edtPasswordSN.getText().toString());

                addUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SinUpLoginActivity.this, addUser.get("username") + " is singed up successfully",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
//                            .setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                            Intent intent = new Intent(SinUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            FancyToast.makeText(SinUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLI.getText().toString(), edtPasswordLI.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(SinUpLoginActivity.this, user.get("username") + " is logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            Intent intent = new Intent(SinUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            FancyToast.makeText(SinUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });
    }
}
