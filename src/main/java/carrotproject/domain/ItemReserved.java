package carrotproject.domain;

import carrotproject.domain.*;
import carrotproject.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class ItemReserved extends AbstractEvent {

    private Long id;
    private Long itemId;
    private String name;
    private String category;
    private Integer price;
    private String description;
    private String status;
    private Integer score;

    public ItemReserved(){
        super();
    }
}
