package ru.fomin.filemanager.services;

import ru.fomin.filemanager.domain.FileData;
import ru.fomin.filemanager.entiries.FileMeta;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IFileStoreService {

    FileMeta storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException;

    FileData getFiles(List<UUID> md5List) throws IOException;

    Collection<FileMeta> getMetaFiles(int subtype);

}
