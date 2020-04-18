package com.project;

import com.project.models.OrderForm;
import com.project.service.OrderService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    @POST
    public String createOrder(List<OrderForm> orderForm) {
        log.info(orderForm.toString());

        String status = null;
        try {
            status = orderService.createOrder(orderForm);
        } catch (Exception e) {
            log.info("Ошибка залогирована , заказ не сформирован " + status);
        }

        return "orderForm в обработке:" + status;
    }

}
