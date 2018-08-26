package co.edu.udea.compumovil.gr06_20182.labscm20182;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class DrinkActivity extends AppCompatActivity {

    ImageView imageView;
    Uri imageUri;
    private EditText txtName;
    private EditText txtPrecio;
    private EditText txtIngredient;
    private TextView txtView;

    private static int RESULT_LOAD_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        imageView = (ImageView) findViewById(R.id.imgView);
        txtView = (TextView) findViewById(R.id.txtView);
        txtName = (EditText)findViewById(R.id.txtName);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);
        txtIngredient = (EditText)findViewById(R.id.txtIngredient);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        UIHelper.killApp(true);
    }
    private void exitApplication() {
        onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.id_clean:
                cleanControls();
                return true;
            case R.id.id_exit:
                exitApplication();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void cleanControls() {
        txtName.setText("");
        txtPrecio.setText("");
        txtIngredient.setText("");
        txtView.setText("");
        imageView.setImageURI(Uri.EMPTY);
    }
}
