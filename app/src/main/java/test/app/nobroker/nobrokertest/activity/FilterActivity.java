package test.app.nobroker.nobrokertest.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import test.app.nobroker.nobrokertest.R;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn2Bhk,btn3Bhk,btn4Bhk,btnApt,btnIh,btnIf,btnFf,btnSf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_filter);
        setSupportActionBar(toolbar);
        btn2Bhk = (Button) findViewById(R.id.button_2bhk);
        btn2Bhk.setOnClickListener(this);
        btn3Bhk = (Button) findViewById(R.id.button_3bhk);
        btn3Bhk.setOnClickListener(this);
        btn4Bhk = (Button) findViewById(R.id.button_4bhk);
        btn4Bhk.setOnClickListener(this);
        btnApt = (Button) findViewById(R.id.button_apt);
        btnApt.setOnClickListener(this);
        btnIh = (Button) findViewById(R.id.button_ih);
        btnIh.setOnClickListener(this);
        btnIf = (Button) findViewById(R.id.button_if);
        btnIf.setOnClickListener(this);
        btnFf = (Button) findViewById(R.id.button_full_furnished);
        btnFf.setOnClickListener(this);
        btnSf = (Button) findViewById(R.id.button_semi_furnished);
        btnSf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent resultIntent = new Intent();
        switch (id){
            case R.id.button_2bhk:
                resultIntent.putExtra("type", "BHK2");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.button_3bhk:
                resultIntent.putExtra("type", "BHK3");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;

            case R.id.button_4bhk:
                resultIntent.putExtra("type", "BHK4");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;

            case R.id.button_apt:
                resultIntent.putExtra("buildingType", "AP");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;

            case R.id.button_if:
                resultIntent.putExtra("buildingType", "IF");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;

            case R.id.button_ih:
                resultIntent.putExtra("buildingType", "IH");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;

            case R.id.button_full_furnished:
                resultIntent.putExtra("furnishing", "FULLY_FURNISHED");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;

            case R.id.button_semi_furnished:
                resultIntent.putExtra("furnishing", "SEMI_FURNISHED");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;

            default:
                break;

        }
    }
}
