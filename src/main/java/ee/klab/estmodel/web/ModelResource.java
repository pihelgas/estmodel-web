package ee.klab.estmodel.web;

import ee.klab.estmodel.EstModel;
import ee.klab.estmodel.LakeEstModel;
import ee.klab.estmodel.MixingZone;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("models")
public final class ModelResource {

    @POST
    @Path("estmodel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstModel runEstModel(EstModel model) {

        return model;

    }

    @POST
    @Path("lake-estmodel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LakeEstModel runLakeEstModel(LakeEstModel model) {

        return model;

    }

    @POST
    @Path("mixing-zone")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MixingZone runMixingZoneModel(MixingZone model) {

        return model;

    }

}
