package com.serafinebot.dint.basicwidgets;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";

    private CheckBox checkBox1 = null;
    private CheckBox checkBox2 = null;
    private RadioGroup radioGroup = null;

    private static final Map<String, Integer> RADIO_COLORS = new HashMap<String, Integer>() {{
        put("Red", Color.RED);
        put("Green", Color.GREEN);
        put("Blue", Color.BLUE);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox1 = findViewById(R.id.checkbox1);
        checkBox1.setOnCheckedChangeListener(this);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox2.setOnCheckedChangeListener(this);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private String getOrientation() {
        int orientation = this.getResources().getConfiguration().orientation;
        switch (orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                return "ORIENTATION_LANDSCAPE";
            case Configuration.ORIENTATION_PORTRAIT:
                return "ORIENTATION_PORTRAIT";
            default:
                return "unknown orientation";
        }
    }

    public void didPressBasicButton(View view) {
        Log.i(TAG, "didPressBasicButton: " + getOrientation());
    }

    public void didPressBasicImageButton(View view) {
        Log.i(TAG, "didPressBasicImageButton: " + getOrientation());
        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        editText2.setText(editText1.getText());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.equals(checkBox1)) {
            checkBox1.setText(String.format("Enabled: %s", isChecked ? "true" : false));
        } else if (buttonView.equals(checkBox2)) {
            checkBox2.setText(isChecked ? getOrientation() : "");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.equals(radioGroup)) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                View view = radioGroup.getChildAt(i);
                if (view instanceof RadioButton) {
                    RadioButton radioButton = (RadioButton) view;
                    radioButton.setTextColor(Color.BLACK);
                }
            }
            RadioButton button = findViewById(checkedId);
            button.setTextColor(RADIO_COLORS.get(button.getText()));
        }
    }
}