package carrotproject.domain;

import carrotproject.domain.*;
import carrotproject.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class ReviewDeleted extends AbstractEvent {

    private Long reviewId;
    private Long itemId;
    private Integer score;

    public ReviewDeleted(){
        super();
    }
}
