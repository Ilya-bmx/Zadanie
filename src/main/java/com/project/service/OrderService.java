package com.project.service;

import com.project.entities.Order;
import com.project.entities.Point;
import com.project.models.OrderForm;
import com.project.repos.OrderRepository;
import com.project.repos.PointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final ProcessingOrderAsync processingOrderAsync;
    private final OrderRepository orderRepository;
    private final PointsRepository pointsRepository;

    /**
     * add list of orders to a database
     *
     * @param orders list of orders
     * @return success of fail status TODO:мб енумку сделать
     */
    @Transactional(rollbackOn = Exception.class)
    public String createOrder(List<OrderForm> orders) throws Exception {
        Future<String> processingResult = processingOrderAsync.processOrders(orders);
        processingOrderAsync.processOrder2(orders);
        System.out.println(1);
        Set<Point> pointSet = new HashSet<>(pointsRepository.findAll());
        System.out.println(2);
        orders.stream()
                //TODO: отрефакторить это отвратительно
                .map(order -> new Order(Long.valueOf(order.getIndex().toString()), order.getTitle() + " " + order.getDescription(), new BigDecimal(order.getPrice()), pointSet))
                .forEach(orderRepository::save);
        log.info(orderRepository.findAll().toString());
        return processingResult.get();
    }

}
