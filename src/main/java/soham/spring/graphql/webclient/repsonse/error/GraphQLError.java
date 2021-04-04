package soham.spring.graphql.webclient.repsonse.error;

import lombok.Data;

import java.util.List;

@Data
public class GraphQLError {

    String message;
    List<String> path;
    Extensions extensions;
    List<Locations> locations;
}
