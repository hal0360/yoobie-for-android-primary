package nz.co.udenbrothers.yoobie;

import android.widget.CheckBox;
import android.widget.RadioGroup;
import nz.co.udenbrothers.yoobie.abstractions.RootFragment;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Setting;

public class SettingFragment extends RootFragment {

    public SettingFragment() {
        super(R.layout.fragment_setting);
    }

    @Override
    public void created() {
        RadioGroup animations = findViewById(R.id.radioGroupAnimation);
        RadioGroup placement = findViewById(R.id.radioGroupTiming);
        CheckBox wifi = findViewById(R.id.wifiCheck);

        if(Setting.animation() == 0) animations.check(R.id.slideIn);
        else if(Setting.animation() == 1) animations.check(R.id.fadeIn);
        else animations.check(R.id.zoomIn);

        animations.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.slideIn) Setting.animation(0);
            else if(checkedId == R.id.fadeIn) Setting.animation(1);
            else Setting.animation(2);
        });

        if(Setting.animation() == 0) placement.check(R.id.afterLock);
        else animations.check(R.id.beforeLock);

        placement.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.afterLock) Setting.timing(0);
            else Setting.timing(1);
        });

        wifi.setChecked(Setting.wifi());

        clicked(wifi,v->{
            if(wifi.isChecked()) Setting.wifi(true);
            else Setting.wifi(false);
        });

        clicked(R.id.logoutButton, v-> {
            Profile.logout();
            parent.toActivity(StartActivity.class);
        });
    }
}
