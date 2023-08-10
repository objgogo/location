package com.api.location.repository;

import com.api.location.entity.LocationEntity;
import com.api.location.entity.MemberEntity;
import com.api.location.entity.MemberGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity,Long> {

    LocationEntity findByIdAndMember(Long id, MemberEntity member);

    List<LocationEntity> findByMemberAndMemberGame(MemberEntity member, MemberGameEntity memberGameEntity);
}
