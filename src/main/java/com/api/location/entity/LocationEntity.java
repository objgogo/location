package com.api.location.entity;

import com.api.location.common.LocationState;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "location")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private LocationState state;

    @Column(name = "createDt")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createDt;

    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    private MemberEntity member;

    @OneToMany(mappedBy = "location", targetEntity = LocationLogEntity.class, fetch = FetchType.LAZY)
    private List<LocationLogEntity> locationLogEntityList;

    @ManyToOne(targetEntity = MemberGameEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_game_id")
    private MemberGameEntity memberGame;

}
