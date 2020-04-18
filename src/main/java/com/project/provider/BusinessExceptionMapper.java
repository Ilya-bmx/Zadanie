package com.project.provider;



import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper extends AbstractExceptionMapper implements ExceptionMapper<BusinessException> {
    
    @Override
    public Response toResponse(BusinessException e) {
        System.out.println("aweeeeeeeeeeeeeeeeed");
        return Response.status(401).entity(e.getMessage()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}