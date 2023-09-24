package com.example.cms.order.controller;

import com.example.cms.order.controller.request.OrderCreateRequest;
import com.example.cms.order.controller.response.OrderCreateResponse;
import com.example.cms.order.controller.response.OrderDetailResponse;
import com.example.cms.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "주문 결제 API")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //TODO: 취소

    @PostMapping
    @Operation(summary = "주문 및 결제", description = "주문한 내역을 결제합니다. 일단 포인트 결제만 구현")
    public OrderCreateResponse create(@RequestBody OrderCreateRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "주문 결제 내역 조회", description = "주문 결제 내역을 조회합니다.")
    public List<OrderDetailResponse> findOrderDetail(@PathVariable String orderId){
        return orderService.findByOrdersId(orderId);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "주문 및 결제 취소", description = "주문한 내역을 결제 취소합니다.")
    public void cancel(@PathVariable String orderId){
        orderService.cancel(orderId);
    }
}
