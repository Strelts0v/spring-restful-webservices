package com.straltsou.rest.ws.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @Generated
    private Integer id;

    private String description;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;
}
