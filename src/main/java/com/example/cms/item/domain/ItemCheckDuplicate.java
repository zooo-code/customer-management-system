package com.example.cms.item.domain;

import com.example.cms.utils.exception.CommonException;

import static com.example.cms.utils.exception.ErrorCode.DUPLICATE_RESOURCE;

public class ItemCheckDuplicate {



    public void validate(Item originItem, Item newItem){
        // 이름과 상태값이 같은 제품이 있을경우
        if(originItem != null && ( originItem.getCost().equals(newItem.getCost()))){
            throw new CommonException(DUPLICATE_RESOURCE);
        }else if(originItem != null && (!originItem.getCost().equals(newItem.getCost()))){
            throw new CommonException(DUPLICATE_RESOURCE);
        }
    }
}
