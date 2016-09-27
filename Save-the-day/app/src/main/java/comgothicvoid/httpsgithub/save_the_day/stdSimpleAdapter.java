package comgothicvoid.httpsgithub.save_the_day;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/24.
 */
public class stdSimpleAdapter extends SimpleAdapter {

    List<? extends Map<String, ?>> mdata;

    public stdSimpleAdapter(Context context, List<? extends Map<String, ?>> data,
                            int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.mdata = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView textView = (TextView) v.findViewById(R.id.mv2w);
        if(textView.getText().equals("SUNDAY")) textView.setTextColor(Color.RED);
        return v;
    }
}