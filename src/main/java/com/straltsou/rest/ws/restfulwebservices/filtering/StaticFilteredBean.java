package com.straltsou.rest.ws.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(value = {"field1"})
@Data
@AllArgsConstructor
public class StaticFilteredBean {

    private String field1;

    @JsonIgnore
    private String field2;

    private String field3;
}
