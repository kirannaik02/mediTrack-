package com.meditrack.service;

import com.meditrack.dto.GroupUserRequest;
import com.meditrack.dto.GroupUserResponse;

import java.util.List;

public interface GroupUserService {
    GroupUserResponse createGroupUser(GroupUserRequest request);
    List<GroupUserResponse> getAllGroupUsers();
    GroupUserResponse getGroupUserById(Long id);
    GroupUserResponse updateGroupUser(Long id, GroupUserRequest request);
    String deleteGroupUser(Long id);
}
