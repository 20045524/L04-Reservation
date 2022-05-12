package sg.edu.rp.c346.id20045524.l04_reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtPhone;
    EditText edtPax;
    CheckBox cbSmoking;
    DatePicker dp;
    TimePicker tp;
    Button btnReserve;
    Button btnReset;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.editName);
        edtPhone = findViewById(R.id.editPhone);
        edtPax = findViewById(R.id.editPax);
        cbSmoking = findViewById(R.id.checkBoxSmoke);
        dp = findViewById(R.id.displayDate);
        tp = findViewById(R.id.displayTime);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);
        dp.updateDate(2020,5, 1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int getCurrentHour = tp.getCurrentHour();
                int getCurrentMinute = tp.getCurrentMinute();
                String time = "";
                if (getCurrentMinute < 10){
                    time = getCurrentHour + ":0" + getCurrentMinute;
                } else {
                    time = getCurrentHour + ":" + getCurrentMinute;
                }


                int getYear = dp.getYear();
                int getDate = dp.getDayOfMonth();
                int getMonth = dp.getMonth() + 1;
                String date = getDate + "/" + getMonth + "/" + getYear;


                String msg = "";

                if (edtName.getText().length() == 0 || edtPhone.length() == 0
                        || edtPax.getText().length() == 0){
                    msg = "Error, blank detected.";
                } else {
                    if(cbSmoking.isChecked()){
                        msg = edtName.getText() + " has booked in a Smoking Area on " + date
                                + " at " + time + " for " + edtPax.getText()
                                + " people. We will send your comfirmation to " + edtPhone.getText();
                        Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
                    } else {
                        msg = edtName.getText() + " has booked in a Non-Smoking Area on " + date
                                + " at " + time + " for " + edtPax.getText()
                                + " people. We will send your comfirmation to " + edtPhone.getText();
                    }
                }

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("");
                dp.updateDate(2020,6, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);

            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker v, int hourOfDay, int minute) {
                hourOfDay = tp.getCurrentHour();
                if(hourOfDay < 8) {
                    tp.setCurrentHour(8);

                } else if (hourOfDay > 20) {
                    tp.setCurrentHour(20);

                }
            }
        });

        dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                LocalDate today = LocalDate.now();

                year = dp.getYear();
                monthOfYear = dp.getMonth() ;
                dayOfMonth = dp.getDayOfMonth();
                if (year < today.getYear() && monthOfYear < today.getMonthValue() && dayOfMonth < today.getDayOfMonth()){
                    dp.updateDate(today.getYear(), today.getMonthValue(), today.getDayOfMonth()+1);
                }
            }
        });












    }
}