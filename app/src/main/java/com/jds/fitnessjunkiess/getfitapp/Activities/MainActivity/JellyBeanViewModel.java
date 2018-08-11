package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class JellyBeanViewModel extends ViewModel {

    private MutableLiveData<Integer> amount = new MutableLiveData<>();
    String name;

    public MutableLiveData<Integer> getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.setValue(amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
