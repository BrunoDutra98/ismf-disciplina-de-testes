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
    
    
    
    
    
    
    
    
    
    
    @Test
    public void devolvendoValoresCertosAposEdicao () {

        Long EXISTING_ID = 1L;

        Survey existing = new Survey();
        existing.setPrefix("Prefixo");
        existing.setTitle("Titulo");
        existing.setDescription("Descrição");
        existing.setId(EXISTING_ID);

        when(surveyRepository.exists(EXISTING_ID)).thenReturn(true);
        when(surveyRepository.saveAndFlush(existing)).thenReturn(existing);

        Survey editedSurvey = surveyService.editSurvey(existing);

        assertEquals(existing.getTitle(), editedSurvey.getTitle());
        assertEquals(existing.getPrefix(), editedSurvey.getPrefix());
        assertEquals(existing.getDescription(), editedSurvey.getDescription());
    }

    @Test(expected = ComponentNotFoundException.class)
    public void enquantoTentarExcluirUmComponenteNaoExistente () {

        Long NOT_EXISTING_SURVEY_ID = 5L;
        when(surveyRepository.exists(NOT_EXISTING_SURVEY_ID)).thenThrow(new ComponentNotFoundException());

        surveyService.deleteSurvey(NOT_EXISTING_SURVEY_ID);
    }
      
    
      @Test
    public void adicionarPesquisa() {
        
        Survey survey = new Survey();
        survey.setPrefix("Prefixo");
        survey.setTitle("Titulo");
        survey.setDescription("Descrição");
        
        Survey surveyCompare = new Survey();
        surveyCompare.setPrefix("Prefixo");
        surveyCompare.setTitle("Titulo");
        surveyCompare.setDescription("Descrição");
        
        when(surveyRepository.saveAndFlush(survey)).thenReturn(survey);
        
        surveyService.addSurvey(survey);
         assertEquals(survey.getTitle(), surveyCompare.getTitle());
        assertEquals(survey.getPrefix(), surveyCompare.getPrefix());
        assertEquals(survey.getDescription(), surveyCompare.getDescription());
     }
    
    
    
    
    
      
    @Test
    public void editarPesquisa() {
        Long EXISTING_ID = 1L;
         Survey existing = new Survey();
        existing.setPrefix("Prefixo");
        existing.setTitle("Titulo");
        existing.setDescription("Descrição");
        existing.setId(EXISTING_ID);
         when(surveyRepository.exists(EXISTING_ID)).thenReturn(true);
        when(surveyRepository.saveAndFlush(existing)).thenReturn(existing);
        
        surveyService.editSurvey(existing);
         Survey editedSurvey = surveyService.editSurvey(existing);
         assertEquals(existing.getTitle(), editedSurvey.getTitle());
        assertEquals(existing.getPrefix(), editedSurvey.getPrefix());
        assertEquals(existing.getDescription(), editedSurvey.getDescription());
    }
    
    
    
    
    
    
    
    
    
    
    

}

