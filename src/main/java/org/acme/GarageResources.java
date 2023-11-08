package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.DatabaseType;

import java.util.List;

@Path("/garage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GarageResources {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    Garage garage;

    @GET
    public List<Car> list() {
        return garage.findAll().toList();
    }

    public record CarResponse(String id, String owner) {
    }

    public record NewCar(String owner) {
    }

    @POST
    public CarResponse add(NewCar request) {
        var newCar = Car.create(request.owner());
        garage.save(newCar);
        return new CarResponse(newCar.getId(), newCar.getOwner());
    }

}