package soham.spring.graphql.webclient.clients;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import soham.spring.graphql.webclient.repsonse.entity.Provider;
import soham.spring.graphql.webclient.repsonse.entity.Service;
import soham.spring.graphql.webclient.resolver.MustacheResolver;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class GraphQLClient {

    private final GraphQLWebClient graphQLWebClient;
    private final MustacheResolver mustacheResolver;


    public void invokeServicesEndpoint() throws IOException {

        GraphQLRequest request = GraphQLRequest.builder().query(mustacheResolver.getServicesEndpointBody()).build();
        GraphQLResponse response = graphQLWebClient.post(request).block();
        log.info("********* Response of Services Endpoint with GraphQLWebClient *************");
        log.info(response.getList("services", Service.class).toString());
    }

    public void invokeServiceByIdEndpoint() throws IOException {
        GraphQLRequest request = GraphQLRequest.builder().query(mustacheResolver.getServiceByIdEndpointBody("2")).build();
        GraphQLResponse response = graphQLWebClient.post(request).block();
        log.info("********* Response of ServiceByID Endpoint with GraphQLWebClient *************");
        log.info(response.get("serviceById", Service.class).toString());
    }

    public void invokeServiceByIdEndpointWithErrors() throws IOException {
        GraphQLRequest request = GraphQLRequest.builder().query(mustacheResolver.getServiceByIdEndpointBody("123")).build();
        GraphQLResponse response = graphQLWebClient.post(request).block();
        log.info("********* Response/Error of ServicesByID Endpoint with GraphQLWebClient *************");
        log.info(response.getErrors().toString());
    }

    public void invokeProviderByIdEndpoint() throws IOException {
        GraphQLRequest request = GraphQLRequest.builder().query(mustacheResolver.getProviderByIdEndpointBody()).build();
        GraphQLResponse response = graphQLWebClient.post(request).block();
        log.info("********* Response of ProviderByID Endpoint with GraphQLWebClient *************");
        log.info(response.get("providerById", Provider.class).toString());
    }

    public void invokeProvidersEndpoint() throws IOException {
        GraphQLRequest request = GraphQLRequest.builder().query(mustacheResolver.getProvidersEndpointBody()).build();
        GraphQLResponse response = graphQLWebClient.post(request).block();
        log.info("********* Response of Providers Endpoint with GraphQLWebClient *************");
        log.info(response.getList("providers", Provider.class).toString());
    }


}
