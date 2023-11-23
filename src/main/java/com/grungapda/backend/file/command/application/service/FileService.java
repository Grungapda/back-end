package com.grungapda.backend.file.command.application.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.grungapda.backend.file.command.application.DTO.CreateFile;
import com.grungapda.backend.file.command.application.DTO.FileDTO;
import com.grungapda.backend.file.command.application.DTO.ParticipantDTO;
import com.grungapda.backend.file.command.domain.aggregate.entity.File;
import com.grungapda.backend.file.command.domain.aggregate.entity.Participant;
import com.grungapda.backend.file.command.domain.repository.FileRepository;
import com.grungapda.backend.file.command.domain.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    private final ParticipantRepository participantRepository;

    @Transactional
    public void createFile(CreateFile createFile) throws Exception{
        try {

            File file = File.builder()
                    .songTitle(createFile.getSongTitle())
                    .songArtist(createFile.getSongArtist())
                    .needSession(createFile.getNeedSession())
                    .imageFileUrl(createFile.getImageFileUrl())
                    .musicFileUrl(createFile.getMusicFileUrl())
                    .midFileUrl(createFile.getMidFileUrl())
                    .build();

            File newFile = fileRepository.save(file);

            for (int i = 0; i < createFile.getParticipantList().size(); i++){
                Participant participant = Participant.builder()
                        .participantNo(createFile.getParticipantList().get(i).getParticipantNo())
                        .userNo(createFile.getParticipantList().get(i).getUserNo())
                        .sessionType(createFile.getParticipantList().get(i).getSessionType())
                        .file(newFile)
                        .build();
                participantRepository.save(participant);
            }


        }catch (Exception e){
            throw new Exception("잘못된 요청입니다.");
        }
    }

    public List<ParticipantDTO> findParticipantByFileNo(Long fileNo){
        List<Participant> participantList = new ArrayList<>();
        participantList = participantRepository.findParticipantByFile_FileNo(fileNo);
        List<ParticipantDTO> participantDTOList = new ArrayList<>();
        for (int i = 0; i < participantList.size(); i++){
            ParticipantDTO participantDTO = new ParticipantDTO(
                    participantList.get(i).getParticipantNo(),
                    participantList.get(i).getSessionType()
            );
            participantDTOList.add(participantDTO);
        }
        return participantDTOList;
    }

    public List<FileDTO> findAllfiles(){
        List<File> files = fileRepository.findAll();
        List<FileDTO> fileDTOList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++){
            FileDTO fileDTOS = new FileDTO(
                    files.get(i).getFileNo(),
                    files.get(i).getSongTitle(),
                    files.get(i).getSongArtist(),
                    files.get(i).getNeedSession(),
                    files.get(i).getImageFileUrl(),
                    files.get(i).getMusicFileUrl(),
                    files.get(i).getMidFileUrl()
            );
            fileDTOList.add(fileDTOS);

        }
        return fileDTOList;
    }



}
