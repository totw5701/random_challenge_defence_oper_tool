package com.rcd.random_challenge_defence_oper.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByIdIn(List<Long> ids);
}
