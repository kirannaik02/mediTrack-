package com.meditrack.service;

import com.meditrack.dto.GroupRequest;
import com.meditrack.dto.GroupResponse;

import java.util.List;

public interface GroupService {

    GroupResponse createGroup(GroupRequest request);

    List<GroupResponse> getAllGroups();

    GroupResponse getGroupById(Long id);

    GroupResponse updateGroup(Long id, GroupRequest request);

    String deleteGroup(Long id);
}
