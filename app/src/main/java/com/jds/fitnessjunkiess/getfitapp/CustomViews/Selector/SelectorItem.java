package com.jds.fitnessjunkiess.getfitapp.CustomViews.Selector;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.CustomViews.Selector.OnItemToggledListener;
import com.jds.fitnessjunkiess.getfitapp.R;

public class SelectorItem extends LinearLayout implements View.OnClickListener {

  ImageView itemIcon;
  TextView itemLabel;
  Drawable selectedIcon;
  Drawable deselectedIcon;
  String text;
  boolean checked;
  OnItemToggledListener itemToggledListener;

  public SelectorItem(Context context) {
    super(context);
    this.initialiseViews(context);
  }

  public SelectorItem(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.initialiseViews(context);
    this.checked = false;

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectorItem);
    this.selectedIcon = a.getDrawable(R.styleable.SelectorItem_selectedIcon);
    this.deselectedIcon = a.getDrawable(R.styleable.SelectorItem_deselectedIcon);
    this.text = a.getString(R.styleable.SelectorItem_text);

    this.setOnClickListener(this);
  }

  private void initialiseViews(Context context) {
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.cv_selector_item, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    this.itemIcon = this.findViewById(R.id.item_icon);
    this.itemLabel = this.findViewById(R.id.item_label);
    this.itemIcon.setImageDrawable(this.deselectedIcon);
    this.itemLabel.setText(this.text);
  }

  @Override
  public void onClick(View v) {
    if (this.itemToggledListener != null) {
      this.itemToggledListener.onItemToggedStateChanged(this.checked, this);
    }
  }

  public void deselect() {
    this.checked = false;
    this.itemIcon.setImageDrawable(this.deselectedIcon);
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
    updateView();
  }

  public boolean isChecked() {
    return this.checked;
  }

  public void toggle() {
    this.checked = !this.checked;
    updateView();
  }

  private void updateView() {
    if (this.checked) {
      this.itemLabel.setTypeface(Typeface.DEFAULT_BOLD);
      this.itemIcon.setImageDrawable(this.selectedIcon);
    } else {
      this.itemLabel.setTypeface(Typeface.DEFAULT);
      this.itemIcon.setImageDrawable(this.deselectedIcon);
    }
  }

  public void setOnItemToggledListener(OnItemToggledListener onItemToggledListener) {
    this.itemToggledListener = onItemToggledListener;
  }

  public String getValue() {
    return this.text;
  }
}
