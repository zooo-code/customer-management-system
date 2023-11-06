package com.example.cms.item.service;

import com.example.cms.item.controller.port.ItemService;
import com.example.cms.item.controller.request.ItemSearchRequest;
import com.example.cms.item.domain.ItemCreate;
import com.example.cms.item.domain.ItemUpdate;
import com.example.cms.item.domain.Item;

import com.example.cms.item.service.port.ItemRepository;
import com.example.cms.utils.common.service.port.ClockHolder;
import com.example.cms.utils.exception.CommonException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


import static com.example.cms.utils.exception.ErrorCode.DATA_NOT_FOUND;
import static com.example.cms.utils.exception.ErrorCode.DUPLICATE_RESOURCE;

@Builder
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ClockHolder clockHolder;

    @Override
    public Item findByName(String name) {
        return itemRepository
                .findByName(name)
                .orElseThrow(() -> new CommonException(DATA_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll(){
        return itemRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public List<Item> findAllByNameContaining(String name) {
        return itemRepository.findAllByNameContaining(name);
    }
    @Override
    @Transactional
    public void create(ItemCreate itemCreate){
        Item item = Item.from(itemCreate,clockHolder);
        //중복체크
        boolean isExistItemAndStatus = itemRepository
                .existsByNameAndAndHotIce(item.getName(), item.getHotIce());

        if(isExistItemAndStatus){
            Item originItem = itemRepository
                    .findByNameAndHotIce(item.getName(), item.getHotIce());
            validateDuplicate(originItem, item);
        }else {
            itemRepository.save(item);
        }
    }

    private void validateDuplicate(Item originItem, Item newItem){
        // 이름과 상태값이 같은 제품이 있을경우
        if(originItem != null && ( originItem.getCost().equals(newItem.getCost()))){
//            throw new IllegalStateException("중복되는 기존 메뉴가 있습니다. " + originItemEntity.getName() + "("+ originItemEntity.getHotIce() +") " +originItemEntity.getCost());
            throw new CommonException(DUPLICATE_RESOURCE);
        }else if(originItem != null && (!originItem.getCost().equals(newItem.getCost()))){
//            throw new IllegalStateException(newItemEntity.getName() +"("+newItemEntity.getHotIce()+") 는(운) 이미 "+originItemEntity.getCost()+"원 으로 책정되어있습니다.");
            throw new CommonException(DUPLICATE_RESOURCE);
        }
    }
    @Override
    @Transactional
    public Item update(ItemUpdate itemUpdate){


        Item item = itemRepository
                .findByName(itemUpdate.getName()).orElseThrow(()->new CommonException(DATA_NOT_FOUND));
        //name, cost, h/i 가 모두 중복
        boolean isDuplicated = itemRepository
                .existsByNameAndCostAndHotIce(itemUpdate.getName(), itemUpdate.getCost(), itemUpdate.getHotIce());
        if(isDuplicated) {
            throw new CommonException(DUPLICATE_RESOURCE);
        }
        Item update = item.update(itemUpdate);

        return itemRepository.save(update);
    }
    @Override
    @Transactional
    public void delete(Long itemId){
        itemRepository.deleteByItemId(itemId);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Item> searchItems(ItemSearchRequest itemSearchRequest) {
        return itemRepository
                .searchItems(itemSearchRequest.getName(),itemSearchRequest.getCost(),itemSearchRequest.getHotIce());
    }
//
//    public PageImpl<ItemResponse> searchItemsPaging(ItemSearchRequest itemSearchRequest, PageRequest pageRequest) {
//        Pageable pageable = pageRequest.of();
//        PageImpl<ItemEntity> searched = itemRepositoryJpa.searchItemsPaging(pageable, itemSearchRequest.toItem());
//        PageImpl<ItemResponse> result = (PageImpl<ItemResponse>) searched.map(ItemResponse::of);
//        return result;
//    }
}
