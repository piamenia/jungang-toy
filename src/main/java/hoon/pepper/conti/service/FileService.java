package hoon.pepper.conti.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import hoon.pepper.common.client.awss3.AwsS3Handler;
import hoon.pepper.conti.controller.model.FileModel;
import hoon.pepper.conti.persistence.entity.FileEntity;
import hoon.pepper.conti.persistence.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final AwsS3Handler awsS3Handler;
    private final FileRepository fileRepository;

    @Transactional
    public List<FileModel> uploadFile(MultipartFile[] multipartFileArray) {
        List<FileModel> fileList = new ArrayList<>();
        for(MultipartFile multipartFile: multipartFileArray) {
            PutObjectResult putObjectResult = awsS3Handler.upload(multipartFile);

            String orgFileName = multipartFile.getOriginalFilename();
            String[] invalidName = {"\\\\", "/", ":", "[*]", "[?]", "\"", "<", ">", "[|]", "#", "%"}; // 파일명 특수문자
            if (StringUtils.isNotEmpty(orgFileName)) {
                for (int i = 0; i < invalidName.length; i++)
                    orgFileName = orgFileName.replaceAll(invalidName[i], "_"); // "-"로 치환
            }
            FileEntity fileEntity = fileRepository.save(
                FileEntity.builder()
                    .fileName(orgFileName)
                    .orgFileName(orgFileName)
                    .fileType(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.') + 1))
                    .fileSize(multipartFile.getSize())
                    .fileAccessKey(putObjectResult.getVersionId())
                    .build()
            );
            FileModel fileModel = FileModel.builder()
                .fileId(fileEntity.getFileId())
                .fileName(fileEntity.getFileName())
                .orgFileName(fileEntity.getOrgFileName())
                .fileType(fileEntity.getFileType())
                .fileSize(fileEntity.getFileSize())
                .downloadUrl(this.getDownloadUrl(fileEntity.getFileAccessKey()))
                .build();
            fileList.add(fileModel);
        }
        return fileList;
    }

    @Transactional(readOnly = true)
    public String getDownloadUrl(String fileAccessKey) {
        String result = awsS3Handler.makeUrl(fileAccessKey);
        result = result.replaceAll("amp;","");
        return result;
    }

    @Transactional
    public void removeFiles(List<Long> fileIdList) {
        List<FileModel> fileList = fileRepository.getFiles(fileIdList);
        List<String> accessKeyList = new ArrayList<>();
        for (FileModel file: fileList) {
            accessKeyList.add(file.getFileAccessKey());
        }
        fileRepository.deleteAllByFileIdIn(fileIdList);
        for(String key : accessKeyList) {
            awsS3Handler.delete(key);
        }
    }
    @Transactional
    public void multiRemove(List<FileModel> fileList) {
        List<String> accessKeyList = new ArrayList<>();
        List<Long> fileIdList = new ArrayList<>();
        for (FileModel file: fileList) {
            accessKeyList.add(file.getFileAccessKey());
            fileIdList.add(file.getFileId());
        }
        fileRepository.deleteAllByFileIdIn(fileIdList);
        for(String key : accessKeyList) {
            awsS3Handler.delete(key);
        }
    }
}
