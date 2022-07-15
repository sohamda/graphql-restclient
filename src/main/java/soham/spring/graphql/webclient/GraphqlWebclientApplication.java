package soham.spring.graphql.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import soham.spring.graphql.webclient.clients.DgsClient;


@SpringBootApplication
@RequiredArgsConstructor
public class GraphqlWebclientApplication {

	private final DgsClient dgsClient;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlWebclientApplication.class, args);
	}


	@EventListener
	public void withDgsClient(ApplicationReadyEvent event) {
		dgsClient.invokeServicesEndpoint();
	}
}
