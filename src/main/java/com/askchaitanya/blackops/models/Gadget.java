package com.askchaitanya.blackops.models;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Gadget {
    private UUID id;
    private String name;
    private String brand;
    private Long price;
    private String color;
}
