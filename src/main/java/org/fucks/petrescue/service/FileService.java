package org.fucks.petrescue.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.fucks.petrescue.entity.announce.Announce;
import org.fucks.petrescue.web.models.EncodedPhotoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * TODO: Necessário criar um dataSource separado, com um novo bd apenas para as imagens
 */
@Service
public class FileService {

    @Autowired
    @Qualifier("fileSystemTemplate")
    private GridFsTemplate fileSystemTemplate;

    @Autowired
    private GridFS gridFS;

    @Autowired
    private GridFsOperations fileSystemOperations;

    public List<DBObject> save(List<EncodedPhotoModel> images, Announce announce) {
        var base64Decoder = Base64.getDecoder();
        List<DBObject> response = new ArrayList<>();

        if (base64Decoder == null)
            throw new IllegalArgumentException("Não foi possível encontrar o decodificador Base 64.");

        for (var image : images) {

            var decodedImage = Base64.getDecoder().decode(image.getData());
            var imageUUID = UUID.randomUUID().toString();

            var metaData = new BasicDBObject();

            metaData.put("announceId", announce.getId());
            metaData.put("mimetype", image.getMimetype());
            metaData.put("filename", image.getFilename());
            metaData.put("uuid", imageUUID);
            metaData.put("path", mountImagePath(announce.getId(), Announce.class.getSimpleName(), imageUUID, image.getMimetype()));

            this.save(decodedImage, metaData);

            response.add(metaData);
        }

        return response;
    }

    public GridFSDBFile findByUuid(String uuid) throws FileNotFoundException {
        var file = this.fileSystemTemplate.findOne(getUuidQuery(uuid));

        if(file != null)
            return gridFS.find(file.getObjectId());

        throw new FileNotFoundException();
    }


    private ObjectId save(byte[] data, DBObject metadata) {

        var fileStream = new ByteArrayInputStream(data);

        ObjectId imageFileId = fileSystemTemplate.store(
                fileStream,
                metadata.get("filename").toString(),
                metadata.get("mimetype").toString(),
                metadata
        );

        return imageFileId;
    }

    private String mountImagePath(String entityOwnerId, String entityType, String imageUUID, String mimeType) {
        var extension = mimeType.split("/")[1];
        return String.format("/%s/%s/image/%s.%s", entityType.toLowerCase(), entityOwnerId, imageUUID, extension);
    }

    private Query getUuidQuery(String uuid) {
        return new Query(Criteria.where("metadata.uuid").is(uuid));
    }
}
