package soham.spring.graphql.webclient.repsonse.service;

import lombok.Data;
import soham.spring.graphql.webclient.repsonse.data.ServicesData;
import soham.spring.graphql.webclient.repsonse.error.GraphQLError;

import java.util.List;

@Data
public class ServicesResponse {

    ServicesData data;
    List<GraphQLError> errors;
}
