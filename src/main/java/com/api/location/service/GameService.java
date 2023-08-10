package com.api.location.service;

import com.api.location.dto.CreateGameRequest;
import com.api.location.dto.GameInfoResponse;
import com.api.location.dto.MemberLocationResponse;
import com.api.location.dto.MemberResponse;
import com.api.location.entity.GameEntity;
import com.api.location.entity.MemberEntity;
import com.api.location.entity.MemberGameEntity;
import com.api.location.repository.GameRepository;
import com.api.location.repository.MemberGameRepository;
import com.api.location.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private MemberRepository memberRepository;
    private GameRepository gameRepository;
    private MemberGameRepository memberGameRepository;
    private ObjectMapper objectMapper;




    /**
     * 게임 생성
     * @param req
     */
    public void createGame(CreateGameRequest req){

        gameRepository.save(
                GameEntity.builder()
                        .title(req.getTitle())
                        .startDt(req.getStartDt())
                        .endDt(req.getEndDt())
                        .build()
        );
    }

    /**
     * 사용자 게임 참여
     * @param gameId
     * @param memberId
     */
    public void joinGame(Long gameId, Long memberId){

        Optional<GameEntity> checkGame = gameRepository.findById(gameId);
        Optional<MemberEntity> checkMember = memberRepository.findById(memberId);

        if(checkGame.isPresent() && checkMember.isPresent()){
            memberGameRepository.save(
                    MemberGameEntity.builder()
                            .member(checkMember.get())
                            .game(checkGame.get())
                            .createDt(LocalDateTime.now())
                            .build()
            );
        }
    }

    public GameInfoResponse getGameInfo(Long gameId){

        Optional<GameEntity> checkGame = gameRepository.findById(gameId);

        if(checkGame.isPresent()){

            GameInfoResponse res = new GameInfoResponse();
            res.setTitle(checkGame.get().getTitle());
            res.setStartDt(checkGame.get().getStartDt());
            res.setEndDt(checkGame.get().getEndDt());

            return res;
        } else {
            return null;
        }
    }
}
