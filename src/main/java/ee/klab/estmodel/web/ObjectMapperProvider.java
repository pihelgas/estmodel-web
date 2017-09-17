package ee.klab.estmodel.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public final class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
            .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
            .registerModule(new JavaTimeModule());

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return DEFAULT_MAPPER;
    }

}