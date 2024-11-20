//package com.yk.graduation_project_admit.repository;
//
//import com.yk.graduation_project_admit.pojo.Borrow;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//public interface BorrowRepository extends CrudRepository<Borrow, Long> {
//    /**
//     *
//     * @param openid
//     * @param RoomID
//     * @param status01
//     * @param status02
//     * @param status03
//     * @param status04
//     * @return Borrow
//     */
//    @Query("select b from Borrow b " +
//            "where b.openid = :openid " +
//            " and b.RoomID = :RoomID " +
//            " and b.status01 = :status01" +
//            " and b.status02 = :status02" +
//            " and b.status03 = :status03" +
//            " and b.status04 = :status04")
//    Borrow findBorrowByOpenidAndRoomIDAndStatus01AndStatus02AndStatus03AndStatus04(@Param("openid") String openid,
//                                                                                   @Param("RoomID") Integer RoomID,
//                                                                                   @Param("status01") Integer status01,
//                                                                                   @Param("status02") Integer status02,
//                                                                                   @Param("status03") Integer status03,
//                                                                                   @Param("status04") Integer status04);
//}
