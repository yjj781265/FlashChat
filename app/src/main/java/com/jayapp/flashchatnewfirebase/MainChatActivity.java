package com.jayapp.flashchatnewfirebase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private DatabaseReference mDatabaseReference;
    private ChatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // TODO: Set up the display name and get the Firebase reference
        setupDisplayName();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Send the message when the "enter" button is pressed
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sendMessage();
                return true;

            };
        });


        // TODO: Add an OnClickListener to the sendButton to send a message
         mSendButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
              sendMessage();
             }
         });
    }

    // TODO: Retrieve the display name from the Shared Preferences
private void setupDisplayName(){
    SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);
    mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY,null);
    if(mDisplayName== null){
        mDisplayName = "Anonymous";
    }
}

    private void sendMessage() {
        Log.d("Chatty","Send messsage");
        String message = mInputText.getText().toString();
        if(!message.equals("")) {
            InstantMessage input = new InstantMessage(message, mDisplayName);
            mDatabaseReference.child("message").push().setValue(input);
            mInputText.setText("");
        }
    }

        // TODO: Grab the text the user typed in and push the message to Firebase



    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
      public void onStart(){
          super.onStart();
          mAdapter = new ChatListAdapter(this,mDatabaseReference,mDisplayName);
          mChatListView.setAdapter(mAdapter);
      }

    @Override
    // TODO: Remove the Firebase event listener on the adapter.
    public void onStop() {
        super.onStop();
        mAdapter.cleanup();



    }

}
