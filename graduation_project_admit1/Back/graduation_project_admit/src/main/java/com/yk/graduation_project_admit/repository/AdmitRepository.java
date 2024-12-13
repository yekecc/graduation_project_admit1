package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.Admit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmitRepository extends JpaRepository<Admit, Long> {
    Admit findAdmitByUserNumber(String userNumber);
}
