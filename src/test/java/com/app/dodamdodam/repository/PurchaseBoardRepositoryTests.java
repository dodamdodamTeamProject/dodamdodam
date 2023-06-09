package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.entity.purchase.PurchaseReview;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.file.purchase.PurchaseFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.product.ProductRepository;
import com.app.dodamdodam.repository.purchase.PurchaseRepository;
import com.app.dodamdodam.repository.reply.purchaseReview.PurchaseReviewRepository;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.MemberType;
import com.app.dodamdodam.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class
PurchaseBoardRepositoryTests {
    @Autowired
    private PurchaseBoardRepository purchaseBoardRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseFileRepository purchaseFileRepository;
    @Autowired
    private PurchaseReviewRepository purchaseReviewRepository;
    /*
        @Test
        public void saveTest(){
            Address address = new Address("test-address", "test-detail");
            Member member = new Member( 1000L, "test1234", "1234", "구매테스트", "test1234@gmail.com", "01012345678", address, MemberStatus.NORMAL, MemberType.GENERAL, Role.MEMBER);

            memberRepository.save(member);

            for (int i = 0; i < 10; i++) {


                PurchaseBoard purchaseBoard = new PurchaseBoard("판매 게시글" + i, "판매 게시글 내용" + i);
                purchaseBoard.setMember(member);

                Product product = new Product("테스트상품명" + i, 10000 * i, Long.valueOf(100 * i), purchaseBoard);

                productRepository.save(product);

                for (int j = 0; j < 5; j++) {
                    PurchaseFile purchaseFile = new PurchaseFile("test" + (i+1) + ".png", UUID.randomUUID().toString(), "test" + (i+1), 1024L, purchaseBoard);
                    purchaseFileRepository.save(purchaseFile);
                }

                purchaseBoardRepository.save(purchaseBoard);
            }

        }*/
/*    @Test
    public void productSaveTest(){
        PurchaseBoard purchaseBoard = purchaseBoardRepository.findById(23L).get();
        Product product = Product.builder()
                .productName("test상품")
                .productCount(10L)
                .productPrice(20)
                .build();
        purchaseBoard.setProduct(product);

        productRepository.save(product);
    }*/

    @Test
    public void purchaseFileSaveTest(){
        PurchaseBoard purchaseBoard = purchaseBoardRepository.findById(23L).get();
        Member member = memberRepository.findById(16L).get();
        List<PurchaseFile> purchaseFiles = new ArrayList<>();
        Product product = Product.builder()
                .productName("test상품")
                .productCount(10L)
                .productPrice(20)
                .build();

        for (int i = 0; i < 5; i++) {
            PurchaseFile purchaseFile = PurchaseFile.builder()
                    .fileOriginalName("test" + i)
                    .filePath("testPath")
                    .fileUuid(UUID.randomUUID().toString())
//                .purchaseBoard(purchaseBoard)
                    .build();

            purchaseFiles.add(purchaseFile);
        }


        purchaseBoard.setProduct(product);
        purchaseBoard.setPurchaseFiles(purchaseFiles);
        purchaseBoardRepository.save(purchaseBoard);
    }

    @Test
    public void findAllWithSearch_QueryDSLTest() {
//        무한스크롤 테스트

        PurchaseBoardSearch purchaseBoardSearch = new PurchaseBoardSearch();
//        purchaseBoardSearch.setBoardTitle("테스트");

        Slice<PurchaseBoard> result = purchaseBoardRepository.findAllWithSearch_QueryDSL(purchaseBoardSearch, PageRequest.of(1, 5));

        result.stream().forEach(purchaseBoard -> log.info(purchaseBoard.toString()));
    }
}

