package ee.klab.estmodel.web.resource;

import ee.klab.estmodel.LakeEstModel;
import ee.klab.estmodel.LakeEstModel.Estimate;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("models/lake-estmodel")
public class LakeEstModelResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Estimate> runLakeEstModel(final LakeEstModel model) {

        return model.estimate();

    }

}
