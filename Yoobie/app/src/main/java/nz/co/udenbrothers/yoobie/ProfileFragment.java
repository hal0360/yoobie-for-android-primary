package nz.co.udenbrothers.yoobie;


import android.widget.TextView;

import nz.co.udenbrothers.yoobie.abstractions.RootFragment;
import nz.co.udenbrothers.yoobie.temps.Profile;


public class ProfileFragment extends RootFragment {

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void created() {
        TextView name = findViewById(R.id.profileName);
        TextView mail = findViewById(R.id.profileMail);
        TextView location = findViewById(R.id.profileLocation);
        TextView gender = findViewById(R.id.profileGender);
        TextView phone = findViewById(R.id.profilePhone);
        TextView birth = findViewById(R.id.profileBirth);
        name.setText(Profile.name());
        mail.setText(Profile.mail());
        location.setText(Profile.location());
        gender.setText(Profile.gender());
        phone.setText(Profile.phone());
        birth.setText(Profile.dateOfBirth());

       // clicked(R.id.editButton, v -> parent.pushActivity());
    }
}
