package com.jds.fitnessjunkiess.getfitapp.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jds.fitnessjunkiess.getfitapp.R;

public class SelectorItem extends LinearLayout {

  ImageView itemIcon;
  TextView itemLabel;
  Drawable selectedIcon;
  Drawable deselectedIcon;
  String text;

  public SelectorItem(Context context) {
    super(context);
    this.initialiseViews(context);
  }

  public SelectorItem(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.initialiseViews(context);
  }

  public SelectorItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.initialiseViews(context);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectorItem);
    this.selectedIcon = a.getDrawable(R.styleable.SelectorItem_selectedIcon);
    this.deselectedIcon = a.getDrawable(R.styleable.SelectorItem_deselectedIcon);
    this.text = a.getString(R.styleable.SelectorItem_text);
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
  }
}
