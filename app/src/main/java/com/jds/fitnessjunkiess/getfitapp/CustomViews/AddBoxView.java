package com.jds.fitnessjunkiess.getfitapp.CustomViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class AddBoxView extends LinearLayout {

    private Button button;
    private EditText inputTxt;

    public AddBoxView(@NonNull Context context) {
        super(context);
        initializeViews(getContext());
    }

    public AddBoxView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(getContext());
    }

    public AddBoxView
            (@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(getContext());
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.add_box_view, this);
        this.onFinishInflate();
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        this.button = this.findViewById(R.id.button);
        this.inputTxt = this.findViewById(R.id.input_txt);
    }

    public Button getButton() {
        return this.button;
    }

    public EditText getInput() {
        return inputTxt;
    }

    public String getInputText() {
        return this.inputTxt.getText().toString();
    }

    public void requestInputFocus() {
        this.inputTxt.requestFocus();
    }
}
