package com.ecore.roles.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecore.roles.model.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, UUID> {

    List<Membership> findByUserIdAndTeamId(UUID userId, UUID teamId);

    @Query("SELECT m FROM Membership m where m.userId = :userId AND m.teamId = :teamId AND m.role.id = :roleId")
    Optional<Membership> findByUserIdAndTeamIdAndRoleId(
            @Param("userId") UUID userId,
            @Param("teamId") UUID teamId,
            @Param("roleId") UUID roleId);

    List<Membership> findByRoleId(UUID roleId);
}
