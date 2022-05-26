package carrotproject.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;

import carrotproject.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import carrotproject.domain.*;


@Service
public class PolicyHandler{
    @Autowired ItemRepository itemRepository;
    @Autowired ReviewRepository reviewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCompleted_ConfirmItemSoldOut(@Payload ReservationCompleted reservationCompleted){

        if(!reservationCompleted.validate()) return;
        ReservationCompleted event = reservationCompleted;
        System.out.println("\n\n##### listener ConfirmItemSoldOut : " + reservationCompleted.toJson() + "\n\n");

        //item.confirmItemSoldOut();
        itemRepository.findById(event.getItemId()).ifPresent(item -> item.confirmItemSoldOut(event));
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewCreated_UpdateItemScore(@Payload ReviewCreated reviewCreated){

        if(!reviewCreated.validate()) return;
        ReviewCreated event = reviewCreated;
        System.out.println("\n\n##### listener UpdateItemScore : " + reviewCreated.toJson() + "\n\n");

        itemRepository.findById(event.getItemId()).ifPresent(item -> item.updateItemScore(event));
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewModified_UpdateItemScore(@Payload ReviewModified reviewModified){

        if(!reviewModified.validate()) return;
        ReviewModified event = reviewModified;
        System.out.println("\n\n##### listener UpdateItemScore : " + reviewModified.toJson() + "\n\n");


        itemRepository.findById(event.getItemId()).ifPresent(item -> item.updateItemScore(event));
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewDeleted_UpdateItemScore(@Payload ReviewDeleted reviewDeleted){

        if(!reviewDeleted.validate()) return;
        ReviewDeleted event = reviewDeleted;
        System.out.println("\n\n##### listener UpdateItemScore : " + reviewDeleted.toJson() + "\n\n");

        itemRepository.findById(event.getItemId()).ifPresent(item -> item.updateItemScore(event));
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationConfirmed_ConfirmItemReserved(@Payload ReservationConfirmed reservationConfirmed){

        if(!reservationConfirmed.validate()) return;
        ReservationConfirmed event = reservationConfirmed;
        System.out.println("\n\n##### listener ConfirmItemReserved : " + reservationConfirmed.toJson() + "\n\n");
 
        itemRepository.findById(event.getItemId()).ifPresent(item -> item.confirmItemReserved(event));

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCompleteCacelled_ConfirmItemReserved(@Payload ReservationCompleteCacelled reservationCompleteCacelled){

        if(!reservationCompleteCacelled.validate()) return;
        ReservationCompleteCacelled event = reservationCompleteCacelled;
        System.out.println("\n\n##### listener ConfirmItemReserved : " + reservationCompleteCacelled.toJson() + "\n\n");
        
        itemRepository.findById(event.getItemId()).ifPresent(item -> item.confirmItemReserved(event));
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationDeleted_ConfirmItemAvaliable(@Payload ReservationDeleted reservationDeleted){

        if(!reservationDeleted.validate()) return;
        ReservationDeleted event = reservationDeleted;
        System.out.println("\n\n##### listener ConfirmItemAvaliable : " + reservationDeleted.toJson() + "\n\n");

        itemRepository.findById(event.getItemId()).ifPresent(item -> item.confirmItemAvaliable(event));
    }


}


