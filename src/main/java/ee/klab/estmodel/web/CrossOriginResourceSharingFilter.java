package ee.klab.estmodel.web;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public final class CrossOriginResourceSharingFilter
        implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request,
            ContainerResponseContext response) {

        final MultivaluedMap<String, Object> headers = response.getHeaders();

        headers.putSingle("Access-Control-Allow-Origin", "*");
        headers.putSingle("Access-Control-Allow-Methods", "OPTIONS, POST");
        headers.putSingle("Access-Control-Allow-Headers", "Content-Type");

    }

}
