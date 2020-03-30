package com.shonen.ukr.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class SingUP extends AppCompatActivity {

    private EditText edtName, edtPunchPower, edtPunchSpeed, edtKickPower, edtKickSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        edtName = findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
    }

    public void helloWordTapped(View view) {
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punchValue", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Toast.makeText(SingUP.this,"boxer object was saved",Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
        kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
        kickBoxer.put("kickPower",Integer.parseInt(edtKickPower.getText().toString()));
        kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SingUP.this,"kickboxer object was saved",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
