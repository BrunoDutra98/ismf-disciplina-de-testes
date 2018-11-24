package com.mackleaps.formium.service.survey;

import com.mackleaps.formium.Application;
import com.mackleaps.formium.exceptions.ComponentNotFoundException;
import com.mackleaps.formium.model.survey.Survey;
import com.mackleaps.formium.repository.survey.SurveyRepository;
import com.mackleaps.formium.service.survey.SurveyResultsRepositoryMock;
import com.mackleaps.formium.service.survey.SurveyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SurveyServiceTest {

    @Mock
    private SurveyRepository surveyRepository;

    private SurveyService surveyService;
    
    private SurveyResultsRepositoryMock surveyResultsRepository;

    @Before
    public void setup () {
        //SurveyResultsRepository is not used in the current test suite, so its dependency is passed as null
        surveyService = new SurveyService(surveyRepository,null);
        surveyService = new SurveyService(surveyRepository, surveyResultsRepository);
    }

    @Test(expected = ComponentNotFoundException.class)
    public void enquantoNaoExistirId() {

        final Long NOT_EXISTING_ID = 5L;

        when(surveyRepository.findOne(NOT_EXISTING_ID)).thenReturn(null);

        Survey survey = new Survey();
        survey.setId(NOT_EXISTING_ID);
        survey.setTitle("Novo titulo");
        survey.setDescription("Nova descrição");
        survey.setPrefix("Novo prefixo");

        surveyService.editSurvey(survey);
    }

}

