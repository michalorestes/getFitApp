package com.jds.fitnessjunkiess.getfitapp.CustomViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutCardView extends CardView {

    private TextView title;
    private TextView subTitle;
    private ImageView icon;

    public WorkoutCardView(@NonNull Context context) {
        super(context);
        initializeViews(getContext());
    }

    public WorkoutCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(getContext());
    }

    public WorkoutCardView
            (@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(getContext());
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.workout_view_layout, this);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        this.title = this.findViewById(R.id.textView3);
        this.subTitle = this.findViewById(R.id.textView4);
        this.icon = this.findViewById(R.id.icon);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setSubTitle(String subTitle) {
        this.subTitle.setText(subTitle);
    }

    public void setIcon(int id) {
        this.icon.setImageResource(id);
    }
}
