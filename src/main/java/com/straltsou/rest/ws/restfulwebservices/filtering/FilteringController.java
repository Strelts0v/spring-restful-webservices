package com.straltsou.rest.ws.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.val;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/static-filtering")
    public StaticFilteredBean getStaticFilteredBean() {
        return new StaticFilteredBean("field1Value", "field2Value", "field3Value");
    }

    @GetMapping(path = "/static-filtering-list")
    public List<StaticFilteredBean> getStaticFilteredBeanList() {
        List<StaticFilteredBean> beans = new ArrayList<>();

        beans.add(new StaticFilteredBean("1-field1Value", "1-field2Value", "1-field3Value"));
        beans.add(new StaticFilteredBean("2-field1Value", "2-field2Value", "2-field3Value"));
        beans.add(new StaticFilteredBean("3-field1Value", "3-field2Value", "3-field3Value"));

        return beans;
    }

    @GetMapping(path = "/dynamic-filtering")
    public MappingJacksonValue getDynamicBean(){
        val bean = new DynamicFilteredBean("field1Value", "field2Value", "field3Value");

        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        val filters = new SimpleFilterProvider().addFilter("DynamicFilteredBean", filter);
        val mapping = new MappingJacksonValue(bean);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(path = "/dynamic-filtering-list")
    public MappingJacksonValue getDynamicBeanList(){
        List<DynamicFilteredBean> beans = new ArrayList<>();

        beans.add(new DynamicFilteredBean("1-field1Value", "1-field2Value", "1-field3Value"));
        beans.add(new DynamicFilteredBean("2-field1Value", "2-field2Value", "2-field3Value"));
        beans.add(new DynamicFilteredBean("3-field1Value", "3-field2Value", "3-field3Value"));

        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        val filters = new SimpleFilterProvider().addFilter("DynamicFilteredBean", filter);
        val mapping = new MappingJacksonValue(beans);
        mapping.setFilters(filters);

        return mapping;
    }
}
