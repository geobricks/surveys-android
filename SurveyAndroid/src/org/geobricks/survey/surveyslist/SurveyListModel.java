package org.geobricks.survey.surveyslist;

import org.geobricks.survey.bean.SurveyBean;

public class SurveyListModel {
	
	public int icon;
    public SurveyBean surveyBean;
    public SurveyListModel(){
        super();
    }
    
    // TODO: the icon should be derives from the survey status or survey type
    public SurveyListModel(int icon, SurveyBean surveyBean) {
        super();
        this.icon = icon;
        this.surveyBean = surveyBean;
    }

}
