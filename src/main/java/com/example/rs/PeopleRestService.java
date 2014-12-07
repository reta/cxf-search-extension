package com.example.rs;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;

import com.example.model.Person;

@Path( "/people" ) 
public class PeopleRestService {
    private final Collection< Person > people = new ArrayList<>();
       
    @Produces( { MediaType.APPLICATION_JSON  } )
    @POST
    public Response addPerson( @Context final UriInfo uriInfo,
            @FormParam( "firstName" ) final String firstName, 
            @FormParam( "lastName" ) final String lastName,
            @FormParam( "age" ) final int age ) {      
        
        final Person person = new Person( firstName, lastName, age );
        people.add( person );
        
        return Response
            .created( uriInfo.getRequestUriBuilder().path( "/search" )
            .queryParam( "$filter=firstName eq '{firstName}' and lastName eq '{lastName}' and age eq {age}" )
            .build( firstName, lastName, age ) )
            .entity( person ).build();
    }
    
    @GET
    @Path("/search")
    @Produces( { MediaType.APPLICATION_JSON  } )
    public Collection< Person > findPeople( @Context SearchContext searchContext ) {        
        final SearchCondition< Person > filter = searchContext.getCondition( Person.class );
        return filter.findAll( people );
    }
}
