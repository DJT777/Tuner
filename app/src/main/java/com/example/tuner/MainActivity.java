package com.example.tuner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;


public class MainActivity extends AppCompatActivity {

    private final int PERMISSION_TO_RECORD_AUDIO = 1;




    private void requestAudioPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //Show message when permission is not granted by user
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "Please grant permission to record audio", Toast.LENGTH_LONG).show();

                //Option to record audio is presented again
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_TO_RECORD_AUDIO);
            } else {
                //Show user dialogue to request permission to record audio
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_TO_RECORD_AUDIO);
            }
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            init();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAudioPermissions();
    }



    public void init() {
        final Note n = new Note();
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);
        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult result, AudioEvent audioEvent) {
                final float hz = result.getPitch();
                n.setHertz(hz);
                n.noteCalc(hz);
                final String Pitch = n.getPitch();
                final String Note = n.getNote();





                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.textView1);
                        text.setText("" + Math.round(hz * 100.0) / 100.0);
                        TextView note = (TextView) findViewById(R.id.textView);
                        note.setText("" + Note);
                        TextView pitch = (TextView) findViewById(R.id.textView2);
                        pitch.setText("" + Pitch);

                    }

                });

            };
        };
        AudioProcessor p = new PitchProcessor(PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(p);
        new Thread(dispatcher, "Audio Dispatcher").start();
    };

    public class Note {

        float hz = -1;
        String note = "-";
        String pitch = "-";


        Note(){
        }



        public void noteCalc(double hz){

                if  (hz < 43.65){
                    note = "-";
                    pitch = "-";
                    }
                else if (43.65 <= hz && hz < 47.25){
                    note = "F#1";
                    pitch = "46.25";
                    }
                else if (47.25 <= hz && hz < 50.45){
                    note = "G1";
                    pitch = "49.00";
                    }
                else if (50.45 <= hz && hz < 53.455){
                    note = "G#1";
                    pitch = "51.91";
                    }
                else if (53.455 <= hz && hz < 56.635){
                    note = "A1";
                    pitch = "55.00";
                    }
                else if (56.635 <= hz && hz < 60.005){
                    note = "Bb1";
                    pitch = "58.27";
                    }
                else if (60.005 <= hz && hz < 63.575){
                    note = "B1";
                    pitch = "61.74";
                    }
                else if (63.575 <= hz && hz < 67.355){
                    note = "C2";
                    pitch = "65.41";
                    }
                else if (67.355 <= hz && hz < 71.36){
                    note = "C#2";
                    pitch = "69.30";
                    }
                else if (71.36 <= hz && hz < 75.60){
                    note = "D2";
                    pitch = "73.42";
                    }
                else if (75.60 <= hz && hz < 80.095){
                    note = "Eb2";
                    pitch = "82.41";
                    }
                else if (80.095 <= hz && hz < 84.86){
                    note = "E2";
                    pitch = "82.41";
                    }
                else if (84.86 <= hz && hz < 89.905){
                    note = "F2";
                    pitch = "87.31";
                    }
                else if (89.905 <= hz && hz < 95.25){
                    note = "F#2";
                    pitch = "92.50";
                    }
                else if (95.25 <= hz && hz < 100.90){
                    note = "G2";
                    pitch = "98.00";
                    }
                else if (100.90 <= hz && hz < 106.65){
                    note = "Ab2";
                    pitch = "103.8";
                    }
                else if (106.65 <= hz && hz < 113.25){
                    note = "A2";
                    pitch = "110.0";
                    }
                else if (113.25 <= hz && hz < 120.00){
                    note = "Bb2";
                    pitch = "116.5";
                    }
                else if (120.00 <= hz && hz < 127.15){
                    note = "B2";
                    pitch = "123.5";
                    }
                else if (127.15 <= hz && hz < 134.7){
                    note = "C3";
                    pitch = "130.8";
                    }
                else if (134.7 <= hz && hz < 142.7){
                    note = "C#3";
                    pitch = "138.60";
                    }
                else if (142.7 <= hz && hz < 151.2){
                    note = "D3";
                    pitch = "146.80";
                    }
                else if (151.2 <= hz && hz < 160.2){
                    note = "Eb3";
                    pitch = "155.60";
                    }
                else if (160.2 <= hz && hz < 169.7){
                    note = "E3";
                    pitch = "164.80";
                    }
                else if (169.7 <= hz && hz < 179.8){
                    note = "F3";
                    pitch = "174.60";
                    }
                else if (179.8 <= hz && hz < 190.5){
                    note = "F#3";
                    pitch = "185.50";
                    }
                else if (190.5 <= hz && hz < 201.85){
                    note = "G3";
                    pitch = "196.00";
                    }
                else if (201.85 <= hz && hz < 213.85){
                    note = "G#3";
                    pitch = "207.7";
                    }
                else if (213.85 <= hz && hz < 226.55){
                    note = "A3";
                    pitch = "220.00";
                    }
                else if (226.55 <= hz && hz < 240.00){
                    note = "Bb3";
                    pitch = "233.10";
                    }
                else if (240.00 <= hz && hz < 254.25){
                    note = "B3";
                    pitch = "246.90";
                    }
                else if (254.25 <= hz && hz < 269.4){
                    note = "C4";
                    pitch = "261.60";
                    }
                else if (269.4 <= hz && hz < 285.45){
                    note = "C#4";
                    pitch = "277.20";
                    }
                else if (285.45 <= hz && hz < 302.40){
                    note = "D4";
                    pitch = "293.70";
                    }
                else if (302.40 <= hz && hz <= 320.32){
                    note = "Eb4";
                    pitch = "311.10";
                    }
                else if (320.32 <= hz && hz < 339.4){
                    note = "E4";
                    pitch = "329.60";
                    }
                else if (339.4 <= hz && hz < 359.6){
                    note = "F4";
                    pitch = "349.20";
                    }
                else if (359.60 <= hz && hz < 381.00){
                    note = "F#4";
                    pitch = "370.00";
                    }
                else if (381.00 <= hz && hz < 403.65){
                    note = "G4";
                    pitch = "392.00";
                    }
                else if (403.65 <= hz && hz < 427.65){
                    note = "G#4";
                    pitch = "415.30";
                    }
                else if (427.65 <= hz && hz < 453.10){
                    note = "A4";
                    pitch = "440.00";
                    }
                else if (453.10 <= hz && hz < 480.25){
                    note = "Bb4";
                    pitch = "466.20";
                    }
                else if (480.25 <= hz && hz < 508.60){
                    note = "B4";
                    pitch = "493.90";
                    }
                else if (508.60 <= hz && hz < 538.85){
                    note = "C5";
                    pitch = "523.30";
                    }
                else if (538.85 <= hz && hz < 570.85){
                    note = "C#5";
                    pitch = "554.40";
                    }
                else if (570.85 <= hz && hz < 604.80){
                    note = "D5";
                    pitch = "587.30";
                    }
                else if (604.80 <= hz && hz < 640.80){
                    note = "D#5";
                    pitch = "622.30";
                    }
                else if (640.80 <= hz && hz < 678.90){
                    note = "E5";
                    pitch = "659.30";
                    }
                else if (678.90 <= hz && hz < 719.25){
                    note = "F5";
                    pitch = "698.50";
                    }
                else if (719.25 <= hz && hz < 762.00){
                    note = "F#5";
                    pitch = "740.0";
                    }
                else if (762.00 <= hz && hz < 807.30){
                    note = "G5";
                    pitch = "784.00";
                    }
                else if (807.30 <= hz && hz < 855.30){
                    note = "G#5";
                    pitch = "830.60";
                    }
                else if (855.30 <= 906.15){
                    note = "A5";
                    pitch = "880.00";
                    }
                else if (906.15 <= hz && hz < 960.05){
                    note = "Bb5";
                    pitch = "932.30";
                    }
                else if (960.05 <= hz && hz < 1017.40){
                    note = "Bb5";
                    pitch = "987.8";
                    }
                else if (1017.40 <= hz && hz < 1078.00){
                    note = "C6";
                    pitch = "1047.00";
                    }
                else if (1078.00 <= hz && hz < 1142.00){
                    note = "C#6";
                    pitch = "1109.00";
                    }
                else if (1142.00 <= hz && hz < 1210.00){
                    note = "D6";
                    pitch = "1175.00";
                    }
                else if (1210.00 <= hz && hz < 1282.00){
                    note = "Eb6";
                    pitch = "2489.00";
                    }
                else if (1282.00 <= hz && hz < 1358.00){
                    note = "E6";
                    pitch = "1319.00";
                    }
                else if (1358.00 <= hz && hz < 1438.50){
                    note = "F6";
                    pitch = "1397.00";
                    }
                else if (1438.50 <= hz && hz < 1524.00){
                    note = "F#6";
                    pitch = "1480.00";
                    }
                else if (1524.00 <= hz && hz < 1614.50){
                    note = "G6";
                    pitch = "1568.00";
                    }
                else if (1614.50 <= hz && hz < 1710.50){
                    note = "G#6";
                    pitch = "1661.00";
                    }
                else if (1710.50 <= hz && hz < 1812.50){
                    note = "A6";
                    pitch = "1760.00";
                    }
                else if (1812.50 <= hz && hz < 1920.50){
                    note = "Bb6";
                    pitch = "1865.00";
                    }
                else if (1920.50 <= hz && hz < 2034.50){
                    note = "B6";
                    pitch = "1976.00";
                    }
                else if (2034.50 <= hz && hz < 2155.00){
                    note = "C7";
                    pitch = "2093.00";
                    }
                else if (2155.00 <= hz && hz < 2283.00){
                    note = "C#7";
                    pitch = "2217.00";
                    }
                else if (2283.00 <= hz && hz < 2419.00){
                    note = "D7";
                    pitch = "2349.00";
                    }
                else if (2419.00 <= hz && hz < 2563.00){
                    note = "Eb7";
                    pitch = "2489.00";
                    }
                else if (2563.00 <= hz && hz < 2715.50){
                    note = "E7";
                    pitch = "2637.00";
                    }
                else if (2715.50 <= hz && hz < 2877.00){
                    note = "F7";
                    pitch = "2794.00";
                    }
                else if (2877.00 <= hz && hz <= 3048.00){
                    note = "F#7";
                    pitch = "2960.00";
                    }
                else if (3048.00 < hz){
                    note = "-";
                    pitch = "";
                    }



            };

        public float setHertz(float hertz) {return hz = hertz;}
        public String getPitch() { return pitch; }
        public String getNote() { return note;}


    };

        }
