package org.familysearch.ct.ws.rs.impl;

import org.gedcomx.common.URI;
import org.gedcomx.conclusion.rs.definition.XrdRSDefinition;
import org.gedcomx.xrd.Link;
import org.gedcomx.xrd.Title;
import org.gedcomx.xrd.XRD;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mike Gardiner
 */
@Path ( "/.well-known/host-meta" )
@Produces ( "application/xrd+xml" )
public class XrdRSImpl implements XrdRSDefinition {
  
  @Context
  UriInfo uriInfo;
  Date startupDate = Calendar.getInstance().getTime();

  @GET
  @Override
  public Response readXRD() {
    return Response.ok(buildXRD()).build();
  }

  @HEAD
  @Override
  public Response headXRD(@Context Request request) {

    Response.ResponseBuilder rb  = request.evaluatePreconditions(startupDate);

    if (rb == null) {
        return Response.ok().lastModified(startupDate).build();
    }
    else {
        return rb.build();
    }
  }

  @Override
  public void setProofStatement(String proofStatement) {

  }

  protected XRD buildXRD() {
    String basePath = "";

    if (uriInfo != null) {
        basePath = uriInfo.getBaseUri().getPath();
    }

    XRD xrd = new XRD();
    xrd.setLinks(new ArrayList<Link>());

    // Persons Link
    Link personsLink = new Link();
    personsLink.setHref(URI.create(basePath + "persons"));
    Title personsTitle = new Title();
    personsTitle.setValue("Persons");
    personsLink.getTitles().add(personsTitle);
    xrd.getLinks().add(personsLink);

    return xrd;
  }
}