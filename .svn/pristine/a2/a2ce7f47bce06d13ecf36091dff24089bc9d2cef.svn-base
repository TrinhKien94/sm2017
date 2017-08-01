package com.tc.cmcglobal.fragments;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tc.cmcglobal.R;
import com.tc.cmcglobal.network.ApiManager;

/**
 * Created by Admin on 7/24/2017.
 */

public class SettingFragment extends Fragment{
    static int baseMod = 0;
    static String settingUrl = ApiManager.getUrlSetting(baseMod);
    public static SettingFragment getInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton rdDev = (RadioButton) view.findViewById(R.id.rbDev);
        RadioButton rdProd = (RadioButton) view.findViewById(R.id.rbProd);
        RadioGroup rdgSetting = (RadioGroup)view.findViewById(R.id.rdgUri);
        final EditText editText = (EditText)view.findViewById(R.id.txtUriServerSetting);
        if(!ApiManager.baseUrl.toString().equals(ApiManager.urlDev)&&!ApiManager.baseUrl.toString().equals(ApiManager.urlProd)){
            baseMod=2;
            editText.setText(ApiManager.baseUrl);
        }
        if(baseMod==0){
            rdDev.setChecked(true);
            rdProd.setChecked(false);
            editText.setText(settingUrl);
        }else if(baseMod==1){
            rdDev.setChecked(false);
            rdProd.setChecked(true);
            editText.setText(settingUrl);
        }else{
            rdDev.setChecked(false);
            rdProd.setChecked(false);
        }
        rdgSetting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup uriGroup, @IdRes int selectedMod) {
                if(selectedMod == R.id.rbDev){
                    // switch to Dev mod (develop uri)
                    settingUrl=ApiManager.getUrlSetting(0);
                    baseMod=0;
                    editText.setText(settingUrl);
                }else if (selectedMod == R.id.rbProd){
                    // switch to Prod mod (products uri)
                    settingUrl=ApiManager.getUrlSetting(1);
                    baseMod=1;
                    editText.setText(settingUrl);
                }
            }
        });
        Button saveButton = (Button) view.findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                ApiManager.baseUrl = url;
                Log.i("ApiManager baseUrl",ApiManager.baseUrl);
                Toast toast = Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
