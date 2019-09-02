package com.example.shrinkio.SecondaryActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shrinkio.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class chatMessage extends AppCompatActivity {

    private String messageUserId;
    private String messageUser;
    private String messageText;
    private long messageTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_messages );

        final EditText input = findViewById( R.id.Input );
        Button sendMessage = findViewById( R.id.SendMessage );
        sendMessage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child( "chats" )
                        .push()
                        .setValue( new chatMessage(
                                user.getUid(),
                                user.getDisplayName(),
                                input.getText().toString() )
                        );

                // Limpa o texto
                input.setText( "" );
            }
        } );





    }


    public chatMessage(String uid, String displayName, String s) {
    }

    // User's ID
    public void setMessageUserId(String messageUserId) {
        this.messageUserId = messageUserId;
    }

    //User name
    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }


    public String getMessageUser() {
        return messageUser;
    }

    //Get user's text message
    public void setmessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public long getMessageTime() {
        return messageTime;




    }
}

