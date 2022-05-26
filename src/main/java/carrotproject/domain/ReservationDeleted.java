package carrotproject.domain;

import carrotproject.domain.*;
import carrotproject.infra.AbstractEvent;
import lombok.Data;
import java.util.Date;
@Data
public class ReservationDeleted extends AbstractEvent {

    private Long rsvId;
    private String rsvStatus;
    private Date rsvDate;
    private String rsvPlace;
    private Long itemId;
    private String buyerId;

}

