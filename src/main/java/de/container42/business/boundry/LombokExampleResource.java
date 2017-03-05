package de.container42.business.boundry;

import de.container42.business.entity.LombokExampleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by edi on 05.03.17.
 */
@Stateless
@Path("example")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class LombokExampleResource {

    private static final Logger LOG = Logger.getLogger(LombokExampleResource.class.getName());

    @PersistenceContext(unitName = "prod")
    EntityManager manager;

    @GET
    @Path("{id}")
    public Response getEntity(@PathParam("id") long id) {
        LombokExampleEntity e = manager.find(LombokExampleEntity.class, id);
        return Response.ok().entity(e).build();
    }

    @GET
    public Response allEntities() {
        List<LombokExampleEntity> entityList = manager.createNamedQuery(LombokExampleEntity.ALL_ENTITIES).getResultList();
        GenericEntity<List<LombokExampleEntity>> list = new GenericEntity<List<LombokExampleEntity>>(entityList) {};
        return Response.ok().entity(list).build();
    }

    @PUT
    public Response addEntity(LombokExampleEntity entity, @Context UriInfo info) {
        URI uri = info.getAbsolutePathBuilder().path("/" + manager.merge(entity).getId()).build();
        return Response.created(uri).build();
    }
}
