package com.example.cms.order.controller;

import com.example.cms.order.controller.port.OrderService;
import com.example.cms.order.domain.OrderCreateRequest;
import com.example.cms.order.controller.response.OrderCreateResponse;
import com.example.cms.order.controller.response.OrderDetailResponse;
import com.example.cms.order.service.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<OrderCreateResponse> create(@RequestBody OrderCreateRequest request){
        return ResponseEntity.ok()
                .body(OrderCreateResponse
                        .from(orderService.createOrder(request)));
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "주문 결제 내역 조회", description = "주문 결제 내역을 조회합니다.")
    public ResponseEntity<List<OrderDetailResponse>> findOrderDetail(@PathVariable String orderId){
        return ResponseEntity.ok()
                .body(orderService.findByOrdersId(orderId)
                        .stream()
                        .map(OrderDetailResponse::from)
                        .collect(Collectors.toList()));
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "주문 및 결제 취소", description = "주문한 내역을 결제 취소합니다.")
    public void cancel(@PathVariable String orderId){
        orderService.cancel(orderId);
    }
}
