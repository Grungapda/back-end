package com.grungapda.backend.file.command.application.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllFileRequest {

    public List<FileDTO> files;

}
