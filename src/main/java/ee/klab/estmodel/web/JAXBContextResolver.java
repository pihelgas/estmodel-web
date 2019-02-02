package ee.klab.estmodel.web;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JAXBContextResolver implements ContextResolver<ObjectMapper> {

    private static final ObjectMapper CONTEXT = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
            .enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)
            .setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP))
            .registerModule(new AfterburnerModule())
            .registerModule(new Jdk8Module())
            .registerModule(new SimpleModule()
                    .addSerializer(Enum.class, new EnumSerializer())
                    .setDeserializerModifier(new EnumDeserializerModifier()));

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return CONTEXT;
    }

    private static class EnumDeserializer extends JsonDeserializer<Enum> {

        private final JavaType type;

        public EnumDeserializer(JavaType type) {
            this.type = type;
        }

        @Override
        public Enum deserialize(JsonParser parser, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            return Enum.valueOf((Class<Enum>) this.type.getRawClass(),
                    parser.getValueAsString().replace('-', '_').toUpperCase());

        }

    }

    private static class EnumDeserializerModifier
            extends BeanDeserializerModifier {

        @Override
        public JsonDeserializer<Enum> modifyEnumDeserializer(
                DeserializationConfig config, JavaType type,
                BeanDescription beanDesc, JsonDeserializer<?> deserializer) {

            return new EnumDeserializer(type);

        }

    }

    private static class EnumSerializer extends JsonSerializer<Enum> {

        @Override
        public void serialize(Enum value, JsonGenerator generator,
                SerializerProvider provider) throws IOException {

            generator.writeString(value.name().replace('_', '-').toLowerCase());

        }

    }

}
