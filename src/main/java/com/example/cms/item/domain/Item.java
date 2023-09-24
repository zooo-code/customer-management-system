package com.example.cms.item.domain;

import com.example.cms.utils.entity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseDateTimeEntity {

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
    private ItemStatus hotIce;

    @Builder
    public Item(Long id,String name, Integer cost, ItemStatus hotIce) {
        this.itemId = id;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }

    public void update(Long id, String name, Integer cost, ItemStatus hotIce) {
        this.itemId = id;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }
}
