package carrotproject.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="reservation", url="${api.path.reservation}", fallback = ReservationServiceImpl.class)
public interface ReservationService {
    @RequestMapping(method= RequestMethod.GET, path="/reservations/{rsvId}")
    public Reservation getReservation(@PathVariable("rsvId") Long rsvId);

}

