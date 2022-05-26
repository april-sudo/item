package carrotproject.domain;

import carrotproject.domain.ItemRegistered;
import carrotproject.domain.ItemSoldOutConfirmed;
import carrotproject.domain.ItemModified;
import carrotproject.domain.ItemAvaliableConfirmed;
import carrotproject.domain.ItemReserved;
import carrotproject.domain.ItemDeleted;
import carrotproject.ItemApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Item_table")
@Data

public class Item  {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    
    
    private String name;
    
    
    private String category;
    
    
    private Integer price;
    
    
    private String description;
    
    
    private String status;
    
    
    private Integer score;

    @PostPersist
    public void onPostPersist(){
        ItemRegistered itemRegistered = new ItemRegistered();
        BeanUtils.copyProperties(this, itemRegistered);
        itemRegistered.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
        ItemModified itemModified = new ItemModified();
        BeanUtils.copyProperties(this, itemModified);
        itemModified.publishAfterCommit();

        ItemAvaliableConfirmed itemAvaliableConfirmed = new ItemAvaliableConfirmed();
        BeanUtils.copyProperties(this, itemAvaliableConfirmed);
        itemAvaliableConfirmed.publishAfterCommit();

        ItemReserved itemReserved = new ItemReserved();
        BeanUtils.copyProperties(this, itemReserved);
        itemReserved.publishAfterCommit();

    }

    @PreRemove
    public void validateRemove() {
        repository().findById(this.itemId).ifPresent(item -> {
            try {
                // Get request from Reservation
                carrotproject.external.Reservation reservation =
                ItemApplication.applicationContext.getBean(carrotproject.external.ReservationService.class)
                .getReservation(this.itemId);

                if("CONFIRMED".equals(reservation.getRsvStatus()) || "COMPLETED".equals(reservation.getRsvStatus())) {
                    throw new RuntimeException("Can not delete Item!!!");
                }
            } catch (Exception e) {
                //404
            }
        });
    }

    @PostRemove
    public void onPostRemove(){
        ItemDeleted itemDeleted = new ItemDeleted();
        BeanUtils.copyProperties(this, itemDeleted);
        itemDeleted.publishAfterCommit();

    }


    public static ItemRepository repository(){
        ItemRepository itemRepository = ItemApplication.applicationContext.getBean(ItemRepository.class);
        return itemRepository;
    }


    public void confirmItemSoldOut(ReservationCompleted reservationCompleted){

        Item item = new Item();
        /*
        LOGIC GOES HERE
        */
        BeanUtils.copyProperties(this, item);
        item.setStatus("SOLDOUT");
        repository().save(item);
    }

    public void confirmItemReserved(ReservationConfirmed reservationConfirmed){

        Item item = new Item();
        /*
        LOGIC GOES HERE
        */
        // repository().save(item);
        BeanUtils.copyProperties(this, item);
        item.setStatus("RESERVED");

        repository().save(item);
    }

    public void confirmItemReserved(ReservationCompleteCacelled reservationCompleteCacelled){

        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        item.setStatus("CREATED");
        repository().save(item);
    }

    public void confirmItemAvaliable(ReservationDeleted reservationDeleted){

        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        item.setStatus("CREATED");

        repository().save(item);
    }

    public void updateItemScore(ReviewCreated reviewCreated) {
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        item.setScore(reviewCreated.getScore());

        repository().save(item);
    }

    public void updateItemScore(ReviewModified reviewModified) {
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        item.setScore(reviewModified.getScore());

        repository().save(item);
    }

    public void updateItemScore(ReviewDeleted reviewDeleted) {
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        item.setScore(0);

        repository().save(item);
    }


}
