package com.example.cms.item.controller;

import com.example.cms.item.controller.port.ItemService;
import com.example.cms.item.controller.request.ItemSearchRequest;
import com.example.cms.item.controller.response.ItemResponse;
import com.example.cms.item.domain.Item;
import com.example.cms.item.domain.ItemCreate;
import com.example.cms.item.domain.ItemUpdate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Tag(name = "Items", description = "상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {


    private final ItemService itemService;

    @Operation(summary = "상품 목록 조회", description = "상품 목록 전부 조회합니다.")
    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAll(){
        List<Item> all = itemService.findAll();
        List<ItemResponse> collect = all.stream().map(ItemResponse::from).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collect);
    }

    @Operation(summary = "상품 이름으로 조회", description = "상품 이름으로 Containing 조회합니다.")
    @GetMapping("/{itemName}")
    public ResponseEntity<List<ItemResponse>> findByName(@PathVariable("itemName") String name){
        List<Item> allByNameContaining = itemService.findAllByNameContaining(name);
        List<ItemResponse> itemResponses = allByNameContaining.stream().map(ItemResponse::from).toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemResponses);
    }

    @Operation(summary = "신규 상품 추가", description = "신규 상품을 추가합니다. 같은 이름의 메뉴를 중복체크합니다.")
    @PostMapping
    public ResponseEntity<ItemResponse> create(@RequestBody ItemCreate itemCreate){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ItemResponse.from(itemService.create(itemCreate)));
    }

    @Operation(summary = "상품 정보 수정", description = "상품 정보를 수정합니다.")
    @PostMapping("/update")
    public ResponseEntity<ItemResponse> update(@RequestBody ItemUpdate itemUpdate){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ItemResponse.from(itemService.update(itemUpdate)));
    }


    @Operation(summary = "상품 삭제", description = "상품 id로 개별 삭제")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        itemService.delete(id);
    }

    //TODO:
    @GetMapping("/search")
    @Operation(summary = "상품 검색", description = "상품을 조건으로 검색합니다.")
    public List<ItemResponse> searchItems(@RequestBody ItemSearchRequest itemSearchRequest){
        return itemService
                .searchItems(itemSearchRequest)
                .stream()
                .map(ItemResponse::from)
                .toList();
    }
//
//    @GetMapping("/search/paging")
//    @Operation(summary = "상품 검색", description = "상품을 조건으로 검색합니다.")
//    public PageImpl<ItemResponse> searchItems(@RequestBody ItemSearchRequest itemSearchRequest, PageRequest pageRequest){
//        return itemServiceImpl.searchItemsPaging(itemSearchRequest, pageRequest);
//    }
}
