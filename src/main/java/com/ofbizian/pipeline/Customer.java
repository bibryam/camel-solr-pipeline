package com.ofbizian.pipeline;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.apache.camel.component.jpa.Consumed;
import org.apache.solr.client.solrj.beans.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@NamedQuery(name = "newCustomers", query = "select c from Customer c where c.step = 0")
public class Customer {
    private static final transient Logger LOG = LoggerFactory.getLogger(Customer.class);

    @Field
    private Long id;
    @Field("description")
    private String address;
    private int step;

    @Field("attr_latitude")
    private Double latitude;
    @Field("attr_longitude")
    private Double longitude;

    public Customer() {
    }

    public Customer(String address) {
        setAddress(address);
        setStep(1);
    }

    @Override
    public String toString() {
        return "Customer[id: " + getId() + " step: " + getStep() + " address: " + getAddress() + "]";
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    /**
     * This method is invoked after the entity bean is processed successfully by a Camel endpoint
     */
    @Consumed
    public void goToNextStep() {
        setStep(getStep() + 1);
        LOG.info("Invoked the completion complete method. Now updated the step to: " + getStep());
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

