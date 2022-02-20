package com.hoangdang.BookStore.controllers;

import com.google.api.services.drive.model.File;
import com.hoangdang.BookStore.models.dto.FileDTO;
import com.hoangdang.BookStore.utils.CreateGoogleFile;
import com.hoangdang.BookStore.utils.FindFilesByName;
import com.hoangdang.BookStore.utils.GetSubFoldersByName;
import com.hoangdang.BookStore.utils.ShareGoogleFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/files")
public class FileController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file")MultipartFile file) {
        String fileName = new Timestamp(System.currentTimeMillis()).getTime() + "_" + file.getOriginalFilename();

        List<com.google.api.services.drive.model.File> folders = null;
        try {
            folders = GetSubFoldersByName.getGoogleSubFolderByName(null, "BookstoreDrive");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String parentFolderId = folders.get(0).getId();

        try {
            File googleFile = CreateGoogleFile.createGoogleFile(parentFolderId, "image/jpeg",
                    fileName, file.getBytes());
            ShareGoogleFile.createPublicPermission(googleFile.getId());
            String fileId = FindFilesByName.getGoogleFilesByName(fileName).get(0).getId();
            FileDTO path = new FileDTO(file.getOriginalFilename(), "https://drive.google.com/uc?export=view&id="+fileId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
