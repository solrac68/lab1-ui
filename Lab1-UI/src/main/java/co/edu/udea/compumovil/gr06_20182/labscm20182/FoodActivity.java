package co.edu.udea.compumovil.gr06_20182.labscm20182;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class FoodActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    ImageView imageView;
    Uri imageUri;
    private TextView mTimeDisplay;
    private CheckBox checkBoxMorning;
    private CheckBox checkBoxAfternoon;
    private CheckBox checkBoxNight;
    private EditText txtPrecio;
    private EditText txtIngredient;
    private TextView txtView;
    private TextView textViewTime;
    private EditText txtName;


    private int mHour;
    private int mMinute;

    private static int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        imageView = (ImageView) findViewById(R.id.imgView);

        mTimeDisplay = (TextView) findViewById(R.id.textViewTime);
        checkBoxMorning = (CheckBox)findViewById(R.id.checkBoxMorning);
        checkBoxAfternoon = (CheckBox)findViewById(R.id.checkBoxAfternoon);
        checkBoxNight = (CheckBox)findViewById(R.id.checkBoxNight);
        txtPrecio =  (EditText)findViewById(R.id.txtPrecio);
        txtIngredient = (EditText)findViewById(R.id.txtIngredient);
        txtView = (TextView) findViewById(R.id.txtView);
        txtName = (EditText)findViewById(R.id.txtName);

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        updateDisplay();
    }

    private void updateDisplay() {
        mTimeDisplay.setText(new StringBuilder()
                .append(mHour).append(":").append(mMinute));
    }

    public void loadImagefromGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    public void onClickPicker(View view) {
        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getFragmentManager(), "timePicker");
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
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        updateDisplay();
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
        mTimeDisplay.setText("");
        txtName.setText("");
        checkBoxMorning.setChecked(false);
        checkBoxAfternoon.setChecked(false);
        checkBoxNight.setChecked(false);
        txtPrecio.setText("");
        txtIngredient.setText("");
        txtView.setText("");
        imageView.setImageURI(Uri.EMPTY);
    }

    private void exitApplication() {
        onDestroy();
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
}
