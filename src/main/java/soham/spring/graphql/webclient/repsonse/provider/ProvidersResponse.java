package soham.spring.graphql.webclient.repsonse.provider;

import lombok.Data;
import soham.spring.graphql.webclient.repsonse.data.ProvidersData;
import soham.spring.graphql.webclient.repsonse.error.GraphQLError;

import java.util.List;

@Data
public class ProvidersResponse {

    ProvidersData data;
    List<GraphQLError> errors;
}
