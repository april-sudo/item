package carrotproject.common;


import carrotproject.ItemApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { ItemApplication.class })
public class CucumberSpingConfiguration {
    
}
