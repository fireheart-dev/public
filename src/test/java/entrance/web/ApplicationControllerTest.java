package entrance.web;

import entrance.domain.entity.Application;
import entrance.domain.entity.Contact;
import entrance.service.api.ApplicationService;
import entrance.service.mapper.ApplicationMapper;
import entrance.web.controller.ApplicationController;
import entrance.web.dto.ApplicationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

    @TestConfiguration
    static class ApplicationControllerTestContextConfiguration {

        @Bean
        public ApplicationMapper applicationMapper() {
            return new ApplicationMapper();
        }
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationMapper applicationMapper;

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void whenFindApplicationsThenReturnJsonArray() throws Exception{
        Application application = new Application();
        application.setProductName("testProductName");
        application.setContact(new Contact());

        List<ApplicationDto> found = Arrays.asList(applicationMapper.entityToDto(application));

        BDDMockito.given(applicationService.findByProductName(application.getProductName())).willReturn(found);

        mvc.perform(get("/api/listByProductName/testProductName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productName", is(application.getProductName())));
    }
}
