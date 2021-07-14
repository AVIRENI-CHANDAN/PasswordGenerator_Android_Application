package com.androidapp.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

class PasswordGenerator extends Object{
    public PasswordGenerator(){}
    public String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$%&*+=-~";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        String respass = "";
        System.out.println("Password operations:");
        respass += specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        for(int i = 1; i< length ; i++){
            if(i%4==0){
                respass += specialCharacters.charAt(random.nextInt(specialCharacters.length()));
            }
            else{
                respass += combinedChars.charAt(random.nextInt(combinedChars.length()));
            }
        }
        System.out.println("\nAfter operations password is:" + respass);
        System.out.println("The length input is:"+length+"\nThe result length is:"+respass.length());
        return respass;
    }
}

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button passgenbtn = (Button) findViewById(R.id.passwordGenerateButton);
        TextView passwordContainer = (TextView) findViewById(R.id.passwordContainer);
        TextView passCharLength = (TextView) findViewById(R.id.passCharLength);

        passgenbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("The data in the etnry box is:" + passCharLength.getText());
                PasswordGenerator passgen = new PasswordGenerator();
                if( Integer.parseInt(passCharLength.getText().toString()) < 8 ){
                    passCharLength.setText("8");
                }
                String newpass = passgen.generatePassword(Integer.parseInt(passCharLength.getText().toString()));
                System.out.println(newpass);
                passwordContainer.setText(newpass);
            }
        });
        passwordContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println(passwordContainer.getText());
                if(! passwordContainer.getText().toString().equals("Password")){
//                    System.out.println(passwordContainer.getText().toString() + "Password");
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("password", passwordContainer.getText());
                    clipboard.setPrimaryClip(clip);
                    System.out.println("The data in clipboard is:" + clipboard.getPrimaryClip());
                }
            }
        });

    }
}