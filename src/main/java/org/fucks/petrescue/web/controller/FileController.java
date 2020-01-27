package org.fucks.petrescue.web.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.gridfs.GridFSDBFile;
import org.fucks.petrescue.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("{entityInfo}/{entityId}/image/{imageId}")
    public HttpEntity getFile(@PathVariable String entityInfo, @PathVariable String entityId, @PathVariable String imageId) throws IOException {

        try {
            GridFSDBFile file = this.fileService.findByUuid(imageId);

            ByteArrayOutputStream fileOutput = new ByteArrayOutputStream();
            file.writeTo(fileOutput);

            return ResponseEntity
                    .ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(fileOutput.toByteArray());
                    .body(new Position(Arrays.asList(0.0,1.0)));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
