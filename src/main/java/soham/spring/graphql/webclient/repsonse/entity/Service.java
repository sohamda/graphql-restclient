package soham.spring.graphql.webclient.repsonse.entity;

import lombok.Data;

@Data
public class Service {

    String id;
    String name;
    String description;
    Provider provider;
}
