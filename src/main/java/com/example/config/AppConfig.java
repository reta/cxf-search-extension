package com.example.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.ext.search.SearchContextProvider;
import org.apache.cxf.jaxrs.ext.search.odata.ODataParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.example.model.Person;
import com.example.rs.JaxRsApiApplication;
import com.example.rs.PeopleRestService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
public class AppConfig {    
    @Bean( destroyMethod = "shutdown" )
    public SpringBus cxf() {
        return new SpringBus();
    }
    
    @Bean @DependsOn( "cxf" )
    public Server jaxRsServer() {
        final Map< String, Object > properties = new HashMap< String, Object >();        
        properties.put( "search.query.parameter.name", "$filter" );
        properties.put( "search.parser", new ODataParser< Person >( Person.class ) );

        final JAXRSServerFactoryBean factory = 
            RuntimeDelegate.getInstance().createEndpoint( 
                jaxRsApiApplication(), 
                JAXRSServerFactoryBean.class 
            );
        factory.setProvider( new SearchContextProvider() );
        factory.setProvider( new JacksonJsonProvider() );
        factory.setServiceBeans( Arrays.< Object >asList( peopleRestService() ) );
        factory.setAddress( factory.getAddress() );      
        factory.setProperties( properties );

        return factory.create();
    }
    
    @Bean 
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
    
    @Bean 
    public PeopleRestService peopleRestService() {
        return new PeopleRestService();
    }   
}
