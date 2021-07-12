package ru.fomin.filemanager.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fomin.filemanager.domain.FileData;
import ru.fomin.filemanager.entiries.FileMeta;
import ru.fomin.filemanager.repositories.FileMetaRepository;
import ru.fomin.filemanager.repositories.IFileSystemProvider;
import ru.fomin.filemanager.services.IFileStoreService;
import ru.fomin.filemanager.utils.HashHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileStoreService implements IFileStoreService {

    final IFileSystemProvider systemProvider;
    final FileMetaRepository fileMetaRepository;

    @Value("${store.zip}")
    String zipName;

    @Override
    public FileMeta storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException {
        UUID md5 = HashHelper.getMd5Hash(content);
        return fileMetaRepository.findByHash(md5)
                .orElse(createFile(md5, content, fileName, subFileType));
    }

    @Override
    public FileData getFiles(List<UUID> md5List) throws IOException {
        if (md5List.size() < 2) {
            return getFile(md5List.get(0));
        }
        return getZipFiles(md5List);
    }

    @Override
    public Collection<FileMeta> getMetaFiles(int subtype) {
        if (subtype == -1) {
            return fileMetaRepository.findAll();
        }
        return fileMetaRepository.findAllBySubType(subtype);
    }

    private FileMeta createFile(UUID md5, byte[] content, String fileName, int subFileType) throws IOException {
        FileMeta fileMeta = FileMeta.builder()
                .hash(md5)
                .fileName(fileName)
                .subType(subFileType)
                .size(content.length)
                .build();
        systemProvider.storeFile(content, md5, fileName);
        return fileMetaRepository.save(fileMeta);
    }

    private FileData getZipFiles(List<UUID> md5List) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            for (UUID md5 : md5List) {
                writeToZipOutputStream(md5, zipOutputStream);
            }
            zipOutputStream.finish();
            return FileData.builder()
                    .fileName(zipName)
                    .bytes(byteArrayOutputStream.toByteArray())
                    .build();
        }
    }

    private void writeToZipOutputStream(UUID md5, ZipOutputStream zipOutputStream) throws IOException {
        String filename = fileMetaRepository.checkFileExists(md5);
        byte[] bytes = getFile(md5, filename);
        ZipEntry entry = new ZipEntry(filename);
        entry.setSize(bytes.length);
        zipOutputStream.putNextEntry(entry);
        zipOutputStream.write(bytes);
    }

    private FileData getFile(UUID md5) throws IOException {
        String filename = fileMetaRepository.checkFileExists(md5);
        return FileData.builder()
                .fileName(filename)
                .bytes(getFile(md5, filename))
                .build();
    }

    private byte[] getFile(UUID md5, String filename) throws IOException {
        String ext = FilenameUtils.getExtension(filename);
        String fullFileName = md5.toString() + "." + ext;
        return systemProvider.getFile(fullFileName);
    }

}
