package ee.klab.estmodel.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.ws.rs.WebApplicationException;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class LocalDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType,
            final Type genericType, final Annotation[] annotations) {

        if (LocalDate.class.equals(rawType)) {
            return (ParamConverter<T>) new LocalDateConverter();
        } else {
            return null;
        }

    }

    public static class LocalDateConverter implements ParamConverter<LocalDate> {

        @Override
        public LocalDate fromString(final String string) {
            try {
                return LocalDate.parse(string);
            } catch (final DateTimeParseException exception) {
                throw new WebApplicationException(exception, BAD_REQUEST);
            }
        }

        @Override
        public String toString(final LocalDate date) {
            return date.toString();
        }

    }

}
