package com.vit.app.cryptonix;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vit.app.cryptonix.Modals.AES;
import com.vit.app.cryptonix.Modals.Message;

public class Alice extends AppCompatActivity {
    Spinner mSpinner;
    public static int opt;
    //public static TextView mScanViewTextView;
    public static String option;

    EditText editText;
    Button button,copy;
    TextView textView;
    int counts;

    private ListView mMsgView;
    private Message messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMsgView = findViewById(R.id.aliceList);

        final ChatAdapter chatAdapter;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myChatCounts = database.getReference("count");
        final DatabaseReference myChat = database.getReference("chats");

        myChatCounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                counts = Integer.parseInt(dataSnapshot.getValue(String.class));
                Log.d("cccc", "Value is: " + counts);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("cccc", "Failed to read value.", error.toException());
            }
        });

        myChat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chatAdapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.chat_bubble, myChat) {
                    @Override
                    protected void populateView(View view, Message myObj, int position) {

                        ((TextView)view.findViewById(R.id.name)).setText(myObj.getName());

                    }
                };
                mMsgView.setAdapter(chatAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        editText=findViewById(R.id.alicemsg);
        button=findViewById(R.id.alicesend);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = editText.getText().toString();
                if(p.equals(""))
                    Toast.makeText(Alice.this, " Text is empty!!", Toast.LENGTH_SHORT).show();
                else{
                    counts = counts+1;
                    String c = AES.encrypt(p,   "12345");
                    myChat.child(String.valueOf(counts)).child("Alice").setValue(p);

                }
                editText.setText("");
            }
        });




    }
}
