package com.project.service;

import com.project.models.OrderForm;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@NoArgsConstructor
public class ProcessingOrderAsync {

    private static final String ORDERS_SUCCESS_RESULT = "List processed successfully";
    private static final String ORDERS_FAIL_RESULT = "One of orders can't be created, no opportunity to supply";

    /**
     * this method is needed to perform date of delivery
     * may be opportunity of creating
     *
     * @param orders ordered items
     */
    @Async("threadPoolTaskExecutor")
    public Future<String> processOrders(List<OrderForm> orders) throws Exception {
        //TODO: расчёт времени доставки , + возможности доставки всех товаров из разных поинтов
        for (int i = 0; i < 100; i++)
            System.out.println("ponomarevIlya");
        for (int i = 0; i < orders.size(); i++) {
             if (!processOrder(orders.get(i))) throw new Exception(ORDERS_FAIL_RESULT);
        }

        return new AsyncResult<>(ORDERS_SUCCESS_RESULT);
    }

    @Async("threadPoolTaskExecutor")
    public void processOrder2(List<OrderForm> orders) {
        //TODO: расчёт времени доставки , + возможности доставки всех товаров из разных поинтов
        for (int i = 0; i < 100; i++)
            System.out.println("ponomarevIlya2");
    }

    private boolean processOrder(OrderForm orderForm) {
        //тут мы как-то обрабатываем orderFrom , формируем заказ
        int value = (int)((Math.random() * 10) % 10);
        return value != 1;
    }
}
