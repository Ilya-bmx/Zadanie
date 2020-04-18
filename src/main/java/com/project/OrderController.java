package com.project.utils;

import com.project.models.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/order")
@Slf4j
public class OrderController {

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    @POST
    public String createOrder(List<OrderForm> orderForm) {
        log.info(orderForm.toString());
        return "orderForm в обработке";
    }

}
