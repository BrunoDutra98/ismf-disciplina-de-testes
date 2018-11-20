/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mackleaps.formium.service.survey;

import com.mackleaps.formium.model.survey.Survey;
import com.mackleaps.formium.model.survey_application.Company;
import com.mackleaps.formium.model.survey_application.SurveyResults;
import com.mackleaps.formium.repository.survey_application.SurveyResultsRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LGPereira
 */
public abstract class SurveyResultsRepositoryMock implements SurveyResultsRepository {

    @Override
    public SurveyResults findByCompanyIdAndAndCorrespondingSurveyId(Long companyId, Long correspondingSurveyId) {
        Company mycompany = new Company("Mackenzie", "12345678915951");
        Survey mysurvey = new Survey("4J", "Suvery Mack", "This is a survey");
        SurveyResults surveyResults = new SurveyResults(mycompany, mysurvey);
        return surveyResults;
    }

    @Override
    public boolean existsSurveyResultsByCompanyIdAndCorrespondingSurveyId(Long companyId, Long correspondingSurveyId) {
        return true;
    }

    @Override
    public List<SurveyResults> findByCorrespondingSurveyId(Long surveyId) {
        List<SurveyResults> mySurveyList = new ArrayList<>();
        Company mycompany = new Company("Mackenzie", "12345678915951");
        Survey mysurvey = new Survey("4J", "Suvery Mack", "This is a survey");
        SurveyResults surveyResults = new SurveyResults(mycompany, mysurvey);
        mySurveyList.add(surveyResults);
        return mySurveyList;
    }
}