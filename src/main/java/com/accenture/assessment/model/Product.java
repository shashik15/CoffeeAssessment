package com.accenture.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;

/**
 * @author Shashi Kumari
 *
 * Product Model
 */
@Getter
@Setter
public class Product {

    @JsonProperty("drink_name")
    private String drinkName;
    private HashMap<String,Double> prices;
}
