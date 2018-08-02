package com.example.bpbhatta.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import static com.example.bpbhatta.quizapp.R.*;

public class MainActivity extends AppCompatActivity {

    List<Question> questionList;
    int score = 0;
    int quid = 0;
    Question currentQuestion;

    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();
        Collections.shuffle(questionList);
        currentQuestion = questionList.get(quid);

        txtQuestion = (TextView) findViewById(id.tv_question);
        rda = (RadioButton) findViewById(id.radio0);
        rdb = (RadioButton) findViewById(id.radio1);
        rdc = (RadioButton) findViewById(id.radio2);
        butNext = (Button) findViewById(id.button1);

        setQuestionView();

    }

    private void setQuestionView() {
        txtQuestion.setText(currentQuestion.getQuestion());
        rda.setText(currentQuestion.getOptA());
        rdb.setText(currentQuestion.getOptB());
        rdc.setText(currentQuestion.getOptC());
        quid++;
    }

    @SuppressLint("ResourceAsColor")
    public void btClick(View view) {
        RadioGroup grp = (RadioGroup) findViewById(id.radioGroup1);
        RadioButton answerId = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
        rda.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        rdb.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        rdc.setBackgroundColor(getResources().getColor(R.color.colorWhite));
//        grp.clearCheck();

        if (!currentQuestion.getAnswer().equalsIgnoreCase(answerId.getText().toString())) {
            answerId.setBackgroundColor(getResources().getColor(color.colorRed));
            Toast.makeText(MainActivity.this, "You Are Wrong" + "The Correct Answer is:" + currentQuestion.getAnswer(), Toast.LENGTH_SHORT).show();

        } else {
            TextView message = (TextView) findViewById(R.id.tv_Message);
            answerId.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            message.setText("You Are Correct");
            score++;
            Log.d("Score", "Your score: " + score);
        }

        if (rda.isChecked() && !answerId.getText().toString().equalsIgnoreCase(currentQuestion.getAnswer())) {
            grp.setBackgroundColor(getResources().getColor(color.colorGreen));
        } else if (rdb.isChecked() && !answerId.getText().toString().equalsIgnoreCase(currentQuestion.getAnswer())) {
            grp.setBackgroundColor(getResources().getColor(color.colorGreen));
        } else if (rdc.isChecked() && !answerId.getText().toString().equalsIgnoreCase(currentQuestion.getAnswer())) {
            grp.setBackgroundColor(getResources().getColor(color.colorGreen));
        }

       /* if (currentQuestion.getAnswer().equalsIgnoreCase(answerId.getText().toString())) {
            TextView message = (TextView) findViewById(R.id.tv_Message);
            answerId.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            message.setText("You Are Correct");
            score++;
            Log.d("Score", "Your score: " + score);
        } else {
            answerId.setBackgroundColor(getResources().getColor(color.colorRed));
            Toast.makeText(MainActivity.this, "You Are Wrong" + "The Correct Answer is:" + currentQuestion.getAnswer(), Toast.LENGTH_SHORT).show();

        }*/

//        if (quid < 10) {
//            currentQuestion = questionList.get(quid);
//            setQuestionView();
//        } else {
//            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//
//            Bundle b = new Bundle();
//            b.putInt("score", score);
//            intent.putExtras(b);
//            startActivity(intent);
//            finish();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
