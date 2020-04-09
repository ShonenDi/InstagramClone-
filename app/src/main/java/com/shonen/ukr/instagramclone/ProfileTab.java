package com.shonen.ukr.instagramclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtBio, edtProfession, edtHobbies, edtFavoriteSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtBio = view.findViewById(R.id.edtBio);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtHobbies = view.findViewById(R.id.edtHobbies);
        edtFavoriteSport = view.findViewById(R.id.edtSport);

        final ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser.get("profileName") != null && parseUser.get("profileBio") != null && parseUser.get("profileProfession") != null
                && parseUser.get("profileHobbies") != null && parseUser.get("profileFavoriteSport") != null) {
            edtProfileName.setText(parseUser.get("profileName").toString());
            edtBio.setText(parseUser.get("profileBio").toString());
            edtProfession.setText(parseUser.get("profileProfession").toString());
            edtHobbies.setText(parseUser.get("profileHobbies").toString());
            edtFavoriteSport.setText(parseUser.get("profileFavoriteSport").toString());
        }

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtBio.getText().toString());
                parseUser.put("profileProfession", edtProfession.getText().toString());
                parseUser.put("profileHobbies", edtHobbies.getText().toString());
                parseUser.put("profileFavoriteSport", edtFavoriteSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (parseUser != null && e == null) {
                            FancyToast.makeText(getContext(), parseUser.getUsername() + " ,your profile information was updated", Toast.LENGTH_SHORT, FancyToast.INFO, false).show();
                        } else {
                            FancyToast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                        }
                    }
                });
            }
        });
        return view;
    }
}
