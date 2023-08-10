package com.api.location.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "member_game")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = MemberEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(targetEntity = GameEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;


    @OneToMany(mappedBy = "memberGame" , targetEntity = LocationEntity.class, fetch = FetchType.LAZY)
    private List<LocationEntity> location;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createDt;
}
