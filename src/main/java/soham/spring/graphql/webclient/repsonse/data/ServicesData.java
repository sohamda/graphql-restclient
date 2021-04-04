package soham.spring.graphql.webclient.repsonse.data;

import lombok.Data;
import soham.spring.graphql.webclient.repsonse.entity.Service;

import java.util.List;

@Data
public class ServicesData {
    List<Service> services;
}
