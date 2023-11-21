package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@ToString(exclude = {"employees"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private long id;

    @Column(name = "dept_name", nullable = false)
    private String name;

    // 양방향 매핑에서는 상대방 엔터티의 갱신에 관여할 수 없다.
    // 단순히 읽기 전용(조회)으로만 사용해야 한다.
    // mappedBy에는 상대방 엔터티의 조인되는 필드명을 작성.
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>(); // 초기화 필요(NullPointerException 방지)

}
