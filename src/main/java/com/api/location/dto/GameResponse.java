package com.api.location.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameResponse {

    private Long id;

    private String title;

    private LocalDateTime startDt;

    private LocalDateTime endDt;
}
