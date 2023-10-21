package com.example.cms.item.infrastructure;

import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.utils.entity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseDateTimeEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "hot_ice", nullable = false)
    @Enumerated(EnumType.STRING)
    private EItemStatus hotIce;

    @Builder
    public ItemEntity(Long id, String name, Integer cost, EItemStatus hotIce) {
        this.itemId = id;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }

    public static ItemEntity from(Item item){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.itemId =item.getItemId();
        itemEntity.name = item.getName();
        itemEntity.cost = item.getCost();
        itemEntity.hotIce = item.getHotIce();
        return itemEntity;
    }
    public void update(Long id, String name, Integer cost, EItemStatus hotIce) {
        this.itemId = id;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }

    public Item toModel(){
        return Item.builder()
                .itemId(itemId)
                .name(name)
                .cost(cost)
                .hotIce(hotIce)
                .build();
    }
}
