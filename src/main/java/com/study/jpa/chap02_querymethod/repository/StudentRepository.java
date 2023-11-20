package com.study.jpa.chap02_querymethod.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByName(String name);
    List<Student> findByCityAndMajor(String city, String major);
    List<Student> findByMajorContaining(String major);

    // 네이티브 쿼리 사용
    @Query(value = "SELECT * FROM tbl_student WHERE stu_name = :nm", nativeQuery = true)
    Student findNameWithSQL(@Param("nm") String name);

    // JPQL
    // SELECT 별칭 FROM 엔터티클래스명 AS 별칭
    // WHERE 별칭.필드명 = ?
    // SELECT st FROM student AS st
    // WHERE st.name = ?

    // native-sql
    // SELECT 컬럼명 FROM 테이블
    // WHERE 컬럼 = ?

    // 도시 이름으로 학생 조회
    // @Query(value = "SELECT * FROM tbl_student WHERE city = ?1", nativeQuery = true)
    @Query("SELECT s FROM Student s WHERE s.city = ?1")
    List<Student> getByCityWithJPQL(String city);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:nm%")
    List<Student> searchByNameWithJPQL(@Param("nm") String name);

    // JPQL로 수정 삭제 쿼리 쓰기
    @Modifying // 조회가 아닐 경우 무조건 붙여야 함.
    @Query("DELETE FROM Student s WHERE s.name = :nm")
    void deleteByNameWithJPQL(@Param("nm") String name);

}
