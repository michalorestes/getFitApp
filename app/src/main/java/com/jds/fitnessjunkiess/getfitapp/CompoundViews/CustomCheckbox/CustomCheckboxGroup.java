package com.jds.fitnessjunkiess.getfitapp.CompoundViews.CustomCheckbox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.flexbox.FlexboxLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomCheckboxGroup extends FlexboxLayout {

  private List<CustomCheckbox> children;

  public CustomCheckboxGroup(Context context) {
    super(context);
    this.initialise();
  }

  public CustomCheckboxGroup(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.initialise();
  }

  public CustomCheckboxGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.initialise();
  }

  private void initialise() {
    this.children = new ArrayList<>();
  }

  @Override
  public void onViewAdded(View child) {
    if (child instanceof CustomCheckbox) {
      super.onViewAdded(child);
      this.children.add((CustomCheckbox) child);
    }
  }

  public List<CustomCheckbox> getCheckedChildren() {
    return children
        .stream()
        .filter(CustomCheckbox::isChecked)
        .collect(Collectors.toList());
  }
}
