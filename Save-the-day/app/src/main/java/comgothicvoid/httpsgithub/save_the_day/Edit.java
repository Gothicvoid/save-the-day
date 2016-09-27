package comgothicvoid.httpsgithub.save_the_day;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/9/17.
 */
public class Edit extends AppCompatActivity {

    private Journal journal;
    private String year;
    private String month;
    private String day;
    private String way;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        journal = (Journal)getIntent().getSerializableExtra("key");
        year = getIntent().getStringExtra("year");
        month = getIntent().getStringExtra("month");
        day = getIntent().getStringExtra("day");
        Calendar c = Calendar.getInstance();
        way = getweek(c,year,month,day);

        if(journal == null){
            journal = new Journal();
            journal.setYear(year);
            journal.setMonth(month);
            journal.setDay(day);
            if ("1".equals(way)) {
                way = "SUNDAY";
            } else if ("2".equals(way)) {
                way = "MONDAY";
            } else if ("3".equals(way)) {
                way = "TUESDAY";
            } else if ("4".equals(way)) {
                way = "WEDNESDAY";
            } else if ("5".equals(way)) {
                way = "THURSDAY";
            } else if ("6".equals(way)) {
                way = "FRIDAY";
            } else if ("7".equals(way)) {
                way = "SATURDAY";
            }
            journal.setWay(way);
        }

        year = journal.getYear();
        month = journal.getMonth();
        day = journal.getDay();
        way = journal.getWay();
        name = year + month + day + ".txt";

        setContentView(R.layout.edit);

        TextView tv = (TextView)findViewById(R.id.d);
        tv.setText(way);
        if(way.equals("SUNDAY")) tv.setTextColor(Color.RED);
        else tv.setTextColor(Color.BLACK);

        TextView textView = (TextView)findViewById(R.id.textview);
        textView.setText("/" + getmonth(month) + " " + day + "/" + year);

        final EditText editText = (EditText)findViewById(R.id.edittext);
        editText.setSingleLine(false);
        editText.setHorizontallyScrolling(false);
        editText.setText(journal.getText());
        if(journal.getText() != null) {
            editText.setSelection(journal.getText().length());
        }

        final ImageButton time = (ImageButton) findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
                String minute = String.valueOf(c.get(Calendar.MINUTE));
                String apm;
                if(c.get(Calendar.AM_PM) == 1) apm = "pm";
                else apm = "am";
                String t = hour + ":" + minute + apm + " ";

                int index = editText.getSelectionStart();
                Editable edit = editText.getEditableText();
                if (index < 0 || index >= edit.length() ){
                    edit.append(t);
                }else{
                    edit.insert(index,t);
                }
            }
        });

        final ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = day;
                Intent intent = new Intent();
                intent.putExtra("d",d);
                setResult(100,intent);
                finish();
            }
        });

        final ImageButton done = (ImageButton) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                journal.setText(editText.getText().toString());
                saveObject(name,journal);
                editText.setCursorVisible(false);
                time.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
                done.setVisibility(View.INVISIBLE);
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setCursorVisible(true);
                time.setVisibility(View.VISIBLE);
                back.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    private void saveObject(String name,Object o){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = this.openFileOutput(name, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getweek(Calendar c,String y, String m, String d){
        c.set(Integer.parseInt(y),Integer.parseInt(m) - 1,Integer.parseInt(d));
        return String.valueOf(c.get(Calendar.DAY_OF_WEEK));
    }

    private String getmonth(String mon){
        if("01".equals(mon)){
            return  "JANUARY";
        } else if("02".equals(mon)){
            return  "FEBRUARY";
        } else if("03".equals(mon)){
            return  "MARCH";
        } else if("04".equals(mon)){
            return  "APRIL";
        } else if("05".equals(mon)){
            return  "MAY";
        } else if("06".equals(mon)){
            return  "JUNE";
        } else if("07".equals(mon)){
            return  "JULY";
        } else if("08".equals(mon)){
            return  "AUGUST";
        } else if("09".equals(mon)){
            return  "SEPTEMBER";
        } else if("10".equals(mon)){
            return  "OCTOBER";
        } else if("11".equals(mon)){
            return  "NOVEMBER";
        } else return  "DECEMBER";
    }
}
