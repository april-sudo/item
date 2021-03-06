package carrotproject.domain;

import carrotproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="items", path="items")
public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{


}
