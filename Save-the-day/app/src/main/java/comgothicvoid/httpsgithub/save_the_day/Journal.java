package comgothicvoid.httpsgithub.save_the_day;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/21.
 */
public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String year;
    private String month;
    private String day;
    private String way;
    private String text;

    public String getYear(){
        return year;
    }
    public String getMonth(){
        return month;
    }
    public String getDay(){
        return day;
    }
    public String getWay(){
        return way;
    }
    public String getText(){
        return text;
    }

    public void setYear(String year){
        this.year = year;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setWay(String way) {
        this.way = way;
    }
    public void setText(String text) {
        this.text = text;
    }
}
