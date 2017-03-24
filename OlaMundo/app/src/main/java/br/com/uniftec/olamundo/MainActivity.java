package br.com.uniftec.olamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private Button button;
    private Button button2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_text);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.text_view);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                onClickButton();
                break;
            case R.id.button2:
                onClickButton2();
                break;
        }
    }

    private void onClickButton(){
        textView.setText("Olá " + editText.getText());
    }

    private void onClickButton2(){
        textView.setText("Boa noite " + editText.getText());
    }
}
