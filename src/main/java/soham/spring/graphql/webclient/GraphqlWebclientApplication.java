package soham.spring.graphql.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;
import soham.spring.graphql.webclient.clients.GraphQLClient;
import soham.spring.graphql.webclient.clients.RestTemplateClient;

import java.io.IOException;

@SpringBootApplication
public class GraphqlWebclientApplication {

	@Autowired
	RestTemplateClient restTemplateClient;
	@Autowired
	GraphQLClient graphQLClient;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlWebclientApplication.class, args);
	}


	@EventListener
	public void withRestTemplate(ApplicationReadyEvent event) {
		try {
			usingRestTemplate();
			usingGraphQLWebCLient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void usingGraphQLWebCLient() throws IOException {
		graphQLClient.invokeServicesEndpoint();
		graphQLClient.invokeServiceByIdEndpoint();
		graphQLClient.invokeProviderByIdEndpoint();
		graphQLClient.invokeProvidersEndpoint();
		graphQLClient.invokeServiceByIdEndpointWithErrors();
	}

	private void usingRestTemplate() throws IOException {
		RestTemplate restTemplate = new RestTemplate();

		restTemplateClient.invokeServicesEndpoint(restTemplate);
		restTemplateClient.invokeServiceByIdEndpoint(restTemplate);
		restTemplateClient.invokeProviderByIdEndpoint(restTemplate);
		restTemplateClient.invokeProvidersEndpoint(restTemplate);
	}

}
