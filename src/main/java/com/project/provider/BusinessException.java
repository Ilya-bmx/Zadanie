package com.project.provider;



import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessException extends Exception implements ExceptionMapper<BusinessException> {


    public BusinessException(String name) {
        super(name);
    }

    @PostConstruct
    public void ahah() {
        System.out.println("created");
    }

    @Override
    public Response toResponse(BusinessException e) {
        System.out.println("here");
        return Response.status(500).entity(e.getMessage()).type("text/plain").build();
    }
}