package soham.spring.graphql.webclient.repsonse.provider;

import lombok.Data;
import soham.spring.graphql.webclient.repsonse.data.ProviderData;
import soham.spring.graphql.webclient.repsonse.error.GraphQLError;

import java.util.List;

@Data
public class ProviderResponse {

    ProviderData data;
    List<GraphQLError> errors;
}
