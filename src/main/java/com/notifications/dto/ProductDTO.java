package com.notifications.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//lombok da annotazioni utili tipo lodash
@Getter
@Setter
@ToString
public class ProductDTO {
    private String id;
    private String name;
    private int price;
    private int quantity;


    public int getTotalPrice(){
        return price * quantity;
    }
}
