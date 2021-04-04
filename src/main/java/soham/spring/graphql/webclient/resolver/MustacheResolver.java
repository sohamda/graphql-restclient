package soham.spring.graphql.webclient.resolver;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import soham.spring.graphql.webclient.repsonse.provider.ProviderResponse;
import soham.spring.graphql.webclient.repsonse.provider.ProvidersResponse;
import soham.spring.graphql.webclient.repsonse.service.ServiceResponse;
import soham.spring.graphql.webclient.repsonse.service.ServicesResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MustacheResolver {

    @Value( "${graphql.endpoint}" )
    private String URL;

    public void invokeServicesEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ServicesResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(getServicesEndpointBody(), getHeaders()), ServicesResponse.class);
        log.info(resp.toString());
    }

    public void invokeServiceByIdEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ServiceResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(getServiceByIdEndpointBody(), getHeaders()), ServiceResponse.class);
        log.info(resp.toString());
    }

    public void invokeProviderByIdEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ProviderResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(getProviderByIdEndpointBody(), getHeaders()), ProviderResponse.class);
        log.info(resp.toString());
    }

    public void invokeProvidersEndpoint(RestTemplate restTemplate) throws IOException {
        ResponseEntity<ProvidersResponse> resp = restTemplate.postForEntity(URL,
                new HttpEntity<>(getProvidersEndpointBody(), getHeaders()), ProvidersResponse.class);
        log.info(resp.toString());
    }

    private String getServicesEndpointBody() throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent("templates/services.template"));

        Map<String, Boolean> data = new HashMap<>();
        data.put("s_id", Boolean.TRUE);
        data.put("s_name", Boolean.TRUE);
        data.put("provider", Boolean.TRUE);
        data.put("p_name", Boolean.TRUE);

        return template.execute(data);
    }

    private String getServiceByIdEndpointBody() throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent("templates/serviceById.template"));

        Map<String, Object> data = new HashMap<>();
        data.put("serviceId", "123");
        data.put("s_id", Boolean.TRUE);
        data.put("s_name", Boolean.TRUE);
        data.put("s_description", Boolean.TRUE);
        return template.execute(data);
    }

    private String getProviderByIdEndpointBody() throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent("templates/providerById.template"));

        Map<String, Object> data = new HashMap<>();
        data.put("providerId", "2");
        data.put("p_name", Boolean.TRUE);
        data.put("p_id", Boolean.TRUE);
        data.put("service", Boolean.TRUE);
        data.put("s_name", Boolean.TRUE);
        return template.execute(data);
    }

    private String getProvidersEndpointBody() throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent("templates/providers.template"));

        Map<String, Boolean> data = new HashMap<>();
        data.put("p_name", Boolean.TRUE);
        data.put("p_id", Boolean.TRUE);

        return template.execute(data);
    }

    private String getTemplateContent(String templatePath) throws IOException {
        Resource resource = new ClassPathResource(templatePath);
        return new String(resource.getInputStream().readAllBytes());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/graphql");
        return headers;
    }


    /*
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl("http://localhost:8083")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            Mono<String> resp = webClient.post().uri("/graphql/providerservice")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(getInvokeServiceEndpointBody()), String.class)
                    //.bodyValue(BodyInserters.fromValue(getInvokeServiceEndpointBody()))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(String.class);
            System.out.println(resp.block());
            //getInvokeServiceEndpointBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
