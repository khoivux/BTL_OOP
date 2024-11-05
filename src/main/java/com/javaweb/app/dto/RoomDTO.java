package com.javaweb.app.dto;


public class RoomDTO {
    private Long id;

    private String type;

    private Long numbers;

    public RoomDTO() {
    }

    public RoomDTO(Long id, String type, Long numbers) {
        this.id = id;
        this.type = type;
        this.numbers = numbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }
}
