package com.example.proyectoandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class PreLogin2 extends Fragment {

    Executor executor = Executors.newSingleThreadExecutor();

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pre_login2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        // esta variable deberia estar en un ViewModel
        MutableLiveData<Boolean> finishedLoading = new MutableLiveData<>();


        finishedLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                navController.navigate(R.id.action_preLogin2_to_preLogin3);
            }
        });

        // esto deberia estar en el Model y llamarlo a traves del ViewModel
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // simular la carga de recursos
                    Thread.sleep(2300);
                    finishedLoading.postValue(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}