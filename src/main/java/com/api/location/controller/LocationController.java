package com.api.location.controller;


import com.api.location.dto.CurrentLocationRequest;
import com.api.location.dto.LocationDto;
import com.api.location.dto.MemberLocationResponse;
import com.api.location.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api")
public class LocationController {


    private final LocationService locationService;


    /**
     * 회원 등록
     * @param name
     */
    @Operation(summary = "사용자 등록 API", description = "회원의 이름 정보를 받아 Member 테이블에 insert")
    @Parameters({
            @Parameter(name = "name", description = "이름", example = "테스터"),
    })
    @PostMapping("/member")
    public void insertMember(@RequestBody String name){

        locationService.insertMember(name);
    }

    /**
     * 사용자 현재 위치 정보 등록
     * @param req
     * @param memberId
     */
    @Operation(summary = "사용자 현재 위치 API", description = "사용자의 현재 위치 정보를 받아 Location 테이블에 insert")
    @Parameters({
            @Parameter(name = "gameId", in = ParameterIn.PATH, required = true, description = "game 식별자"),
            @Parameter(name = "memberId", in = ParameterIn.PATH, required = true, description = "회원 식별자")
    })
    @PostMapping("/location/{gameId}/{memberId}/current")
    public Long setCurrentLocation(
            @RequestBody CurrentLocationRequest req,
            @PathVariable(name = "gameId") Long gameId,
            @PathVariable(name = "memberId") Long memberId){

        return locationService.setLocationInGame(gameId,memberId,req);
    }


    /**
     * 기준점을 기준으로 이동한 위치 정보 리스트 등록
     * @param memberId
     * @param locationId
     * @param locationList
     */
    @Operation(summary = "기준점으로 부터 이동한 위치 정보 리스트 등록 API", description = "위치 정보 리스트를 받아 LocationLog 테이블에 insert")
    @Parameters({
            @Parameter(name = "memberId", in = ParameterIn.PATH, required = true, description = "회원 식별자"),
            @Parameter(name = "locationId", in = ParameterIn.PATH, required = true, description = "기준점의 정보 식별자")
    })
    @PostMapping("/location/{memberId}/{locationId}/log")
    public void setLocationLog(@PathVariable(name = "memberId") Long memberId,
                               @PathVariable(name = "locationId") Long locationId,
                               @RequestBody List<LocationDto> locationList){
        locationService.setLocationLog(memberId,locationId,locationList);
    }


    @Operation(summary = "기준점으로 부터 이동한 위치 정보 리스트 조회 API", description = "기준점의 정보를 받아 이동한 위치 목록과 해당 지점의 거리를 조회")
    @Parameters({
            @Parameter(name = "memberId", in = ParameterIn.PATH, required = true, description = "회원 식별자"),
            @Parameter(name = "locationId", in = ParameterIn.PATH, required = true, description = "기준점의 정보 식별자")
    })
    @GetMapping("/location/{memberId}/{locationId}/log")
    public MemberLocationResponse getLocationLog(
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "locationId") Long locationId
    ){
        return locationService.getMemberLocation(memberId,locationId);
    }





}
