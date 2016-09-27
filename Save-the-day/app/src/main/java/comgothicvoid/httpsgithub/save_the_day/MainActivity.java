package comgothicvoid.httpsgithub.save_the_day;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Runnable{

    private int viewtype = 1;

    private ListView lv;

    private Calendar c = Calendar.getInstance();
    private String year = String.valueOf(c.get(Calendar.YEAR));
    private int x = c.get(Calendar.MONTH);
    private String month = change(String.valueOf(x + 1), x + 1);
    private int y = c.get(Calendar.DAY_OF_MONTH);
    private String day = change(String.valueOf(y), y);
    private String way = getweek(c.get(Calendar.DAY_OF_WEEK));

    private String syear = year;
    private String smonth = month;
    private String sday = day;
    private String yn;
    private boolean isadd = false;

    private int n;
    private int mv2pos;
    private String vname;
    private Journal vjournal;
    private ArrayList<Object> o;
    private ArrayList<HashMap<String, Object>> listItem;
    private ABAdapter abAdapter;
    private stdSimpleAdapter stdSA;
    private Handler handler;
    private RelativeLayout mainv;
    private RelativeLayout monthv;
    private RelativeLayout yearv;
    private Button buttonyear;
    private Button buttonmonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        final TextView textView = (TextView)this.findViewById(R.id.today);
        final String xx = "Today is " + way + " " + month + "/" + day + "/" + year;

        handler = new Handler() {
            public void handleMessage(Message msg) {
                textView.setText(xx + " " + (String)msg.obj);
            }
        };

        new Thread(this).start();

        if(viewtype == 1) lv1();
        else lv2();

        mainv = (RelativeLayout)findViewById(R.id.rl);
        monthv = (RelativeLayout) findViewById(R.id.mrl);
        yearv = (RelativeLayout)findViewById(R.id.yrl);

        monthv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainv.setVisibility(View.VISIBLE);
                monthv.setVisibility(View.GONE);
            }
        });

        yearv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainv.setVisibility(View.VISIBLE);
                yearv.setVisibility(View.GONE);
            }
        });

        Button buttonadd = (Button)this.findViewById(R.id.buttonadd);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isadd = true;
                String name = year + month + day + ".txt";
                Journal journal = (Journal)getObject(name);
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("key",journal);
                intent.putExtras(mBundle);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("day",day);
                startActivityForResult(intent, 100);
            }
        });

        buttonmonth = (Button)this.findViewById(R.id.buttonmonth);
        buttonmonth.setText(getmonth(smonth));

        buttonyear = (Button)this.findViewById(R.id.buttonyear);
        buttonyear.setText(syear);

        buttonyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainv.setVisibility(View.GONE);
                yearv.setVisibility(View.VISIBLE);
                setyearbutt();
            }
        });

        ImageButton buttonview = (ImageButton)this.findViewById(R.id.buttonview);
        buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewtype == 1){
                    viewtype = 2;
                    lv2();
                } else{
                    viewtype = 1;
                    lv1();
                }
            }
        });

        final Button jan = (Button)findViewById(R.id.jan);
        jan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "01".compareTo(month) <= 0) {
                    smonth = "01";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button feb = (Button)findViewById(R.id.feb);
        feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "02".compareTo(month) <= 0) {
                    smonth = "02";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button mar = (Button)findViewById(R.id.mar);
        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "03".compareTo(month) <= 0) {
                    smonth = "03";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button apr = (Button)findViewById(R.id.apr);
        apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "04".compareTo(month) <= 0) {
                    smonth = "04";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button may = (Button)findViewById(R.id.may);
        may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "05".compareTo(month) <= 0) {
                    smonth = "05";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button jun = (Button)findViewById(R.id.jun);
        jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "06".compareTo(month) <= 0) {
                    smonth = "06";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button jul = (Button)findViewById(R.id.jul);
        jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "07".compareTo(month) <= 0) {
                    smonth = "07";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button aug = (Button)findViewById(R.id.aug);
        aug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "08".compareTo(month) <= 0) {
                    smonth = "08";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button sep = (Button)findViewById(R.id.sep);
        sep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "09".compareTo(month) <= 0) {
                    smonth = "09";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button oct = (Button)findViewById(R.id.oct);
        oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "10".compareTo(month) <= 0) {
                    smonth = "10";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button nov = (Button)findViewById(R.id.nov);
        nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "11".compareTo(month) <= 0) {
                    smonth = "11";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        final Button dec = (Button)findViewById(R.id.dec);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!syear.equals(year) || "12".compareTo(month) <= 0) {
                    smonth = "12";
                    buttonmonth.setText(getmonth(smonth));
                    mainv.setVisibility(View.VISIBLE);
                    monthv.setVisibility(View.GONE);
                    if(viewtype == 1) lv1();
                    else lv2();
                }
            }
        });

        buttonmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("01".equals(smonth)) jan.setBackgroundResource(R.drawable.dark);
                else jan.setBackgroundResource(R.drawable.gray);
                if("02".equals(smonth)) feb.setBackgroundResource(R.drawable.dark);
                else feb.setBackgroundResource(R.drawable.gray);
                if("03".equals(smonth)) mar.setBackgroundResource(R.drawable.dark);
                else mar.setBackgroundResource(R.drawable.gray);
                if("04".equals(smonth)) apr.setBackgroundResource(R.drawable.dark);
                else apr.setBackgroundResource(R.drawable.gray);
                if("05".equals(smonth)) may.setBackgroundResource(R.drawable.dark);
                else may.setBackgroundResource(R.drawable.gray);
                if("06".equals(smonth)) jun.setBackgroundResource(R.drawable.dark);
                else jun.setBackgroundResource(R.drawable.gray);
                if("07".equals(smonth)) jul.setBackgroundResource(R.drawable.dark);
                else jul.setBackgroundResource(R.drawable.gray);
                if("08".equals(smonth)) aug.setBackgroundResource(R.drawable.dark);
                else aug.setBackgroundResource(R.drawable.gray);
                if("09".equals(smonth)) sep.setBackgroundResource(R.drawable.dark);
                else sep.setBackgroundResource(R.drawable.gray);
                if("10".equals(smonth)) oct.setBackgroundResource(R.drawable.dark);
                else oct.setBackgroundResource(R.drawable.gray);
                if("11".equals(smonth)) nov.setBackgroundResource(R.drawable.dark);
                else nov.setBackgroundResource(R.drawable.gray);
                if("12".equals(smonth)) dec.setBackgroundResource(R.drawable.dark);
                else dec.setBackgroundResource(R.drawable.gray);
                mainv.setVisibility(View.GONE);
                monthv.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && requestCode == 100){
            if(isadd){
                isadd = false;
                syear = year;
                smonth = month;
                buttonyear.setText(syear);
                buttonmonth.setText(getmonth(smonth));
                if(viewtype == 1) lv1();
                else lv2();
            } else {
                String d = data.getStringExtra("d");
                String n = syear + smonth + d + ".txt";
                if (viewtype == 1) {
                    if ((Journal) getObject(n) != null) {
                        int i = Integer.parseInt(d) - 1;
                        o.set(i, (Journal) getObject(n));
                        abAdapter = new ABAdapter(this, o);
                        lv.setAdapter(abAdapter);
                    }
                } else {
                    vjournal = (Journal) getObject(n);
                    listItem.get(mv2pos).put("ItemText", " " + "/" + vjournal.getText());
                    stdSA = new stdSimpleAdapter(this, listItem, R.layout.view2_listitem,
                            new String[]{"Itemday", "Itemway", "ItemText"},
                            new int[]{R.id.mv2d, R.id.mv2w, R.id.mv2j});
                    lv.setAdapter(stdSA);
                }
            }
        }
    }

    //主视图1列表生成
    private void lv1(){

        if(syear.equals(year) && smonth.equals(month)){
            n = Integer.parseInt(day);
        } else{
            if(smonth.equals("02")){
                int y = Integer.parseInt(syear);
                if((y%4) == 0){
                    n = 29;
                } else n = 28;
            } else if(smonth.equals("04") || smonth.equals("06") ||
                    smonth.equals("09") || smonth.equals("11")){
                n = 30;
            } else n = 31;
        }

        lv = (ListView) findViewById(R.id.lv);
        o = new ArrayList<>();

        String nam;

        for(int i = 0; i != n; i++){
            nam = String.valueOf(i + 1);
            if(i <= 8) nam = "0" + nam;
            vname = syear + smonth + nam + ".txt";
            vjournal = (Journal)getObject(vname);
            if(vjournal == null){
                Point p = new Point();
                p.setissd(issd(c,syear,smonth,i + 1));
                o.add(p);
            } else o.add(vjournal);
        }

        abAdapter = new ABAdapter(this,o);
        lv.setAdapter(abAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sday = String.valueOf(position + 1);
                if(position <= 8) sday = "0" + sday;
                String name = syear + smonth + sday + ".txt";
                Journal journal = (Journal)getObject(name);
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("key",journal);
                intent.putExtras(mBundle);
                intent.putExtra("year",syear);
                intent.putExtra("month",smonth);
                intent.putExtra("day",sday);
                startActivityForResult(intent, 100);
            }
        });
    }

    //主视图2列表生成
    private void lv2(){

        if(syear.equals(year) && smonth.equals(month)){
            n = Integer.parseInt(day);
        } else{
            if(smonth.equals("02")){
                int y = Integer.parseInt(syear);
                if((y%4) == 0){
                    n = 29;
                } else n = 28;
            } else if(smonth.equals("04") || smonth.equals("06") ||
                    smonth.equals("09") || smonth.equals("11")){
                n = 30;
            } else n = 31;
        }

        lv = (ListView) findViewById(R.id.lv);

        listItem = new ArrayList<HashMap<String, Object>>();

        String nam;

        for(int i = 0; i != n; i++){
            nam = String.valueOf(i + 1);
            if(i <= 8) nam = "0" + nam;
            vname = syear + smonth + nam + ".txt";
            vjournal = (Journal)getObject(vname);
            if(vjournal != null){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("Itemday", nam + " ");
                map.put("Itemway", vjournal.getWay());
                map.put("ItemText", " " + "/" + vjournal.getText());
                listItem.add(map);
            }
        }

        stdSA = new stdSimpleAdapter(this,listItem, R.layout.view2_listitem,
                new String[] {"Itemday", "Itemway", "ItemText"},
                new int[] {R.id.mv2d,R.id.mv2w,R.id.mv2j});

        lv.setAdapter(stdSA);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mv2pos = position;
                TextView tv = (TextView)view.findViewById(R.id.mv2d);
                sday = tv.getText().toString().substring(0, 2);
                String name = syear + smonth + sday + ".txt";
                Journal journal = (Journal)getObject(name);
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("key",journal);
                intent.putExtras(mBundle);
                intent.putExtra("year",syear);
                intent.putExtra("month",smonth);
                intent.putExtra("day",sday);
                startActivityForResult(intent, 100);
            }
        });
    }

    private Object getObject(String name){

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = this.openFileInput(name);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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

    private String getweek(int s){
        if (s == 1) {
            return "Sunday";
        } else if (s == 2) {
            return "Monday";
        } else if (s == 3) {
            return "Tuesday";
        } else if (s == 4) {
            return "Wednesday";
        } else if (s == 5) {
            return "Thursday";
        } else if (s == 6) {
            return "Friday";
        } else return "Saturday";
    }

    private String change(String s, int i){
        String str = s;
        if(i <= 9) str = "0" + s;
        return str;
    }

    private boolean issd(Calendar c,String y, String m, int d){
        c.set(Integer.parseInt(y),Integer.parseInt(m) - 1,d);
        if(c.get(Calendar.DAY_OF_WEEK) == 1) return true;
        else return false;
    }

    //动态生成选择年份的按钮
    private void setyearbutt(){
        int ny = Integer.parseInt(year) - 2010 + 1;

        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

        ll.removeAllViews();

        for(int i = 0; i != ny; i++){
            yn = String.valueOf(2010 + i);
            final Button butt = new Button(this);
            butt.setWidth(10);
            butt.setHeight(20);
            butt.setBackgroundResource(R.color.transparent);
            butt.setText(yn);
            if(syear.equals(yn)) butt.setTextColor(getResources().getColor(R.color.black));
            else butt.setTextColor(getResources().getColor(R.color.gray));
            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    syear = butt.getText().toString();
                    if(viewtype == 1) lv1();
                    else lv2();
                    mainv.setVisibility(View.VISIBLE);
                    yearv.setVisibility(View.GONE);
                    buttonyear.setText(syear);
                }
            });
            ll.addView(butt);
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while(true){
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
                String str=sdf.format(new Date());
                handler.sendMessage(handler.obtainMessage(100,str));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
