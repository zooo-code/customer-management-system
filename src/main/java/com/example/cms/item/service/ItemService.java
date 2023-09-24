package com.example.cms.item.service;

import com.example.cms.item.controller.request.ItemCreateRequest;
import com.example.cms.item.controller.request.ItemSearchRequest;
import com.example.cms.item.controller.request.ItemUpdateRequest;
import com.example.cms.item.controller.request.PageRequest;
import com.example.cms.item.controller.response.ItemResponse;
import com.example.cms.item.domain.Item;
import com.example.cms.item.repository.ItemRepository;
import com.example.cms.utils.exception.CommonException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cms.utils.exception.ErrorCode.DUPLICATE_RESOURCE;


@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findAll(){
        return itemRepository.findAll().stream()
                .map(ItemResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findByName(String name) {
        return itemRepository.findAllByNameContaining(name)
                .stream().map(ItemResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(ItemCreateRequest itemCreateRequest){
        Item newItem = itemCreateRequest.toItem();
        //중복체크
        Boolean isExistItemAndStatus = itemRepository.existsByNameAndAndHotIce(newItem.getName(), newItem.getHotIce());

        if(isExistItemAndStatus){
            Item originItem = itemRepository.findByNameAndHotIce(newItem.getName(), newItem.getHotIce());
            validateDuplicate(originItem, newItem);
        }else {
            itemRepository.save(newItem);
        }
    }

    private void validateDuplicate(Item originItem, Item newItem){
        // 이름과 상태값이 같은 제품이 있을경우
        if(originItem != null && ( originItem.getCost().equals(newItem.getCost()))){
//            throw new IllegalStateException("중복되는 기존 메뉴가 있습니다. " + originItem.getName() + "("+ originItem.getHotIce() +") " +originItem.getCost());
            throw new CommonException(DUPLICATE_RESOURCE);
        }else if(originItem != null && ( originItem.getCost() != newItem.getCost())){
//            throw new IllegalStateException(newItem.getName() +"("+newItem.getHotIce()+") 는(운) 이미 "+originItem.getCost()+"원 으로 책정되어있습니다.");
            throw new CommonException(DUPLICATE_RESOURCE);
        }
    }

    @Transactional
    public void update(ItemUpdateRequest updateRequest){
        Item updateItem = updateRequest.toItem();

        //name, cost, h/i 가 모두 중복
        Boolean isDuplicated = itemRepository.existsByNameAndCostAndHotIce(updateItem.getName(), updateItem.getCost(), updateItem.getHotIce());
        if(isDuplicated) {
//            throw new IllegalStateException("중복되는 기존 메뉴가 있습니다. " + updateItem.getName() + "(" + updateItem.getHotIce() + ") " + updateItem.getCost() + "원");
            throw new CommonException(DUPLICATE_RESOURCE);
        }

        //name , h/i 중복
        Item originItem = itemRepository.findByNameAndHotIce(updateItem.getName(), updateItem.getHotIce());

        if (originItem != null && originItem.getCost() != updateItem.getCost()){
            //cost 만 다를경우 cost만 업뎃
            updateItem.update(updateItem.getItemId(), originItem.getName(), updateItem.getCost(), originItem.getHotIce());
            itemRepository.save(updateItem);
        }else if(originItem == null){
            //중복된것 없으면 그냥 업뎃
            updateItem.update(updateItem.getItemId(), updateItem.getName(), updateItem.getCost(), updateItem.getHotIce());
            itemRepository.save(updateItem);
        }
    }

    @Transactional
    public void delete(Long itemId){
        itemRepository.deleteByItemId(itemId);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> searchItems(ItemSearchRequest itemSearchRequest) {
        Item filter = itemSearchRequest.toItem();
        return itemRepository.searchItems(filter)
                .stream()
                .map(ItemResponse::of)
                .collect(Collectors.toList());
    }

    public PageImpl<ItemResponse> searchItemsPaging(ItemSearchRequest itemSearchRequest, PageRequest pageRequest) {
        Pageable pageable = pageRequest.of();
        PageImpl<Item> searched = itemRepository.searchItemsPaging(pageable, itemSearchRequest.toItem());
        PageImpl<ItemResponse> result = (PageImpl<ItemResponse>) searched.map(ItemResponse::of);
        return result;
    }
}
