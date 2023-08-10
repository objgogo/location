package com.api.location.service;

import com.api.location.dto.CurrentLocationRequest;
import com.api.location.dto.LocationDistance;
import com.api.location.dto.LocationDto;
import com.api.location.dto.MemberLocationResponse;
import com.api.location.entity.*;
import com.api.location.repository.*;
import com.api.location.util.LocationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {

    private MemberRepository memberRepository;
    private LocationRepository locationRepository;
    private LocationLogRepository locationLogRepository;
    private GameRepository gameRepository;
    private MemberGameRepository memberGameRepository;

    private LocationUtil locationUtil;


    /**
     * 사용자 현재 위치 저장
     * @param memberId
     * @param req
     * @return LocationId
     */
    public Long setLocationInGame(Long gameId, Long memberId, CurrentLocationRequest req){

        Optional<MemberEntity> checkMember = memberRepository.findById(memberId);
        Optional<GameEntity> checkGame = gameRepository.findById(gameId);

        if(checkMember.isPresent() && checkGame.isPresent()){
            Optional<MemberGameEntity> checkInGame = memberGameRepository.findByMemberAndGame(checkMember.get(),checkGame.get());

            if(checkInGame.isPresent()){
                LocationEntity location =  LocationEntity.builder()
                        .longitude(req.getLongitude())
                        .latitude(req.getLatitude())
                        .createDt(LocalDateTime.now())
                        .state(req.getState())
                        .member(checkMember.get())
                        .memberGame(checkInGame.get())
                        .build();
                location = locationRepository.save(location);

                return location.getId();
            } else{
                return null;
            }

        } else{
            return null;
        }
    }

    /**
     * 기준점으로 부터 이동한 위치 리스트 저장
     * @param locationId
     * @param locationList
     */
    public void setLocationLog(Long memberId, Long locationId, List<LocationDto> locationList){

        Optional<MemberEntity> checkMember = memberRepository.findById(memberId);

        if(checkMember.isPresent()){

            LocationEntity checkLocation = locationRepository.findByIdAndMember(locationId,checkMember.get());
            if(checkLocation != null){
                List<LocationLogEntity> logList = locationList.stream().map( dto ->
                        LocationLogEntity
                                .builder()
                                .longitude(dto.getLongitude())
                                .latitude(dto.getLatitude())
                                .location(checkLocation)
                                .createDt(LocalDateTime.now())
                                .build()
                ).toList();

                locationLogRepository.saveAll(logList);
            }
        }

    }

    /**
     * 사용자 등록
     * @param name
     */
    public void insertMember(String name){
        memberRepository.save(
                MemberEntity.builder()
                        .name(name)
                        .createDt(LocalDateTime.now())
                        .build()
        );
    }

    /**
     * 기준점으로 부터 기록된 log 데이터 및 기준점과의 거리 조회
     * @param memberId
     * @param locationId
     * @return
     */
    public MemberLocationResponse getMemberLocation(Long memberId, Long locationId){
        Optional<MemberEntity> checkMember = memberRepository.findById(memberId);
        if(checkMember.isPresent()){
            LocationEntity location = locationRepository.findByIdAndMember(locationId,checkMember.get());

            MemberLocationResponse res = new MemberLocationResponse();

            res.setLocationId(locationId);

            LocationDto start = new LocationDto();
            start.setLatitude(location.getLatitude());
            start.setLongitude(location.getLongitude());
            res.setStart(start);

            List<LocationDistance> distanceList = location.getLocationLogEntityList().stream().map( log -> {
                LocationDistance distance = new LocationDistance();
                distance.setStartLatitude(location.getLatitude());
                distance.setStartLongitude(location.getLongitude());
                distance.setEndLatitude(log.getLatitude());
                distance.setEndLongitude(log.getLongitude());
                distance.setDistance(locationUtil.getDistanceByLocation(location.getLatitude(), location.getLongitude(), log.getLatitude(),log.getLongitude()));
                return distance;
            }).toList();

            res.setDistanceList(distanceList);

            return res;
        } else {
            return null;
        }
    }

}
