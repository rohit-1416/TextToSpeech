package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.*;
import android.util.Log;
import java.util.Locale;
import android.view.View;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    private TextToSpeech textToSpeech;
    private ImageView btn;
    private EditText text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =(ImageView) findViewById(R.id.imgbtn);
        text=(EditText)findViewById(R.id.text);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                {
                    int ttslang= textToSpeech.setLanguage(Locale.US);
                    if(ttslang == TextToSpeech.LANG_MISSING_DATA || ttslang == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("TTS","The Language is not Supported!");
                    }
                    else{
                        Log.i("TTS","Language Supported");
                    }
                    Log.i("TTS","Initialization Successful");
                }else{
                    Toast.makeText(getApplicationContext(),"TTS Initialization Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = text.getText().toString();
                Log.i("TTS","button Clicked: "+data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH,null);
                if(speechStatus == TextToSpeech.ERROR){
                    Log.e("TTS","Error in converting Text to Speech");
                }
            }

            public void onDestroy()
            {
                MainActivity.super.onDestroy();
                if(textToSpeech != null){
                    textToSpeech.stop();
                    textToSpeech.shutdown();
                }
            }
        });
    }
}