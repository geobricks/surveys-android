package org.geobricks.survey.questions;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

	public class QuestionsPagerAdapter extends FragmentPagerAdapter {
		
//	    private Question[] mFragments;
		
		private List<Question> questions;
		
		FragmentManager fm;
		/**
		 * @param fm
		 * @param fragments
		 */
		public QuestionsPagerAdapter(FragmentManager fm, List<Question> questions) {
			super(fm);		
			Log.i("QuestionsPagerAdapter", "setting up the manager");
			this.questions = questions;
//			this.mFragments = new Question[questions.size()];
		}

		
//		@Override
//		public Fragment getItem(int position) {
////			Log.i("GET ITEM: ", String.valueOf(position));
////			return this.questions.get(position);
//		}
		
		// this is the key, do not destroy the item ( TODO: possible memory issue??)
		@Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	        Log.i("DESTROY FRAGMENT ITEM", "Do not destroy the fragment item");
	    }
		
		@Override
	       public Fragment getItem(int position) {
			return this.questions.get(position);
			
			// working as well
//	           if (mFragments[position] == null) {
//					Log.i("GET ITEM is null", String.valueOf(position));
//	               /* this calls the newInstance from when you setup the ListFragment */
//	               mFragments[position] = this.questions.get(position);
//	           }else
//					Log.i("GET ITEM is NOT null", String.valueOf(position));
//
//
//	           return mFragments[position];
	       }
		
		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount() {
			return this.questions.size();
		}
	}

