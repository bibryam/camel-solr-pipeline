package com.ofbizian.pipeline;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class LatLongAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Customer customer = oldExchange.getIn().getBody(Customer.class);
        Double lat = newExchange.getIn().getHeader("lat", Double.class);
        Double lng = newExchange.getIn().getHeader("lng", Double.class);

        customer.setLatitude(lat);
        customer.setLongitude(lng);

        return oldExchange;
    }

}
