package org.geobricks.survey.questions;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

	public class QuestionsPagerAdapter extends FragmentPagerAdapter {
		
//	    private Question[] mFragments;
		
		private ViewPager pager;

		private List<Question> questions;
		
	    private Fragment mCurrentPrimaryItem = null;
		
		FragmentManager fm;
		/**
		 * @param fm
		 * @param fragments
		 */
		public QuestionsPagerAdapter(ViewPager pager, FragmentManager fm, List<Question> questions) {
			super(fm);		
			Log.i("QuestionsPagerAdapter", "setting up the manager");
			this.questions = questions;
			this.pager = pager;
			this.fm = fm;
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
			Log.i("SURVEYANDROID", "DESTROY: "  + pager.getCurrentItem() + " | " + (questions.size() -1) );

			if ( pager.getCurrentItem() != questions.size() -1) {
				destroySummary(getItem(questions.size() -1));
			}
//			if ( questions.size() -1 == position || questions.size() -3 == position ) {
//				destroySummary(getItem(questions.size() -1));
//				// destroy that is the summary
////				Log.i("SURVEYANDROID", "destroy the fragment item, it's the summary");
////				 FragmentManager manager = ((Fragment) object).getFragmentManager();
////		         FragmentTransaction trans = manager.beginTransaction();
////		         trans.remove((Fragment) object);
////		         trans.commit();
//			}
////			else
////				Log.i("SURVEYANDROID", "Do not destroy the fragment item");
//				
//			Log.i("SURVEYANDROID", "DESTROY: " + (questions.size() -1) + " | " + position);
//			destroySummary(getItem(questions.size() -1));
	    }
		
	    public void destroySummary(Object object) {
	    	
	    	try {
				Log.i("SURVEYANDROID","always destroy summary");
				FragmentManager manager = ((Fragment) object).getFragmentManager();
			    FragmentTransaction trans = manager.beginTransaction();
			    trans.detach((Fragment)object);
			    trans.commit();
	    	}catch(Exception e ){
	    		Log.e("SURVEYANDROID", "failed to detach: " + e.getMessage());
	    	}
			
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
//		
//		
//	    @Override
//	    public Object instantiateItem(ViewGroup container, int position) {
//	    	Log.i("SURVEYANDRODI", "instantiateItem");
//		    FragmentTransaction mCurTransaction = fm.beginTransaction();
//	        if (mCurTransaction == null) {
//	            mCurTransaction = fm.beginTransaction();
//	        }
//
//	        // Do we already have this fragment?
//	        String name = makeFragmentName(container.getId(), position);
//	        Fragment fragment = fm.findFragmentByTag(name);
//	        if (fragment != null) {
////	            if (DEBUG) 
//	        	Log.i("SURVEYANDROID", "Attaching item #" + position + ": f=" + fragment);
//	            mCurTransaction.attach(fragment);
//	        } else {
//	            fragment = getItem(position);
////	            if (DEBUG) 
//	            Log.i("SURVEYANDROID", "Adding item #" + position + ": f=" + fragment);
//	            mCurTransaction.add(container.getId(), fragment,
//	            makeFragmentName(container.getId(), position));
//	        }
//	        if (fragment != mCurrentPrimaryItem) {
//	            fragment.setMenuVisibility(false);
//	            fragment.setUserVisibleHint(false);
//	        }
//
//	        return fragment;
//	    }
//	    
//	    private static String makeFragmentName(int viewId, int index) {
//	        return "android:switcher:" + viewId + ":" + index;
//	    }
//	    
//	    @Override
//	    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//	        Fragment fragment = (Fragment)object;
//	        if (fragment != mCurrentPrimaryItem) {
//	            if (mCurrentPrimaryItem != null) {
//	                mCurrentPrimaryItem.setMenuVisibility(false);
//	                mCurrentPrimaryItem.setUserVisibleHint(false);
//	            }
//	            if (fragment != null) {
//	                fragment.setMenuVisibility(true);
//	                fragment.setUserVisibleHint(true);
//	            }
//	            mCurrentPrimaryItem = fragment;
//	        }
//	    }
	}

