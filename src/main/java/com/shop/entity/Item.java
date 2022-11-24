package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Columns;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품코드
    @Column(nullable = false, length = 50)
    private String itemNm; // 상품명
    @Column(name = "price", nullable = false)
    private int price; //가격
    @Column(nullable = false)
    private int stockNumber; //재고수량
    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품에 대한 상세 설명
//    enumtype 순서를 말하는거...아마..?
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품의 판매 상태
    private LocalDateTime regTime; //상품 등록 시간
    private LocalDateTime updateTime; // 상품 수정 시간

}
