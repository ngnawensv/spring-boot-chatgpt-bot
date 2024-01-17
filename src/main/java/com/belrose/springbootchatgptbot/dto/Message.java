package com.belrose.springbootchatgptbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private String role; //what you accessing with
    private String content; // What you looking for
}
