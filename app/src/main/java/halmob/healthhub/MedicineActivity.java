package halmob.healthhub;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import halmob.healthhub.EventListeners.DrugListener;
import halmob.healthhub.Models.Drug;
import halmob.healthhub.Models.NotificationInfo;


public class MedicineActivity extends AppCompatActivity implements DrugListener {

    private EditText editTextName;
    private TextView editTextStartDate;
    private TextView editTextEndDate;

    private Spinner howManySpinner;
    private LinearLayout myLinearLayout;
    private List<String> timeList;
    private int many;

    // new
    private List<NotificationInfo> notificationList;

    TextView viewhead;
    TextView viewtime;
    List<TextView> allTimes = new ArrayList<TextView>();

    private DatePickerDialog.OnDateSetListener dateSetListener1;
    private DatePickerDialog.OnDateSetListener dateSetListener2;

    private Button buttonSubmit;

    Drug medicine;

    public MedicineActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        medicine = new Drug();

        editTextName = (EditText) findViewById(R.id.editText_1);
        editTextStartDate = (TextView) findViewById(R.id.date1);
        editTextEndDate = (TextView) findViewById(R.id.date2);

        howManySpinner = (Spinner) findViewById(R.id.howManySpinner);
        myLinearLayout = (LinearLayout) findViewById(R.id.takeTimeLinearLayout);

