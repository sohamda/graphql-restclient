package soham.spring.graphql.webclient.repsonse.entity;

import lombok.Data;

import java.util.List;

@Data
public class Provider {
    String id;
    String name;
    String description;
    List<Service> services;
}
