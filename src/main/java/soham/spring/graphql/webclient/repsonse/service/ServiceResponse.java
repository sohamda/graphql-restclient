package soham.spring.graphql.webclient.repsonse.service;

import lombok.Data;
import soham.spring.graphql.webclient.repsonse.data.ServiceData;
import soham.spring.graphql.webclient.repsonse.error.GraphQLError;

import java.util.List;

@Data
public class ServiceResponse {

    ServiceData data;
    List<GraphQLError> errors;
}
