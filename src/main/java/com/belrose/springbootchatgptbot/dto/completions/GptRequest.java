package com.belrose.springbootchatgptbot.dto.completions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GptRequest implements Serializable {
    private String model;
    private String prompt;
}
