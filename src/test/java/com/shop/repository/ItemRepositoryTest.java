package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j //이거 넣으면 따로 LOGGER xml 안만들어도 됨
class ItemRepositoryTest {
//    의존성 주입 (생성자 만들때는 autowire 안써도 됨), 단순히 선언만 한것!
    @Autowired
    ItemRepository itemRepository;
//    실제 값을 저장하는거 만듦
    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
//        item 객체 생성하고 거기에 값을 넣음
        for(int i = 1; i<=10; i++){
            Item item = new Item();
            item.setItemNm("아메리카노" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("그냥 노멀 커피 입니다."+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }
    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemNmTest(){
//        테스트하려면 데이터 생성 해야하니까 this.로 메소드 불러서 생성
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("아메리카노5");
        for(Item item : itemList){
            log.warn(item.toString());
        }
    }
    @Test
    @DisplayName("상품명, 상품상세설명")
    public void findByItemNmOrItemDetail(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트1","그냥 노멀 커피 입니다.1");
        for(Item item : itemList){
            log.warn(item.toString());
        }
    }
    @Test
    @DisplayName("입력 받은 가격보다 싼 제품 찾기")
    public void findByPriceLessThanTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList){
            log.warn(item.toString());
        }
    }
}