package com.grungapda.backend.file.command.domain.repository;


import com.grungapda.backend.file.command.domain.aggregate.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAll();
    List<File> findFileByFileNo(Long fileNo);
    List<File> findFileBySongArtist(String songArtist);
}
