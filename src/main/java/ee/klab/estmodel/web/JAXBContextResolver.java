package ee.klab.estmodel.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public final class JAXBContextResolver implements ContextResolver<ObjectMapper> {

    private static final ObjectMapper DEFAULT = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID)
            .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
            .registerModule(new JavaTimeModule())
            .registerModule(new Jdk8Module());

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return DEFAULT;
    }

}
