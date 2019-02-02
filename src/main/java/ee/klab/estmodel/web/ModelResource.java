package ee.klab.estmodel.web;

import ee.klab.estmodel.EstModel;
import ee.klab.estmodel.LakeEstModel;
import ee.klab.estmodel.MixingZoneModel;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("models")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModelResource {

    @POST
    @Path("estmodel")
    public EstModel runEstModel(@Valid @NotNull EstModel model) {
        model.run();
        return model;
    }

    @POST
    @Path("lake-estmodel")
    public LakeEstModel runLakeEstModel(@Valid @NotNull LakeEstModel model) {
        model.run();
        return model;
    }

    @POST
    @Path("mixing-zone")
    public MixingZoneModel runMixingZoneModel(@Valid @NotNull MixingZoneModel model) {
        model.run();
        return model;
    }

}
