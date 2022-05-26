package carrotproject.domain;

import carrotproject.domain.*;
import carrotproject.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class ItemModified extends AbstractEvent {

    private Long itemId;
    private String name;
    private String category;
    private Integer price;
    private String description;
    private String status;
    private Integer score;

    public ItemModified(){
        super();
    }
}
