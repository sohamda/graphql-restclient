package soham.spring.graphql.webclient.clients;

import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import com.netflix.graphql.dgs.client.WebClientGraphQLClient;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import soham.spring.graphql.model.client.ServicesGraphQLQuery;
import soham.spring.graphql.model.client.ServicesProjectionRoot;
import soham.spring.graphql.model.types.Service;

import java.util.List;

@Component
@Slf4j
public class DgsClient {

    @Value( "${graphql.endpoint}" )
    private String URL;

    public void invokeServicesEndpoint() {

        GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(ServicesGraphQLQuery.newRequest().build()
                , new ServicesProjectionRoot().name().description().provider().description() );
        log.info("Query to execute: {}", graphQLQueryRequest.serialize());
        WebClient webClient = WebClient.create(URL);
        WebClientGraphQLClient webClientGraphQLClient = MonoGraphQLClient.createWithWebClient(webClient);
        Mono<GraphQLResponse> graphQLResponseMono = webClientGraphQLClient.reactiveExecuteQuery(graphQLQueryRequest.serialize());
        Mono<List<Service>> servicesProjectionRoot = graphQLResponseMono.map(r -> {
            log.info("JSON response : {}", r.getJson());
            return r.extractValueAsObject("data.services", new TypeRef<>() {
            });
        });
        graphQLResponseMono.subscribe();

        log.info("Mapped object: {}", servicesProjectionRoot.block());
    }
}
