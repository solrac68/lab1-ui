package co.edu.udea.compumovil.gr06_20182.labscm20182;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;

public class DrinkActivity extends AppCompatActivity {

    ImageView imageView;
    Uri imageUri;

    private static int RESULT_LOAD_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        imageView = (ImageView) findViewById(R.id.imgView);
    }

    public void loadImagefromGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMG){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }
}
