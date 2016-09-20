package ee.klab.water.web;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EstModelExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response
                .status(exception.getResponse().getStatusInfo())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity("")
                .build();
    }

}
