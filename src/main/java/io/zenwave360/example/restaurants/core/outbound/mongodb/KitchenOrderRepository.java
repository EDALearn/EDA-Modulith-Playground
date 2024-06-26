package io.zenwave360.example.restaurants.core.outbound.mongodb;

import io.zenwave360.example.restaurants.core.domain.KitchenOrder;
import io.zenwave360.example.restaurants.core.domain.KitchenOrderAggregate;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** Spring Data MongoDB repository for the KitchenOrder entity. */
@SuppressWarnings("unused")
@Repository
public interface KitchenOrderRepository extends MongoRepository<KitchenOrder, String> {

    default Optional<KitchenOrderAggregate> findKitchenOrderAggregateById(String id) {
        return findById(id).map(KitchenOrderAggregate::new);
    }

    Optional<KitchenOrder> findByOrderId(String orderId);

}
