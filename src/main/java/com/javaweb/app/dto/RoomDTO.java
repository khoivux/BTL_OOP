package com.javaweb.app.dto;


import org.springframework.web.multipart.MultipartFile;

public class RoomDTO {
    private String type;
    private Long numbers;
    private MultipartFile image;
    private String description;
    private String imgURL;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public RoomDTO() {
    }

    public RoomDTO(String type, Long numbers, String description) {
        this.type = type;
        this.numbers = numbers;
        this.description = description;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        if (numbers != null && !numbers.isEmpty()) {
            this.numbers = Long.parseLong(numbers);
        } else {
            this.numbers = 0L; // hoặc giá trị mặc định khác
        }
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
