package org.geobricks.survey;

import java.util.List;
import java.util.Vector;

import org.geobricks.survey.R;
import org.geobricks.survey.activies.PagerAdapter;
import org.geobricks.survey.activies.Tab1Fragment;
import org.geobricks.survey.activies.Tab2Fragment;
import org.geobricks.survey.activies.Tab3Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;



public class ViewPagerFragmentActivity extends FragmentActivity {

		/** maintains the pager adapter*/
		private PagerAdapter mPagerAdapter;
		/* (non-Javadoc)
		 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
		 */
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			super.setContentView(R.layout.viewpager);
			//initialsie the pager
			this.initialisePaging();
		}

		/**
		 * Initialise the fragments to be paged
		 */
		private void initialisePaging() {

			List<Fragment> fragments = new Vector<Fragment>();
			fragments.add(Fragment.instantiate(this, Tab1Fragment.class.getName()));
			fragments.add(Fragment.instantiate(this, Tab2Fragment.class.getName()));
			fragments.add(Fragment.instantiate(this, Tab3Fragment.class.getName()));
			this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
			Log.i("output", "creaing");
			ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
			pager.setAdapter(this.mPagerAdapter);
		}
	}


