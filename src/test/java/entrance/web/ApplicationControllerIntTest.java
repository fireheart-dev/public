package entrance.web;

import entrance.EntranceApplication;
import entrance.domain.entity.Application;
import entrance.domain.entity.Contact;
import entrance.domain.repository.api.ApplicationRepository;
import entrance.domain.repository.api.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EntranceApplication.class)
@AutoConfigureMockMvc
public class ApplicationControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ContactRepository contactRepository;

    private void createAndSaveApplication(String productName) {
        Contact contact = new Contact();
        contactRepository.save(contact);

        Application application = new Application();
        application.setProductName(productName);
        application.setContact(contact);

        applicationRepository.save(application);
    }

    @Test
    public void whenFindExistedApplicationsThenStatus200() throws Exception{
        createAndSaveApplication("intTestProduct");

        mvc.perform(get("/api/listByProductName/intTestProduct")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productName", is("intTestProduct")));
    }
}
