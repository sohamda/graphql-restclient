package soham.spring.graphql.webclient.resolver;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MustacheResolver {

    @Value( "${template.services}" )
    private String servicesTemplate;
    @Value( "${template.serviceById}" )
    private String serviceByIdTemplate;
    @Value( "${template.providers}" )
    private String providersTemplate;
    @Value( "${template.providerById}" )
    private String provierByIdTemplate;

    public String getServicesEndpointBody() throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent(servicesTemplate));

        Map<String, Boolean> data = new HashMap<>();
        data.put("s_id", Boolean.TRUE);
        data.put("s_name", Boolean.TRUE);
        data.put("provider", Boolean.TRUE);
        data.put("p_name", Boolean.TRUE);

        return template.execute(data);
    }

    public String getServiceByIdEndpointBody(String serviceId) throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent(serviceByIdTemplate));

        Map<String, Object> data = new HashMap<>();
        data.put("serviceId", serviceId);
        data.put("s_id", Boolean.TRUE);
        data.put("s_name", Boolean.TRUE);
        data.put("s_description", Boolean.TRUE);
        return template.execute(data);
    }

    public String getProviderByIdEndpointBody() throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent(provierByIdTemplate));

        Map<String, Object> data = new HashMap<>();
        data.put("providerId", "2");
        data.put("p_name", Boolean.TRUE);
        data.put("p_id", Boolean.TRUE);
        data.put("service", Boolean.TRUE);
        data.put("s_name", Boolean.TRUE);
        return template.execute(data);
    }

    public String getProvidersEndpointBody() throws IOException {

        Template template = Mustache.compiler().defaultValue("").compile(getTemplateContent(providersTemplate));

        Map<String, Boolean> data = new HashMap<>();
        data.put("p_name", Boolean.TRUE);
        data.put("p_id", Boolean.TRUE);

        return template.execute(data);
    }

    private String getTemplateContent(String templatePath) throws IOException {
        Resource resource = new ClassPathResource(templatePath);
        return new String(resource.getInputStream().readAllBytes());
    }
}
