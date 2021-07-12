package ru.fomin.filemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.fomin.filemanager.domain.FileData;
import ru.fomin.filemanager.entiries.FileMeta;
import ru.fomin.filemanager.services.IFileStoreService;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
public class FileController {

    @Autowired
    IFileStoreService fileStoreService;

    @PostMapping("/storefile")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "subtype", required = false, defaultValue = "1") int subType
    ) throws IOException, NoSuchAlgorithmException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is Empty");
        }

        FileMeta fileMeta = fileStoreService.storeFile(file.getBytes(), file.getOriginalFilename(), subType);
        return ResponseEntity.ok(fileMeta);
    }

    @GetMapping("/getfile")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam(value = "hash") @NotEmpty List<UUID> hashList) throws IOException {
        FileData fileData = fileStoreService.getFiles(hashList);
        return ResponseEntity.ok()
                .header("content-disposition", String.format("attachment; filename = \"%s\"", fileData.getFileName()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(fileData.getBytes()));
    }

    @GetMapping("/getfiles")
    public ResponseEntity<?> getFiles(@RequestParam(value = "subtype", required = false, defaultValue = "-1") int subtype) {
        return ResponseEntity.ok(fileStoreService.getMetaFiles(subtype));
    }

}
