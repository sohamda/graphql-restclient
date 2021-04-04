package soham.spring.graphql.webclient.repsonse.data;

import lombok.Data;
import soham.spring.graphql.webclient.repsonse.entity.Provider;

import java.util.List;

@Data
public class ProvidersData {
    List<Provider> providers;
}
