package com.example.shrinkio.SecondaryActivities;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shrinkio.MainActivities.BottomActivity;
import com.example.shrinkio.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Objects;

public class PostActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 1;
    ImageButton video;
    StorageTask uploadTask;
    EditText Post;
    Uri imageUri;
    String myUrl = "";
    ImageView close;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    TextView post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout_post);

        Post = findViewById(R.id.Post);

        close = findViewById(R.id.close);
        post = findViewById(R.id.post);
        storageReference = FirebaseStorage.getInstance().getReference("post");


        close.setOnClickListener(view -> startActivity(new Intent(PostActivity.this, BottomActivity.class)));

        post.setOnClickListener(view -> uploadImage());


        video = findViewById(R.id.ImageButton);
        video.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, GALLERY_REQUEST);

        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        {
            if (item.getItemId() == R.menu.post_menu) {

                FirebaseDatabase.getInstance().getReference().child("Users").child("post").push();

                String post = Post.getText().toString();


                startActivity(new Intent(PostActivity.this, BottomActivity.class));
                Toast.makeText(PostActivity.this, "Shrinked", Toast.LENGTH_LONG).show();
                overridePendingTransition(0, 0);


                video.setImageURI(imageUri);

                DatabaseReference nPost = databaseReference.push();
                nPost.child("post").setValue(post);
                nPost.child("img").setValue(imageUri);

            }
        }
        return super.onOptionsItemSelected(item);
    }


    //Post


    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));

    }


    private void uploadImage() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Posting...");

        if (imageUri != null) {
            StorageReference filereference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            uploadTask = filereference.putFile(imageUri);

            uploadTask.continueWithTask((Continuation) task -> {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }

                return filereference;
            })
                    .addOnCompleteListener((OnCompleteListener<Uri>) task -> {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            myUrl = downloadUri.toString();

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                            String postId = reference.push().getKey();

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("postId", post);
                            hashMap.put("post_image", myUrl);
                            hashMap.put("publisher", FirebaseAuth.getInstance().getCurrentUser().getUid());

                            reference.child(postId).setValue(hashMap);
                            progressDialog.dismiss();
                            startActivity(new Intent(PostActivity.this, BottomActivity.class));
                            finish();
                        } else {
                            Toast.makeText(PostActivity.this, "Post failed!", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(e -> Toast.makeText(PostActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show());

        } else {
            Toast.makeText(PostActivity.this, " No Image Selected!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            assert result != null;
            imageUri = result.getUri();
            video.setImageURI(imageUri);
            video.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, BottomActivity.class));
            finish();
        }
    }
}
