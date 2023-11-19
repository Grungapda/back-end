package com.grungapda.backend.file.command.domain.repository;

import com.grungapda.backend.file.command.domain.aggregate.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAll();

    List<Participant> findParticipantByFile_FileNo(Long fileNo);
}
