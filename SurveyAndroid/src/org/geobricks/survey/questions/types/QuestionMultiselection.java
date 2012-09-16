package org.geobricks.survey.questions.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.geobricks.survey.R;
import org.geobricks.survey.utils.Data;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionMultiselection extends QuestionValue  {
	
  LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);

  LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		
  
  private ListView mainListView ;
  private Data[] values ;
  private ArrayAdapter<Data> listAdapter ;
  
  public QuestionMultiselection(Context context) {
	  this.context = context;
  }
  
  /** Called when the activity is first created. */
  public LinearLayout build(List<Data> data) {
	  panel = new LinearLayout(context);
//	  panel.setLayoutParams(param);
	  
    
    // Find the ListView resource. 
    mainListView =  new ListView(context);
    mainListView.setLayoutParams(param);
    
    // When item is tapped, toggle checked properties of CheckBox and Planet.
    mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick( AdapterView<?> parent, View item, int position, long id) {
        Data planet = listAdapter.getItem( position );
        planet.toggleChecked();
        DatatViewHolder viewHolder = (DatatViewHolder) item.getTag();
        viewHolder.getCheckBox().setChecked( planet.isChecked() );
      }
    });

    
    // Create and populate planets.
//    planets = (Planet[]) contegetLastNonConfigurationInstance() ;
//    if ( planets == null ) {
    values = new Data[data.size()];
      for(int i=0; i < data.size(); i++)
    	  values[i] = data.get(i);
      
//          new Data("Mercury"), new Planet("Venus"), new Planet("Earth"), 
//          new Data("Mars"), new Planet("Jupiter"), new Planet("Saturn"), 
//          new Data("Uranus"), new Planet("Neptune"), new Planet("Ceres"),
//          new Data("Pluto"), new Planet("Haumea"), new Planet("Makemake"),
//          new Data("Eris")
//      };  
    
    ArrayList<Data> planetList = new ArrayList<Data>();
    planetList.addAll( Arrays.asList(values) );
    
    // Set our custom array adapter as the ListView's adapter.
    listAdapter = new PlanetArrayAdapter(context, planetList);
    mainListView.setAdapter( listAdapter ); 
    
    panel.addView(mainListView, param);
    return panel;
  }
  
  
  /** Holds child views for one row. */
  private static class DatatViewHolder {
    private CheckBox checkBox ;
    private TextView textView ;
    public DatatViewHolder() {}
    public DatatViewHolder( TextView textView, CheckBox checkBox ) {
      this.checkBox = checkBox ;
      this.textView = textView ;
    }
    public CheckBox getCheckBox() {
      return checkBox;
    }
    public void setCheckBox(CheckBox checkBox) {
      this.checkBox = checkBox;
    }
    public TextView getTextView() {
      return textView;
    }
    public void setTextView(TextView textView) {
      this.textView = textView;
    }    
  }
  
  /** Custom adapter for displaying an array of Planet objects. */
  private static class PlanetArrayAdapter extends ArrayAdapter<Data> {
    
    private LayoutInflater inflater;
    
    public PlanetArrayAdapter( Context context, List<Data> planetList ) {
      super( context, R.layout.simplerow, R.id.rowTextView, planetList );
      // Cache the LayoutInflate to avoid asking for a new one each time.
      inflater = LayoutInflater.from(context) ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      // Planet to display
    	Data planet = (Data) this.getItem( position ); 

      // The child views in each row.
      CheckBox checkBox ; 
      TextView textView ; 
      
      // Create a new row view
      if ( convertView == null ) {
        convertView = inflater.inflate(R.layout.simplerow, null);
        
        // Find the child views.
        textView = (TextView) convertView.findViewById( R.id.rowTextView );
        checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );
        
        // Optimization: Tag the row with it's child views, so we don't have to 
        // call findViewById() later when we reuse the row.
        convertView.setTag( new DatatViewHolder(textView,checkBox) );

        // If CheckBox is toggled, update the planet it is tagged with.
        checkBox.setOnClickListener( new View.OnClickListener() {
          public void onClick(View v) {
            CheckBox cb = (CheckBox) v ;
            Data planet = (Data) cb.getTag();
            planet.setChecked( cb.isChecked() );
          }
        });        
      }
      // Reuse existing row view
      else {
        // Because we use a ViewHolder, we avoid having to call findViewById().
    	 DatatViewHolder viewHolder = (DatatViewHolder) convertView.getTag();
        checkBox = viewHolder.getCheckBox() ;
        textView = viewHolder.getTextView() ;
      }

      // Tag the CheckBox with the Data it is displaying, so that we can
      // access the planet in onClick() when the CheckBox is toggled.
      checkBox.setTag( planet ); 
      
      // Display planet data
      checkBox.setChecked( planet.isChecked() );
      textView.setText( planet.getLabel() );      
      
      return convertView;
    }
    
  }

  public Data[] getValues() {
	return values;
}

public Object onRetainNonConfigurationInstance() {
    return values ;
  }

public ArrayAdapter<Data> getListAdapter() {
	return listAdapter;
}


}