package com.jersey.practicamodel.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> name = new MutableLiveData<>();

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getName() {
        return name.getValue();
    }
}
