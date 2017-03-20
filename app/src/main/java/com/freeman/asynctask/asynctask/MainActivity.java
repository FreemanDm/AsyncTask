package com.freeman.asynctask.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTxt, countTxt;
    private ProgressBar myProgressBar;
    private Button startTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTxt = (TextView) findViewById(R.id.result_txt);
        countTxt = (TextView) findViewById(R.id.count_txt);
        myProgressBar = (ProgressBar) findViewById(R.id.my_progress);
        startTaskBtn = (Button) findViewById(R.id.start_task_btn);
        myProgressBar.setVisibility(View.INVISIBLE);
        startTaskBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_task_btn:
                new MyTask().execute(5);
                new MyTask().execute(5);
                new MyTask().execute(5);
                new MyTask().execute(5);
                break;
        }
    }

    class MyTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            myProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values.length > 0){
                countTxt.setText(String.valueOf(values[0]));
            }
        }

        @Override
        protected String doInBackground(Integer... params) {
            int size = 1;
            if (params.length > 0 && params[0] > size) {
                size = params[0];
            }
            try {
                for (int i = 0; i < size; i++) {
                    Thread.sleep(1000);
                    publishProgress(i);
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "This is text from doInBackground";
        }

        @Override
        protected void onPostExecute(String result) {
            myProgressBar.setVisibility(View.INVISIBLE);
            resultTxt.setText(result);
        }
    }
}
