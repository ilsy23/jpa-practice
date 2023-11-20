package com.study.jpa.chap01_basic.repository;

import com.study.jpa.chap01_basic.entity.Product;
import com.study.jpa.chap01_basic.entity.Product.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.study.jpa.chap01_basic.entity.Product.Category.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional // 테스트 완료 후 롤백.
    @Rollback(false)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach // 테스트 메서드 전에 실행되는 메서드
    void insertDummyData() {
        // given
        Product p1 = Product.builder()
                .name("아이폰")
                .category(ELECTRONIC)
                .price(1000000)
                .build();

        Product p2 = Product.builder()
                .name("탕수육")
                .category(FOOD)
                .price(20000)
                .build();

        Product p3 = Product.builder()
                .name("구두")
                .category(FASHION)
                .price(300000)
                .build();

        Product p4 = Product.builder()
                .name("쓰레기")
                .category(FOOD)
                .build();

        //when
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);

        //then
    }

    @Test
    @DisplayName("5번째 상품을 데이터베이스에 저장해야 한다.")
    void testSave() {
        //given
        Product p5 = Product.builder()
                .name("정장")
                .category(FASHION)
                .price(100000)
                .build();

        //when
        Product saved = productRepository.save(p5);

        //then
        assertNotNull(saved);
    }

    @Test
    @DisplayName("id가 2번인 데이터를 삭제해야 한다.")
    void testRemove() {
        //given
        long id = 2L;
        //when
        productRepository.deleteById(id);
        //then
    }

    @Test
    @DisplayName("상품 전체조회를 하면 상품의 개수가 4개여야 한다.")
    void testFindAll () {
        //given

        //when
        List<Product> products = productRepository.findAll();

        //then
        products.forEach(System.out::println);

        assertEquals(4, products.size());
    }

    @Test
    @DisplayName("3번 상품을 조회하면 상품명이 구두일 것이다.")
    void testFindOne() {
        //given
        long id = 3L;
        //when
        Optional<Product> product = productRepository.findById(id);
        //then
        product.ifPresent(p -> {
            assertEquals("구두", p.getName());
        });
        
        Product foundProduct = product.get();
        assertNotNull(foundProduct);

        System.out.println("foundProduct = " + foundProduct);

    }

    @Test
    @DisplayName("2번 상품의 이름과 가격을 변경해야 한다.")
    void testModify () {
        //given
        long id = 2L;
        String newName = "짜장면";
        int newPrice = 6000;

        //when
        // jpa에서는 update는 따로 메서드로 제공하지 않고,
        // 조회한 후 setter로 변경하면 자동으로 update문이 나갑니다.
        // 변경 후 save를 호출하면 update가 진행됩니다.
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(p -> {
            p.setName(newName);
            p.setPrice(newPrice);

            productRepository.save(p);
        });

        //then
        assertTrue(product.isPresent());

        Product p = product.get();
        assertEquals("짜장면", p.getName());
    }

}