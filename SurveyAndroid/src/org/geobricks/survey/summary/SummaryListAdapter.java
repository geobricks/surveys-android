package org.geobricks.survey.summary;

import org.geobricks.survey.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryListAdapter extends ArrayAdapter<SummaryListModel>{

    Context context; 
    int layoutResourceId;    
    SummaryListModel data[] = null;
    
    public SummaryListAdapter(Context context, int layoutResourceId, SummaryListModel[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new WeatherHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.listview_row_icon);
            holder.txtName = (TextView)row.findViewById(R.id.listview_row_name);
            holder.txtAbstract = (TextView)row.findViewById(R.id.listview_rowsurvey_abstract);

            
            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }
        
        SummaryListModel model = data[position];
        holder.txtName.setText(model.answerBean.getQuestionBean().getText());
        holder.txtAbstract.setText(model.answerBean.getTextResult());
        holder.imgIcon.setImageResource(model.icon);
        
        return row;
    }
    
    static class WeatherHolder
    {
        ImageView imgIcon;
        TextView txtName;
        TextView txtAbstract;
    }
}
