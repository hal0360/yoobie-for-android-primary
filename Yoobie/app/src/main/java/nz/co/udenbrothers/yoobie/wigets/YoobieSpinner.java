package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import nz.co.udenbrothers.yoobie.R;
import nz.co.udenbrothers.yoobie.interfaces.CmdInt;


public class YoobieSpinner extends RelativeLayout {

    private Context context;
    private ArrayAdapter<String> dataAdapter = null;
    private CmdInt cmdInt;
    private AppCompatSpinner spinner;

    public YoobieSpinner(Context context) {
        super(context);
        setup(context, null);
        this.context = context;
    }

    public YoobieSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
        this.context = context;
    }

    public void selected(CmdInt cmdInt) {
        if(dataAdapter != null) this.cmdInt = cmdInt;
        else throw new ArrayIndexOutOfBoundsException("data not yet initiated");
    }

    public void init(String[] strings){
        dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, strings);
        initAdapter();
    }

    public void init(List<String> strings, int rid){
        dataAdapter = new ArrayAdapter<>(context, rid, strings);
        initAdapter();
    }

    public void init(List<String> strings){
        dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, strings);
        initAdapter();
    }

    public void init(String[] strings, int rid){
        dataAdapter = new ArrayAdapter<>(context, rid, strings);
        initAdapter();
    }

    private void initAdapter(){
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) spinner.getSelectedView()).setTextColor(Color.parseColor("#FFFF00"));
                ((TextView) spinner.getSelectedView()).setTextSize(getResources().getDimension(R.dimen.standardTxtSize));
                cmdInt.exec(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void setup(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.yoobie_spinner_layout, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        spinner = findViewById(R.id.spinner);
        ImageView icon = findViewById(R.id.spinnerImg);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.YoobieSpinner,
                    0, 0);

            Drawable iconImg = null;

            try {
                iconImg = a.getDrawable(R.styleable.YoobieSpinner_spinnerIcon);
            } catch (Exception e) {
                Log.e("YoobieSpinner", "There was an error loading attributes.");
            } finally {
                a.recycle();
            }

            icon.setBackgroundDrawable(iconImg);
        }
    }
}
