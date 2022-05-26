package carrotproject.domain;

import carrotproject.domain.ReviewCreated;
import carrotproject.domain.ReviewModified;
import carrotproject.domain.ReviewDeleted;
import carrotproject.ItemApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Review_table")
@Data

public class Review  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long reviewId;
    
    
    private Long itemId;
    
    
    private Integer score;

    @PostPersist
    public void onPostPersist(){
        ReviewCreated reviewCreated = new ReviewCreated();
        BeanUtils.copyProperties(this, reviewCreated);
        reviewCreated.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){
        ReviewModified reviewModified = new ReviewModified();
        BeanUtils.copyProperties(this, reviewModified);
        reviewModified.publishAfterCommit();

    }
    @PostRemove
    public void onPostRemove(){
        ReviewDeleted reviewDeleted = new ReviewDeleted();
        BeanUtils.copyProperties(this, reviewDeleted);
        reviewDeleted.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
    }


    public static ReviewRepository repository(){
        ReviewRepository reviewRepository = ItemApplication.applicationContext.getBean(ReviewRepository.class);
        return reviewRepository;
    }




}
