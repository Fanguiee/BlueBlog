package com.jountain.demo.dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private String downloadUrl;
    private String fileName;
}
