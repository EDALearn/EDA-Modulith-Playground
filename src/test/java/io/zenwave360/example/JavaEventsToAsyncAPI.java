package io.zenwave360.example;

import io.zenwave360.example.orders.core.outbound.events.EventPublisher;
import io.zenwave360.sdk.options.asyncapi.AsyncapiVersionType;
import io.zenwave360.sdk.plugins.JavaToAsyncAPIGenerator;

import java.io.IOException;
import java.util.List;

//DEPS io.zenwave360.sdk.plugins:java-to-asyncapi:2.0.0
public class JavaEventsToAsyncAPI {

    public static void main(String[] args) throws IOException {
        String asyncapi = new JavaToAsyncAPIGenerator()
                .withEventProducerClass(EventPublisher.class) // <-- your event publisher class
                .withServiceName("OrdersService")
                .withAsyncapiVersion(AsyncapiVersionType.v3)
                .withIncludeKafkaCommonHeaders(true)
                .withIncludeCloudEventsHeaders(true)
                .withAsyncapiMergeFile("src/main/resources/public/apis/_base-asyncapi.yml")
//                .withAsyncapiOverlayFiles(List.of("src/main/resources/public/apis/asyncapi-overlay.yml"))
                .withTargetFile("src/main/resources/public/apis/asyncapi-orders.yml")
                .generate();
        System.out.println(asyncapi); // printing for debug purposes
    }
}
