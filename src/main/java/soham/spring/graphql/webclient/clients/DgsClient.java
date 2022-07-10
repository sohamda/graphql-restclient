package soham.spring.graphql.webclient.clients;

import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import soham.spring.graphql.model.client.ServicesGraphQLQuery;
import soham.spring.graphql.model.client.ServicesProjectionRoot;

@Component
public class DgsClient {

    @Value( "${graphql.endpoint}" )
    private String URL;

    public void invokeServicesEndpoint() {

        GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(ServicesGraphQLQuery.newRequest().build()
                , new ServicesProjectionRoot().name().description().provider().description() );
        WebClient webClient = WebClient.create("http://localhost:8080/graphql");

    }
}
