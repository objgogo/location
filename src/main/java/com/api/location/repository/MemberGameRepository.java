package com.api.location.repository;

import com.api.location.entity.GameEntity;
import com.api.location.entity.MemberEntity;
import com.api.location.entity.MemberGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberGameRepository extends JpaRepository<MemberGameEntity, Long> {

    Optional<MemberGameEntity> findByMemberAndGame(MemberEntity member, GameEntity game);

    List<MemberGameEntity> findByGame(GameEntity game);
}
