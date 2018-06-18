package ee.klab.estmodel.web;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public final class JsonbContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper defaultObjectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
            .enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
            .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
            .setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP))
            .registerModule(new AfterburnerModule())
            .registerModule(new Jdk8Module());

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return this.defaultObjectMapper;
    }

}
