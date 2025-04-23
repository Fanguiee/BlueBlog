package com.jountain.demo.service.image;

import com.jountain.demo.dto.ImageDto;
import com.jountain.demo.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService  {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);

}
