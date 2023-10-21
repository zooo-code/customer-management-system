package com.example.cms.item.controller;

import com.example.cms.item.controller.request.ItemCreateRequest;
import com.example.cms.item.controller.request.ItemSearchRequest;
import com.example.cms.item.controller.request.ItemUpdateRequest;
import com.example.cms.item.controller.request.PageRequest;
import com.example.cms.item.controller.response.ItemResponse;
import com.example.cms.item.service.ItemServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Items", description = "상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemServiceImpl itemServiceImpl;



    @Operation(summary = "상품 목록 조회", description = "상품 목록 전부 조회합니다.")
    @GetMapping
    public List<ItemResponse> findAll(){
        return itemServiceImpl.findAll();
    }

    @Operation(summary = "상품 이름으로 조회", description = "상품 이름으로 Containing 조회합니다.")
    @GetMapping("/{itemName}")
    public List<ItemResponse> findByName(@PathVariable("itemName") String name){
        return itemServiceImpl.findByName(name);
    }

    @Operation(summary = "신규 상품 추가", description = "신규 상품을 추가합니다. 같은 이름의 메뉴를 중복체크합니다.")
    @PostMapping
    public void create(@RequestBody ItemCreateRequest itemCreateRequest){
        itemServiceImpl.create(itemCreateRequest);
    }

    @Operation(summary = "상품 정보 수정", description = "상품 정보를 수정합니다.")
    @PostMapping("/update")
    public void update(@RequestBody ItemUpdateRequest itemUpdateRequest){
        itemServiceImpl.update(itemUpdateRequest);
    }


    @Operation(summary = "상품 삭제", description = "상품 id로 개별 삭제")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        itemServiceImpl.delete(id);
    }

    //TODO: 필터링 완료
//    @GetMapping("/search")
//    @Operation(summary = "상품 검색", description = "상품을 조건으로 검색합니다.")
//    public List<ItemResponse> searchItems(@RequestBody ItemSearchRequest itemSearchRequest){
//        return itemServiceImpl.searchItems(itemSearchRequest);
//    }
//
//    @GetMapping("/search/paging")
//    @Operation(summary = "상품 검색", description = "상품을 조건으로 검색합니다.")
//    public PageImpl<ItemResponse> searchItems(@RequestBody ItemSearchRequest itemSearchRequest, PageRequest pageRequest){
//        return itemServiceImpl.searchItemsPaging(itemSearchRequest, pageRequest);
//    }
}
