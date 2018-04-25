package entrance.domain;


import entrance.domain.entity.Application;
import entrance.domain.repository.api.ApplicationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void whenFindByNameThenReturnApplication() {
        Application application = new Application();
        application.setProductName("testName");

        applicationRepository.save(application);
        Application found = applicationRepository.findByProductName("testName").get(0);

        Assert.assertEquals(application.getProductName(), found.getProductName());
    }
}
