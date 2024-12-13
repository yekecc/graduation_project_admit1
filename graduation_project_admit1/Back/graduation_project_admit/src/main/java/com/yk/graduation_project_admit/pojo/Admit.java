package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_admit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long admitId;
    @Column(name = "userNumber", nullable = false)
    private String userNumber;
    @Column(name = "password", nullable = false)
    private String password;
}
