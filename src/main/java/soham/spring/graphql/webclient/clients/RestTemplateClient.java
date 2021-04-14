package soham.spring.graphql.webclient.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import soham.spring.graphql.webclient.repsonse.provider.ProviderResponse;
import soham.spring.graphql.webclient.repsonse.provider.ProvidersResponse;
import soham.spring.graphql.webclient.repsonse.service.ServiceResponse;
import soham.spring.graphql.webclient.repsonse.service.ServicesResponse;
import soham.spring.graphql.webclient.resolver.MustacheResolver;

import java.io.IOException;

@Component
@Slf4j
public class RestTemplateClient {

    @Autowired
    private MustacheResolver mustacheResolver;

    @Value( "${graphql.endpoint}" )
    private String URL;

    public void invokeServicesEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ServicesResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(mustacheResolver.getServicesEndpointBody(), mustacheResolver.getHeaders()),
                ServicesResponse.class);
        log.info("********* Response of Services Endpoint with RestTemplate *************");
        log.info(resp.toString());
    }

    public void invokeServiceByIdEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ServiceResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(mustacheResolver.getServiceByIdEndpointBody("123"), mustacheResolver.getHeaders()),
                ServiceResponse.class);
        log.info("********* Response of ServiceByID Endpoint with RestTemplate *************");
        log.info(resp.toString());
    }

    public void invokeProviderByIdEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ProviderResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(mustacheResolver.getProviderByIdEndpointBody(), mustacheResolver.getHeaders()),
                ProviderResponse.class);
        log.info("********* Response of ProviderByID Endpoint with RestTemplate *************");
        log.info(resp.toString());
    }

    public void invokeProvidersEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ProvidersResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(mustacheResolver.getProvidersEndpointBody(), mustacheResolver.getHeaders()),
                ProvidersResponse.class);
        log.info("********* Response of Providers Endpoint with RestTemplate *************");
        log.info(resp.toString());
    }
}
