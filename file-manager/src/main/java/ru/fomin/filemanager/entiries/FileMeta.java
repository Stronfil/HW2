package ru.fomin.filemanager.entiries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "file_meta")
@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class FileMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "hash", unique = true, nullable = false)
    private UUID hash;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "sub_type")
    private int subType;

    @CreationTimestamp
    @Column(name = "time_creation")
    private LocalDateTime createAt;

    @Column(name = "file_size")
    private int size; //bytes

}
