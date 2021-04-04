package soham.spring.graphql.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;
import soham.spring.graphql.webclient.resolver.MustacheResolver;

import java.io.IOException;

@SpringBootApplication
public class GraphqlWebclientApplication {

	@Autowired
	MustacheResolver mustacheResolver;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlWebclientApplication.class, args);
	}


	@EventListener
	public void withRestTemplate(ApplicationReadyEvent event) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			mustacheResolver.invokeServicesEndpoint(restTemplate);
			mustacheResolver.invokeServiceByIdEndpoint(restTemplate);

			mustacheResolver.invokeProviderByIdEndpoint(restTemplate);
			mustacheResolver.invokeProvidersEndpoint(restTemplate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
