package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter @ToString
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

}
