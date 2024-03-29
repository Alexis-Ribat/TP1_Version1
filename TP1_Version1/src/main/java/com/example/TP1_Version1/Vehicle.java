package com.example.TP1_Version1;

import javax.persistence.Column;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = Van.class, name = "van")
})

@Entity
	
	public abstract class Vehicle {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @NonNull
	    @Column(unique = true)
	    private String plateNumber;

	    @JsonIgnore
	    @OneToMany(targetEntity = Rent.class,
	            mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private List<Rent> rents = new ArrayList<>();

	    Vehicle() {
	    }

	    Vehicle(String plateNumber) {
	        this.plateNumber = plateNumber;
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getPlateNumber() {
	        return plateNumber;
	    }

	    public void setPlateNumber(String plateNumber) {
	        this.plateNumber = plateNumber;
	    }

	    public List<Rent> getRents() {
	        return rents;
	    }

	    public void setRents(List<Rent> rents) {
	        this.rents = rents;
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();

	        return sb
	                .append("(plateNumber: ")
	                .append(plateNumber)
	                .append(", rent: ")
	                .append(")").toString();
	    }
}

