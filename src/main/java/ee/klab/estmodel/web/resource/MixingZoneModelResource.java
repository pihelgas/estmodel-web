package ee.klab.estmodel.web.resource;

import ee.klab.estmodel.web.resource.model.MixingZone;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("models/mixing-zone")
public class MixingZoneModelResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MixingZone runMixingZoneModel(ee.klab.estmodel.MixingZone zone) {

        MixingZone mixingZone = new MixingZone();
        mixingZone.setLength(zone.estimateLength());
        return mixingZone;

    }

}