//    public void findAllTest(){
////        purchaseBoardRepository.findAll().stream().map(PurchaseBoard::toString).forEach(log::info);
//        Pageable pageable = Pageable.ofSize(1);
//        purchaseBoardRepository.findAllPurchaseBoard_QueryDSL(pageable);
//package com.app.dodamdodam.repository;
//
//import com.app.dodamdodam.entity.embeddable.Address;
//import com.app.dodamdodam.entity.member.Member;
//import com.app.dodamdodam.entity.purchase.Product;
//import com.app.dodamdodam.entity.purchase.PurchaseBoard;
//import com.app.dodamdodam.entity.purchase.PurchaseFile;
//import com.app.dodamdodam.entity.purchase.PurchaseReview;
//import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
//import com.app.dodamdodam.repository.file.purchase.PurchaseFileRepository;
//import com.app.dodamdodam.repository.member.MemberRepository;
//import com.app.dodamdodam.repository.product.ProductRepository;
//import com.app.dodamdodam.repository.purchase.PurchaseRepository;
//import com.app.dodamdodam.repository.reply.purchaseReview.PurchaseReviewRepository;
//import com.app.dodamdodam.search.PurchaseBoardSearch;
//import com.app.dodamdodam.type.MemberStatus;
//import com.app.dodamdodam.type.MemberType;
//import com.app.dodamdodam.type.Role;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.UUID;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class
//PurchaseBoardRepositoryTests {
//    @Autowired
//    private PurchaseBoardRepository purchaseBoardRepository;
//    @Autowired
//    private PurchaseRepository purchaseRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private PurchaseFileRepository purchaseFileRepository;
//    @Autowired
//    private PurchaseReviewRepository purchaseReviewRepository;
//
//    @Test
//    public void saveTest(){
//        Address address = new Address("test-address", "test-detail");
//        Member member = new Member( 1000L, "test1234", "1234", "구매테스트", "test1234@gmail.com", "01012345678", address, MemberStatus.NORMAL, MemberType.GENERAL, Role.MEMBER);
//
//        memberRepository.save(member);
//
//        for (int i = 0; i < 10; i++) {
//
//
//            PurchaseBoard purchaseBoard = new PurchaseBoard("판매 게시글" + i, "판매 게시글 내용" + i);
//            purchaseBoard.setMember(member);
//
//            Product product = new Product("테스트상품명" + i, 10000 * i, Long.valueOf(100 * i), purchaseBoard);
//
//            productRepository.save(product);
//
//            for (int j = 0; j < 5; j++) {
//                PurchaseFile purchaseFile = new PurchaseFile("test" + (i+1) + ".png", UUID.randomUUID().toString(), "test" + (i+1), 1024L, purchaseBoard);
//                purchaseFileRepository.save(purchaseFile);
//            }
//
//            purchaseBoardRepository.save(purchaseBoard);
//        }
//
//    }
//
//    @Test
//    public void findAllWithSearch_QueryDSLTest(){
////        무한스크롤 테스트
//
//        PurchaseBoardSearch purchaseBoardSearch = new PurchaseBoardSearch();
////        purchaseBoardSearch.setBoardTitle("테스트");
//
//        Slice<PurchaseBoard> result = purchaseBoardRepository.findAllWithSearch_QueryDSL(purchaseBoardSearch, PageRequest.of(1, 5));
//
//        result.stream().forEach(purchaseBoard -> log.info(purchaseBoard.toString()));
//    }
//
////    public void findAllTest(){
//////        purchaseBoardRepository.findAll().stream().map(PurchaseBoard::toString).forEach(log::info);
////        Pageable pageable = Pageable.ofSize(1);
////        purchaseBoardRepository.findAllPurchaseBoard_QueryDSL(pageable);
////    }
//
//    @Test
//    public void findByIdTest(){
//        purchaseBoardRepository.findById(3L).ifPresent(purchaseBoard -> log.info(purchaseBoard.toString()));
//    }
//
//    @Test
//    public void deleteTest(){
//        purchaseBoardRepository.findById(3L).ifPresent(purchaseBoardRepository::delete);
//    }
//
//    @Test
//    public void findPurchaseBoardById_QueryDSLTest(){
//        purchaseBoardRepository.findPurchaseBoardById_QueryDSL(80L)
//                .ifPresent(purchaseBoard -> log.info(purchaseBoard.toString()));
//    }
//
//    @Test
//    public void reviewSaveTest(){
//
//        PurchaseBoard purchaseBoard = purchaseBoardRepository.findById(80L).get();
//        Member member = memberRepository.findById(73L).get();
//
//        for (int i = 0; i < 20; i++) {
//            PurchaseReview purchaseReview = new PurchaseReview("test" + (i+1), 5, purchaseBoard, member);
//
//            purchaseReviewRepository.save(purchaseReview);
//        }
//
//
//    }
//
//    @Test
//    public void findAllPurchaseReviewByBoardIdTest(){
//        purchaseReviewRepository.findAllPurchaseReviewByBoardId(80L, PageRequest.of(1, 5))
//                .forEach(purchaseReview -> log.info(purchaseReview.toString()));
//    }
//
//
//}