        ArrayAdapter adapterSpinner = ArrayAdapter.createFromResource(this, R.array.howManyMedicineArray, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        howManySpinner.setAdapter(adapterSpinner);

        howManySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String howManyResult = howManySpinner.getSelectedItem().toString();
                many = Integer.parseInt(howManyResult);

                // Remove all existing views from linear layout
                if(myLinearLayout.getChildCount() > 0)
                {
                    myLinearLayout.removeAllViews();
                }

                LayoutInflater inflater_layout = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                // remove all previously created elements from allTimes TextView Array
                allTimes.removeAll(allTimes);

                for(int count = 0; count < many; count++)
                {
                    final View customView = inflater_layout.inflate(R.layout.custom_time_field, null);
                    myLinearLayout.addView(customView);
                    viewhead = (TextView) customView.findViewById(R.id.headText);
                    viewtime = (TextView) customView.findViewById(R.id.timeText);

                    viewhead.setText((count + 1) + ". Time");
                    viewtime.setText("Tap Here");
                    viewtime.setId(count + 1);
                    viewtime.setOnClickListener(timeClick);
                    allTimes.add(viewtime);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        editTextStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int year = cal1.get(Calendar.YEAR);
                int month = cal1.get(Calendar.MONTH);
                int day = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(MedicineActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener1,
                        year, month, day
                );
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();

            }
        });

        editTextEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal2 = Calendar.getInstance();
                int year = cal2.get(Calendar.YEAR);
                int month = cal2.get(Calendar.MONTH);
                int day = cal2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog2 = new DatePickerDialog(MedicineActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener2,
                        year, month, day
                );
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();

            }
        });


        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;  // since index starts from 0

                String strMonth = String.valueOf(month);
                String strDay = String.valueOf(day);

                if(month < 10){
                    strMonth = "0" + String.valueOf(month);
                }
                if(day < 10){
                    strDay = "0" + String.valueOf(day);
                }

                String date1 = year + "-" + strMonth + "-" + strDay;
                editTextStartDate.setText(date1);
            }
        };

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;  // since index starts from 0

                String strMonth = String.valueOf(month);
                String strDay = String.valueOf(day);

                if(month < 10){
                    strMonth = "0" + String.valueOf(month);
                }
                if(day < 10){
                    strDay = "0" + String.valueOf(day);
                }

                String date2 = year + "-" + strMonth + "-" + strDay;
                editTextEndDate.setText(date2);
            }
        };

        buttonSubmit = (Button) findViewById(R.id.button_submit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMedicine();

            }
        });
    }


    View.OnClickListener timeClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calendar timeCal = Calendar.getInstance();
            int hour = timeCal.get(Calendar.HOUR_OF_DAY);
            int minute = timeCal.get(Calendar.MINUTE);

            final TextView tv = (TextView) findViewById(view.getId());

            TimePickerDialog timePickerDynamic = new TimePickerDialog(MedicineActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                            String time;
                            if(minute < 10 && hour < 10){
                                time = "0" + hour + ":0" + minute;
                            }
                            else if(minute < 10){
                                time = hour + ":0" + minute;
                            }
                            else if(hour < 10){
                                time = "0" + hour + ":" + minute;
                            }
                            else{
                                time = hour + ":" + minute;
                            }
                            tv.setText(time);
                            // allTimes.add(tv);
                        }
                    }, hour, minute, true);
            timePickerDynamic.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDynamic.show();
        }
    };


    //kullanıcının ilaçlarını okumak için aşağıdaki iki satırlık kodu kullan. Sonuçlar drugsRead fonksiyonuna düşecek.
        /*FirebaseTransaction.setDrugListenerListener(this);
        FirebaseTransaction.getDrugs();*/


    @Override
    public void drugsRead(List<Drug> drugList){
        //kullanıcıyla ilgili bütün ilaçlar drugList'in içinde
        //istediğin gibi kullan
    }

    public void submitMedicine() {
        String input1 = editTextName.getText().toString();
        String input2 = editTextStartDate.getText().toString();
        String input3 = editTextEndDate.getText().toString();
        String input4 = howManySpinner.getSelectedItem().toString();

        timeList = new ArrayList<>();

        for(int i = 0; i < many; i++){
            timeList.add(allTimes.get(i).getText().toString());
        }


        createNotificationArray(input2, input3, input1);

        createMedicine(input1, input2, input3, input4);

    }

    public void createMedicine(String s1, String s2, String s3, String s4) {
        if(validateForm(s1, s2, s3) == true)
        {
            medicine.setName(s1);
            medicine.setStartDate(s2);
            medicine.setEndDate(s3);
            medicine.setHowMany(s4);
            medicine.setTimeList(timeList);

            FirebaseTransaction.addDrug(medicine);

            Toast.makeText(getApplicationContext(),
                    "Medicine Record is saved successfully!\n Notification is set for this medicine!",
                    Toast.LENGTH_LONG).show();

            setNotification();

            Intent intent1 = new Intent(MedicineActivity.this, MedicineMainActivity.class);
            startActivity(intent1);
            finish();
        }
    }

    public void createNotificationArray(String s1, String s2, String s3)
    {
        notificationList = new ArrayList<>();

        // split strings into parts to find year, month and day
        String[] parts1 = s1.split("-");
        int startYear = Integer.parseInt(parts1[0]);
        int startMonth = Integer.parseInt(parts1[1]);
        int startDay = Integer.parseInt(parts1[2]);

        String[] parts2 = s2.split("-");
        int endYear = Integer.parseInt(parts2[0]);
        int endMonth = Integer.parseInt(parts2[1]);
        int endDay = Integer.parseInt(parts2[2]);

        Calendar startCal = Calendar.getInstance();
        startCal.set(startYear, startMonth - 1, startDay);

        Calendar endCal = Calendar.getInstance();
        endCal.set(endYear, endMonth - 1, endDay);


        while(startCal.before(endCal) || startCal.equals(endCal)){     // between date range

            for( int i = 0; i < many; i++ ){    // how many times in a day?
                NotificationInfo newNotification = new NotificationInfo();
                newNotification.setYear(startCal.get(Calendar.YEAR));
                newNotification.setMonth(startCal.get(Calendar.MONTH) + 1);
                newNotification.setDay(startCal.get(Calendar.DAY_OF_MONTH));
                newNotification.setDrugName(s3);

                String timeToSplit = timeList.get(i).toString();
                String[] parts3 = timeToSplit.split(":");
                int hour = Integer.parseInt(parts3[0]);
                int minute = Integer.parseInt(parts3[1]);

                newNotification.setHour(hour);
                newNotification.setMinute(minute);

                notificationList.add(newNotification);

            }

            startCal.add(Calendar.DATE, 1);

        }


    }

    public void setNotification()
    {
        for(int i = 0; i < notificationList.size(); i++)
        {
            long notificationID = setNotificationID(i);
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.MONTH, notificationList.get(i).getMonth() - 1);
            calendar.set(Calendar.YEAR, notificationList.get(i).getYear());
            calendar.set(Calendar.DAY_OF_MONTH, notificationList.get(i).getDay());

            calendar.set(Calendar.HOUR_OF_DAY, notificationList.get(i).getHour());
            calendar.set(Calendar.MINUTE, notificationList.get(i).getMinute());
            calendar.set(Calendar.SECOND, 0);
            // calendar.set(Calendar.AM_PM,Calendar.AM);

            Intent notifyIntent = new Intent(this,MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MedicineActivity.this, (int)notificationID, notifyIntent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    public long setNotificationID(int i)
    {
        Date now = new Date();
        String notificationID = Long.toString(Long.parseLong(new SimpleDateFormat("ddHHmmss").format(now)));
        notificationID = Integer.toString(i) + notificationID;
        return Integer.parseInt(notificationID);
    }

    public boolean validateForm(String s1, String s2, String s3)
    {
        boolean flag = true;    // if true form is valid, else form inputs are not valid

        // get today's date to check
        String strToday;
        Calendar cToday = Calendar.getInstance();
        int year1 = cToday.get(Calendar.YEAR);
        int month1 = cToday.get(Calendar.MONTH) + 1;
        int day1 = cToday.get(Calendar.DAY_OF_MONTH);

        if(month1 < 10 && day1 < 10){
            strToday = String.valueOf(year1) + "-0" + String.valueOf(month1) + "-0" + String.valueOf(day1);
        }
        else if(month1 < 10){
            strToday = String.valueOf(year1) + "-0" + String.valueOf(month1) + "-" + String.valueOf(day1);
        }
        else if(day1 < 10){
            strToday = String.valueOf(year1) + "-" + String.valueOf(month1) + "-0" + String.valueOf(day1);
        }
        else{
            strToday = String.valueOf(year1) + "-" + String.valueOf(month1) + "-" + String.valueOf(day1);
        }


        if(s1 == null || s1.trim().isEmpty()){   // if medicine name is not entered.
            flag = false;
            editTextName.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Medicine Name cannot be left blank!",
                    Toast.LENGTH_LONG).show();

        }

        else if(s2 == null || s2.trim().isEmpty()){
            flag = false;
            editTextStartDate.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Start date cannot be left blank!",
                    Toast.LENGTH_LONG).show();
        }

        else if(s3 == null || s3.trim().isEmpty()){
            flag = false;
            editTextStartDate.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "End date cannot be left blank!",
                    Toast.LENGTH_LONG).show();
        }

        else if(s2.compareTo(s3) > 0){     // if start date is greater than end date
            flag = false;
            editTextEndDate.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "End date must be later than start date!",
                    Toast.LENGTH_LONG).show();

        }

        else if(s2.compareTo(strToday) < 0){    // if start date is greater than today's date
            flag = false;
            Toast.makeText(getApplicationContext(),
                    "Start date must not be choosen from the past!",
                    Toast.LENGTH_LONG).show();
        }

        else if(s3.compareTo(strToday) < 0){    // if end date is greater than today's date
            flag = false;
            Toast.makeText(getApplicationContext(),
                    "End date must not be choosen from the past!",
                    Toast.LENGTH_LONG).show();
        }

        else {   // if some of the time values are not entered
            for(int k = 0; k < many; k++){
                String toCheck = timeList.get(k).toString();
                if(toCheck.equals("Tap Here")){
                    flag = false;
                    Toast.makeText(getApplicationContext(),
                            "Medicine times cannot be left blank!",
                            Toast.LENGTH_LONG).show();
                    break;
                }
            }

        }

        return flag;
    }
}