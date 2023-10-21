package com.example.cms.item.service;

import com.example.cms.item.controller.request.ItemCreateRequest;
import com.example.cms.item.controller.request.ItemSearchRequest;
import com.example.cms.item.controller.request.ItemUpdateRequest;
import com.example.cms.item.controller.request.PageRequest;
import com.example.cms.item.controller.response.ItemResponse;
import com.example.cms.item.infrastructure.ItemEntity;
import com.example.cms.item.infrastructure.ItemRepositoryJpa;
import com.example.cms.utils.exception.CommonException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cms.utils.exception.ErrorCode.DUPLICATE_RESOURCE;


@Service
public class ItemServiceImpl {

    private final ItemRepositoryJpa itemRepositoryJpa;

    public ItemServiceImpl(ItemRepositoryJpa itemRepositoryJpa) {
        this.itemRepositoryJpa = itemRepositoryJpa;
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findAll(){
        return itemRepositoryJpa.findAll().stream()
                .map(ItemResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findByName(String name) {
        return itemRepositoryJpa.findAllByNameContaining(name)
                .stream().map(ItemResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(ItemCreateRequest itemCreateRequest){
        ItemEntity newItemEntity = itemCreateRequest.toItem();
        //중복체크
        Boolean isExistItemAndStatus = itemRepositoryJpa.existsByNameAndAndHotIce(newItemEntity.getName(), newItemEntity.getHotIce());

        if(isExistItemAndStatus){
            ItemEntity originItemEntity = itemRepositoryJpa.findByNameAndHotIce(newItemEntity.getName(), newItemEntity.getHotIce());
            validateDuplicate(originItemEntity, newItemEntity);
        }else {
            itemRepositoryJpa.save(newItemEntity);
        }
    }

    private void validateDuplicate(ItemEntity originItemEntity, ItemEntity newItemEntity){
        // 이름과 상태값이 같은 제품이 있을경우
        if(originItemEntity != null && ( originItemEntity.getCost().equals(newItemEntity.getCost()))){
//            throw new IllegalStateException("중복되는 기존 메뉴가 있습니다. " + originItemEntity.getName() + "("+ originItemEntity.getHotIce() +") " +originItemEntity.getCost());
            throw new CommonException(DUPLICATE_RESOURCE);
        }else if(originItemEntity != null && ( originItemEntity.getCost() != newItemEntity.getCost())){
//            throw new IllegalStateException(newItemEntity.getName() +"("+newItemEntity.getHotIce()+") 는(운) 이미 "+originItemEntity.getCost()+"원 으로 책정되어있습니다.");
            throw new CommonException(DUPLICATE_RESOURCE);
        }
    }

    @Transactional
    public void update(ItemUpdateRequest updateRequest){
        ItemEntity updateItemEntity = updateRequest.toItem();

        //name, cost, h/i 가 모두 중복
        Boolean isDuplicated = itemRepositoryJpa.existsByNameAndCostAndHotIce(updateItemEntity.getName(), updateItemEntity.getCost(), updateItemEntity.getHotIce());
        if(isDuplicated) {
//            throw new IllegalStateException("중복되는 기존 메뉴가 있습니다. " + updateItemEntity.getName() + "(" + updateItemEntity.getHotIce() + ") " + updateItemEntity.getCost() + "원");
            throw new CommonException(DUPLICATE_RESOURCE);
        }

        //name , h/i 중복
        ItemEntity originItemEntity = itemRepositoryJpa.findByNameAndHotIce(updateItemEntity.getName(), updateItemEntity.getHotIce());

        if (originItemEntity != null && originItemEntity.getCost() != updateItemEntity.getCost()){
            //cost 만 다를경우 cost만 업뎃
            updateItemEntity.update(updateItemEntity.getItemId(), originItemEntity.getName(), updateItemEntity.getCost(), originItemEntity.getHotIce());
            itemRepositoryJpa.save(updateItemEntity);
        }else if(originItemEntity == null){
            //중복된것 없으면 그냥 업뎃
            updateItemEntity.update(updateItemEntity.getItemId(), updateItemEntity.getName(), updateItemEntity.getCost(), updateItemEntity.getHotIce());
            itemRepositoryJpa.save(updateItemEntity);
        }
    }

    @Transactional
    public void delete(Long itemId){
        itemRepositoryJpa.deleteByItemId(itemId);
    }

//    @Transactional(readOnly = true)
//    public List<ItemResponse> searchItems(ItemSearchRequest itemSearchRequest) {
//        ItemEntity filter = itemSearchRequest.toItem();
//        return itemRepositoryJpa.searchItems(filter)
//                .stream()
//                .map(ItemResponse::of)
//                .collect(Collectors.toList());
//    }
//
//    public PageImpl<ItemResponse> searchItemsPaging(ItemSearchRequest itemSearchRequest, PageRequest pageRequest) {
//        Pageable pageable = pageRequest.of();
//        PageImpl<ItemEntity> searched = itemRepositoryJpa.searchItemsPaging(pageable, itemSearchRequest.toItem());
//        PageImpl<ItemResponse> result = (PageImpl<ItemResponse>) searched.map(ItemResponse::of);
//        return result;
//    }
}
