package com.example.cms.item.service;

import com.example.cms.item.controller.request.ItemSearchRequest;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.item.domain.ItemCreate;
import com.example.cms.item.domain.ItemUpdate;
import com.example.cms.mock.FakeItemRepository;
import com.example.cms.mock.TestClockHolder;
import com.example.cms.utils.exception.CommonException;
import com.example.cms.utils.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemServiceTest {

    private ItemServiceImpl itemService;

    @BeforeEach
    void init(){
        FakeItemRepository fakeItemRepository = new FakeItemRepository();
        this.itemService = ItemServiceImpl
                .builder()
                .itemRepository(fakeItemRepository)
                .clockHolder(new TestClockHolder(1L))
                .build();

        fakeItemRepository.save(Item.builder()
                        .name("test1")
                        .cost(10000)
                        .hotIce(EItemStatus.ICED)
                .build());
        fakeItemRepository.save(Item.builder()
                .name("test231")
                .cost(100001)
                .hotIce(EItemStatus.HOT)
                .build());
    }

    @Test
    void ItemCreate_로_아이템을_생성할_수_있다_이름도_조회_가능하다(){
        //given
        ItemCreate test = ItemCreate.builder()
                .name("test11")
                .cost(12234)
                .hotIce(EItemStatus.ICED)
                .build();
        //when
        itemService.create(test);
        Item item = itemService.findByName("test11");

        //then
        assertThat(test.getName()).isEqualTo(item.getName());
        assertThat(test.getCost()).isEqualTo(item.getCost());
        assertThat(test.getHotIce()).isEqualTo(item.getHotIce());
    }
    @Test
    void ItemCreate_로_중복_생성은_불가_하다(){
        //given
        ItemCreate test = ItemCreate.builder()
                .name("test1")
                .cost(10000)
                .hotIce(EItemStatus.ICED)
                .build();
        //when
        //then
        assertThatThrownBy(()->itemService.create(test)).isInstanceOf(CommonException.class);

    }
    @Test
    void 모든_아이템을_조회할_수_있다(){
        //given
        List<Item> all = itemService.findAll();
        //when
        //then
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void 상품이름으로_찾을_수_있다(){
        //given
        Item test11 = itemService.findByName("test1");
        //when

        //then
        assertThat(test11.getName()).isEqualTo("test1");
    }
    @Test
    void ItemUpdate_로_아이템을_수정할_수_있다(){
        //given

        ItemUpdate test = ItemUpdate.builder()
                .name("test1")
                .cost(10)
                .hotIce(EItemStatus.ICED)
                .build();
        //when
        itemService.update(test);
        Item item = itemService.findByName("test1");
        //then
        assertThat(test.getName()).isEqualTo(item.getName());
        assertThat(test.getCost()).isEqualTo(item.getCost());
        assertThat(test.getHotIce()).isEqualTo(item.getHotIce());
    }

    @Test
    void ItemDelete_로_아이템을_삭제할_수_있다(){
        //given

        ItemCreate test = ItemCreate.builder()
                .name("test11")
                .cost(12234)
                .hotIce(EItemStatus.ICED)
                .build();
        //when
        itemService.create(test);
        Item delItem = itemService
                .findByName("test11");
        itemService.delete(delItem.getItemId());

        //then
        assertThatThrownBy(() ->itemService
                .findByName("test11"))
                .isInstanceOf(CommonException.class);

    }
    @Test
    void searchItems_로_아이템을_검색할_수_있다(){
        //given

        ItemSearchRequest test = ItemSearchRequest.builder()
                .name("test")
                .build();
        //when

        List<Item> items = itemService
                .searchItems(test);


        //then
        assertThat(items).size().isEqualTo(2);

    }
}