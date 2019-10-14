package com.vit.app.cryptonix;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vit.app.cryptonix.Modals.AES;

public class Bob extends AppCompatActivity {
    Spinner mSpinner;
    public static int opt;
    //public static TextView mScanViewTextView;
    public static String option;

    EditText editText;
    Button button,copy;
    TextView textView;

    public static String[] options={"AES","DES","3DES","Blowfish"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = editText.getText().toString();
                if(p.equals(""))
                    Toast.makeText(Bob.this, "Cipher Text is empty!!", Toast.LENGTH_SHORT).show();
                else{

                }
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = textView.getText().toString();
                if(c.equals(""))
                    Toast.makeText(Bob.this, "Decryption not done!!", Toast.LENGTH_SHORT).show();
                else{
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("AES Encrypted", c);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(Bob.this, "Plain text Copied", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
