package hoon.pepper.conti.persistence.repository;

import hoon.pepper.conti.controller.model.FileModel;

import java.util.List;

public interface FileRepositoryCustom {
    List<FileModel> getFiles(List<Long> fileIdList);
}
