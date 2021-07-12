package ru.fomin.filemanager.domain;


import lombok.Builder;
import lombok.Data;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@Builder
public class FileData {

    byte[] bytes;
    String fileName;

    public String getFileName() {
        return Arrays.stream(fileName.split("\\s"))
                .map(this::encoding)
                .collect(Collectors.joining("%20"));
    }

    private String encoding(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

}
