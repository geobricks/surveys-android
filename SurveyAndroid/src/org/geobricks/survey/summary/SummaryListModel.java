package org.geobricks.survey.summary;

import org.geobricks.survey.bean.AnswerBean;
import org.geobricks.survey.bean.SurveyBean;

public class SummaryListModel {
	
	public int icon;
    public AnswerBean answerBean;
    public SummaryListModel(){
        super();
    }
    
    // TODO: the icon should be derives from the survey status or survey type
    public SummaryListModel(int icon, AnswerBean answerBean) {
        super();
        this.icon = icon;
        this.answerBean = answerBean;
    }

}
