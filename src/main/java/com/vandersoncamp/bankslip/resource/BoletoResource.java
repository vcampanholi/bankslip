package com.vandersoncamp.bankslip.resource;import com.vandersoncamp.bankslip.business.BoletoBusiness;import com.vandersoncamp.bankslip.model.Boleto;import javax.enterprise.context.RequestScoped;import javax.inject.Inject;import javax.ws.rs.*;import javax.ws.rs.core.MediaType;import javax.ws.rs.core.Response;@Path("/bankslips")@RequestScopedpublic class BoletoResource {    @Inject    private BoletoBusiness business;    @GET    @Path("{id}")    @Produces(MediaType.APPLICATION_JSON)    public Response find(@PathParam("id") String id) {        if (id.length() != 36) {            return Response.status(Response.Status.BAD_REQUEST).entity("ID_Invalid").build();        }        Boleto boleto = business.find(id);        if (boleto == null) {            return Response.status(Response.Status.NOT_FOUND).build();        }        return Response.ok(boleto).build();    }    @GET    @Produces(MediaType.APPLICATION_JSON)    public Response findAll(            @QueryParam("filterField") String filterField,            @QueryParam("filterValue") String filterValue,            @QueryParam("order") String order) {        return Response.ok(business.findAll(filterField, filterValue, order)).build();    }    @POST    @Produces(MediaType.APPLICATION_JSON)    @Consumes(MediaType.APPLICATION_JSON)    public Response create(Boleto boleto) {        business.create(boleto);        return Response.status(Response.Status.CREATED).entity(boleto).build();    }    @PUT    @Path("{id}")    @Produces(MediaType.APPLICATION_JSON)    @Consumes(MediaType.APPLICATION_JSON)    public Response update(Boleto boleto, @PathParam("id") String id) {        if (business.find(id) == null) {            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();        }        business.update(id, boleto);        return Response.status(Response.Status.OK).entity(boleto).build();    }}