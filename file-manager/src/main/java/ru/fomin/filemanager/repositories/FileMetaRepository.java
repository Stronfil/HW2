package ru.fomin.filemanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fomin.filemanager.entiries.FileMeta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileMetaRepository extends JpaRepository<FileMeta, UUID> {

    @Query("SELECT fileName FROM FileMeta WHERE hash = ?1")
    String checkFileExists(UUID fileHash);

    Optional<FileMeta> findByHash(UUID hash);

    List<FileMeta> findAllBySubType(int subType);

}
