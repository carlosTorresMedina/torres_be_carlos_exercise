package com.ecore.roles.service;

import java.util.List;
import java.util.UUID;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;

public interface MembershipService {

    Membership assignRoleToMembership(Membership membership) throws ResourceNotFoundException;

    List<Membership> getMemberships(UUID roleId);
}
