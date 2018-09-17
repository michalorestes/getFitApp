package com.jds.fitnessjunkiess.getfitapp.compoundViews.customCheckbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.R;

public class CustomCheckbox extends FrameLayout implements View.OnClickListener, Checkable {

  private boolean checked;
  private Drawable selectedBg;
  private Drawable deselectedBg;
  private String text;
  private FrameLayout layout;
  private TextView itemLabel;

  public CustomCheckbox(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.initialiseViews(context, attrs);
  }

  public CustomCheckbox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.initialiseViews(context, attrs);
  }

  private void initialiseViews(Context context, @Nullable AttributeSet attrs) {
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.cv_custom_checkbox, this);

    this.selectedBg = ContextCompat.getDrawable(context, R.drawable.custom_checkbox_shape_selected);
    this.deselectedBg = ContextCompat.getDrawable(context, R.drawable.custom_checkbox_shape);

    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomCheckbox);
      this.text = a.getString(R.styleable.CustomCheckbox_textLabel);
    }
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    this.layout = this.findViewById(R.id.background);
    this.itemLabel = this.findViewById(R.id.label);

    this.layout.setBackground(this.deselectedBg);
    this.itemLabel.setText(this.text);

    this.setOnClickListener(this);
  }

  private void updateUi(boolean checked) {
    if (checked) {
      this.layout.setBackground(this.selectedBg);
      this.itemLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorWhite));
    } else {
      this.layout.setBackground(this.deselectedBg);
      this.itemLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.secondaryTextColor));
    }
  }

  @Override
  public void setChecked(boolean checked) {
    this.checked = checked;
    this.updateUi(this.checked);
  }

  @Override
  public boolean isChecked() {
    return this.checked;
  }

  @Override
  public void toggle() {
    this.checked = !this.checked;
    this.updateUi(this.checked);
  }

  @Override
  public void onClick(View v) {
    this.toggle();
  }

  public String getValue() {
    return this.itemLabel.getText().toString();
  }
}
