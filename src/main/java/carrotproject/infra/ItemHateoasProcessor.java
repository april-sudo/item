package carrotproject.infra;
import carrotproject.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class ItemHateoasProcessor implements RepresentationModelProcessor<EntityModel<Item>>  {

    @Override
    public EntityModel<Item> process(EntityModel<Item> model) {
        
        return model;
    }
    
}

