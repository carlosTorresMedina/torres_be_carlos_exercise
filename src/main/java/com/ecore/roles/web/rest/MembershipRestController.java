package com.ecore.roles.web.rest;

import static com.ecore.roles.web.dto.MembershipDto.fromModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecore.roles.model.Membership;
import com.ecore.roles.service.MembershipService;
import com.ecore.roles.web.MembershipApi;
import com.ecore.roles.web.dto.MembershipDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles/memberships")
public class MembershipRestController implements MembershipApi {

    private final MembershipService membershipsService;

    @Override
    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<MembershipDto> assignRoleToMembership(
            @NotNull @Valid @RequestBody MembershipDto membershipDto) {
        Membership membership = membershipsService.assignRoleToMembership(membershipDto.toModel());
        return ResponseEntity
                .status(201)
                .body(fromModel(membership));
    }

    @Override
    @GetMapping(
            path = "/search",
            produces = {"application/json"})
    public ResponseEntity<List<MembershipDto>> getMemberships(
            @RequestParam UUID roleId) {

        List<Membership> memberships = membershipsService.getMemberships(roleId);

        List<MembershipDto> newMembershipDto = new ArrayList<>();

        for (Membership membership : memberships) {
            MembershipDto membershipDto = fromModel(membership);
            newMembershipDto.add(membershipDto);
        }

        return ResponseEntity
                .status(200)
                .body(newMembershipDto);
    }

}
