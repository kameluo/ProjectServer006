package com.example.android.projectserver006;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    VideoView videoView;
    ToggleButton toggleButton;
    static String stringIP="";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        videoView=findViewById(R.id.videoView);
        toggleButton=findViewById(R.id.toggleButton);
        toggleButton.setEnabled(false);
        videoView.setVisibility(INVISIBLE);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.logo);
        imageView.setVisibility(View.VISIBLE);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().matches("")){
                    toggleButton.setEnabled(false);
                }else {
                    toggleButton=findViewById(R.id.toggleButton);
                    toggleButton.setEnabled(true);

                }


            }
        });
    }
    public void streamButton(View view){
        boolean checked=((ToggleButton)view).isChecked();
        if(checked){
            String ipAddress=editText.getText().toString();
            stringIP="rtsp://"+ipAddress+":8080/h264_pcm.sdp";//phone stream
            //stringIP="http://"+ipAddress+":8080/onvif/device_service";
            //stringIP="http://"+ipAddress+":8080/video";
            Uri uri=Uri.parse(stringIP);
            videoView.setVisibility(view.VISIBLE);
            imageView.setVisibility(INVISIBLE);
            videoView.setVideoURI(uri);
            videoView.start();
        }else{
            videoView.suspend();
            videoView.setVisibility(INVISIBLE);
            imageView.setVisibility(View.VISIBLE);

        }

    }


}
