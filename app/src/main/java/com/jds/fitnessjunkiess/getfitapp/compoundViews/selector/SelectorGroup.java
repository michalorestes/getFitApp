package com.jds.fitnessjunkiess.getfitapp.compoundViews.selector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SelectorGroup extends LinearLayout implements OnItemToggledListener {

  List<SelectorItem> children;

  public SelectorGroup(Context context) {
    super(context);
    this.initialise();
  }

  public SelectorGroup(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.initialise();
  }

  @Override
  public void onViewAdded(View child) {
    if (child instanceof SelectorItem) {
      super.onViewAdded(child);
      SelectorItem selectorItem = (SelectorItem) child;
      selectorItem.setOnItemToggledListener(this);
      this.children.add(selectorItem);
    }
  }

  private void initialise() {
    this.children = new ArrayList<>();
  }

  @Override
  public void onItemToggedStateChanged(boolean checked, SelectorItem view) {
    for (int i = 0; i < this.children.size(); i++) {
      SelectorItem item = this.children.get(i);
      if (view.getId() != item.getId()) {
        item.setChecked(false);
      }
    }
    view.setChecked(true);
  }

  public String getSelectedValue() {
    for (SelectorItem child : this.children) {
      if (child.isChecked()) {
        return child.getValue();
      }
    }

    return "";
  }
}
