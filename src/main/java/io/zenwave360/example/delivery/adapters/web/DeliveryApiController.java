package io.zenwave360.example.delivery.adapters.web;

import io.zenwave360.example.delivery.adapters.web.dtos.*;
import io.zenwave360.example.delivery.adapters.web.mappers.*;
import io.zenwave360.example.delivery.core.domain.*;
import io.zenwave360.example.delivery.core.inbound.*;
import io.zenwave360.example.delivery.core.inbound.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/** REST controller for DeliveryApi. */
@RestController
@RequestMapping("/api")
public class DeliveryApiController implements DeliveryApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private DeliveryService deliveryService;

    @Autowired
    public DeliveryApiController setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        return this;
    }

    private DeliveryDTOsMapper mapper = DeliveryDTOsMapper.INSTANCE;

    public DeliveryApiController(DeliveryService deliveryService) {

        this.deliveryService = deliveryService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<DeliveryDTO> updateDeliveryStatus(String orderId, DeliveryStatusInputDTO reqBody) {
        var input = mapper.asDeliveryStatusInput(reqBody);
        var delivery = deliveryService.updateDeliveryStatus(orderId, input);
        DeliveryDTO responseDTO = mapper.asDeliveryDTO(delivery);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<DeliveryPaginatedDTO> listDeliveries(Integer page, Integer limit,
            List<String> sort) {
        var deliveryPage = deliveryService.listDeliveries(pageOf(page, limit, sort));
        var responseDTO = mapper.asDeliveryPaginatedDTO(deliveryPage);
        return ResponseEntity.status(200).body(responseDTO);
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null ? Sort.by(sort.stream().map(sortParam -> {
            String[] parts = sortParam.split(":");
            String property = parts[0];
            Sort.Direction direction = parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
            return new Sort.Order(direction, property);
        }).toList()) : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }

}
