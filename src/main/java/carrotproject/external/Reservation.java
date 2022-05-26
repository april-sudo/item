package carrotproject.external;

import lombok.Data;
import java.util.Date;
@Data
public class Reservation {

    private Long rsvId;
    private String rsvStatus;
    private Date rsvDate;
    private String rsvPlace;
    private Long itemId;
    private String buyerId;
    public Object getRsvStatus() {
        return null;
    }


}
