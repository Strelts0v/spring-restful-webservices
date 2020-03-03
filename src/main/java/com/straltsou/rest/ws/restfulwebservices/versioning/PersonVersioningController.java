package com.straltsou.rest.ws.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping(path = "/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 personV2() {
        return new PersonV2("John", "Doe");
    }

    @GetMapping(path = "/person", params="version=1")
    public PersonV1 personParamV1() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path = "/person", params="version=2")
    public PersonV2 personParamV2() {
        return new PersonV2("John", "Doe");
    }

    @GetMapping(path = "/person/header", headers="X-API-VERSION=1")
    public PersonV1 personHeaderV1() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path = "/person/header", headers="X-API-VERSION=2")
    public PersonV2 personHeaderV2() {
        return new PersonV2("John", "Doe");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2("Bob", "Charlie");
    }
}
