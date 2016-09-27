package comgothicvoid.httpsgithub.save_the_day;

/**
 * Created by Administrator on 2016/9/23.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ABAdapter extends BaseAdapter {
    //itemA类的type标志
    private static final int TYPE_A = 0;
    //itemB类的type标志
    private static final int TYPE_B = 1;

    private Context context;

    //整合数据
    private List<Object> data = new ArrayList<>();


    public ABAdapter(Context context, ArrayList<Object> o) {
        this.context = context;

        //把数据装载同一个list里面
        //这里把所有数据都转为object类型是为了装载同一个list里面好进行排序

        data.addAll(o);

    }

    /**
     * 获得itemView的type
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int result = 0;
        if (data.get(position) instanceof Journal) {
            result = TYPE_A;
        } else if (data.get(position) instanceof Point) {
            result = TYPE_B;
        }
        return result;
    }

    /**
     * 获得有多少中view type
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //创建两种不同种类的viewHolder变量
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        //根据position获得View的type
        int type = getItemViewType(position);
        if (convertView == null) {
            //实例化
            holder1 = new ViewHolder1();
            holder2 = new ViewHolder2();
            //根据不同的type 来inflate不同的item layout
            //然后设置不同的tag
            //这里的tag设置是用的资源ID作为Key
            switch (type) {
                case TYPE_A:
                    convertView = View.inflate(context, R.layout.preview, null);
                    holder1.way = (TextView) convertView.findViewById(R.id.wayt);
                    holder1.day = (TextView) convertView.findViewById(R.id.dayt);
                    holder1.journal = (TextView) convertView.findViewById(R.id.jourt);
                    convertView.setTag(R.id.tag_first, holder1);
                    break;
                case TYPE_B:
                    convertView = View.inflate(context, R.layout.point, null);
                    holder2.img = (ImageView) convertView.findViewById(R.id.img);
                    convertView.setTag(R.id.tag_second, holder2);
                    break;
            }

        } else {
            //根据不同的type来获得tag
            switch (type) {
                case TYPE_A:
                    holder1 = (ViewHolder1) convertView.getTag(R.id.tag_first);
                    break;
                case TYPE_B:
                    holder2 = (ViewHolder2) convertView.getTag(R.id.tag_second);
                    break;
            }
        }

        Object o = data.get(position);
        //根据不同的type设置数据
        switch (type) {
            case TYPE_A:
                Journal a = (Journal) o;
                holder1.way.setText(a.getWay().substring(0, 3));
                holder1.day.setText(a.getDay());
                holder1.journal.setText(a.getText());
                if(a.getWay().equals("SUNDAY")) holder1.day.setTextColor(Color.RED);
                else holder1.day.setTextColor(Color.BLACK);
                break;

            case TYPE_B:
                Point b = (Point) o;
                if(b.issd) holder2.img.setImageResource(R.drawable.redpoint);
                else holder2.img.setImageResource(R.drawable.point);
                break;
        }
        return convertView;
    }

    /**
     * item A 的Viewholder
     */
    private static class ViewHolder1 {
        TextView way;
        TextView day;
        TextView journal;
    }

    /**
     * item B 的Viewholder
     */
    private static class ViewHolder2 {
        ImageView img;
    }

}

