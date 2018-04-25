package entrance.service;

import entrance.domain.entity.Application;
import entrance.domain.entity.Contact;
import entrance.domain.repository.api.ApplicationRepository;
import entrance.domain.repository.api.ContactRepository;
import entrance.service.api.ApplicationService;
import entrance.service.impl.ApplicationServiceImpl;
import entrance.service.mapper.ApplicationMapper;
import entrance.web.dto.ApplicationDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class ApplicationServiceTest {

    @TestConfiguration
    static class ApplicationServiceTestContextConfiguration {

        @Bean
        public ApplicationService applicationService() {
            return new ApplicationServiceImpl();
        }

        @Bean
        public ApplicationMapper applicationMapper() {
            return new ApplicationMapper();
        }
    }

    @Autowired
    private ApplicationService applicationService;

    @MockBean
    private ApplicationRepository applicationRepository;

    @MockBean
    private ContactRepository contactRepository;

    @Before
    public void setup() {
        Application application = new Application();
        application.setProductName("testProductName");
        application.setContact(new Contact());

        Mockito.when(applicationRepository.findByProductName(application.getProductName()))
                .thenReturn(Arrays.asList(application));
    }

    @Test
    public void whenValidProductName_thenApplicationShouldBeFound() {
        String productName = "testProductName";

        ApplicationDto found = applicationService.findByProductName(productName).get(0);

        Assert.assertEquals(productName, found.getProductName());
    }
}

