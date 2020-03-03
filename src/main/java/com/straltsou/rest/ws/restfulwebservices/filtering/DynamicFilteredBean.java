package com.straltsou.rest.ws.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter("DynamicFilteredBean")
public class DynamicFilteredBean {

    private String field1;

    private String field2;

    private String field3;
}
