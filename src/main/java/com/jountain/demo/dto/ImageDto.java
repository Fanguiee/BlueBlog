package com.jountain.demo.dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long imageId;
    private String downloadUrl;
    private String imageName;
}
