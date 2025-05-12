package com.meditrack.service.impl;

import com.meditrack.dto.GroupUserRequest;
import com.meditrack.dto.GroupUserResponse;
import com.meditrack.entity.Group;
import com.meditrack.entity.GroupUser;
import com.meditrack.entity.User;
import com.meditrack.repository.GroupRepo;
import com.meditrack.repository.GroupUserRepo;
import com.meditrack.repository.UserRepo;
import com.meditrack.service.GroupUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupUserServiceImpl implements GroupUserService {

    private final GroupUserRepo groupUserRepository;
    private final UserRepo userRepo;
    private final GroupRepo groupRepository;

    @Override
    public GroupUserResponse createGroupUser(GroupUserRequest request) {
        User user = userRepo.findById(request.getUserId()).orElseThrow();
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow();


        GroupUser groupUser = GroupUser.builder()
                .user(user)
                .group(group)
                .joinedAt(LocalDateTime.now())
                .role(request.getRole())
                .status(request.getStatus())
                .notes(request.getNotes())
                .build();

        groupUserRepository.save(groupUser);
        return mapToResponse(groupUser);
    }

    @Override
    public List<GroupUserResponse> getAllGroupUsers() {
        return groupUserRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GroupUserResponse getGroupUserById(Long id) {
        GroupUser groupUser = groupUserRepository.findById(id).orElseThrow();
        return mapToResponse(groupUser);
    }

    @Override
    public GroupUserResponse updateGroupUser(Long id, GroupUserRequest request) {
        GroupUser groupUser = groupUserRepository.findById(id).orElseThrow();
        groupUser.setUser(userRepo.findById(request.getUserId()).orElseThrow());
        groupUser.setGroup(groupRepository.findById(request.getGroupId()).orElseThrow());
        groupUser.setRole(request.getRole());
        groupUser.setStatus(request.getStatus());
        groupUser.setNotes(request.getNotes());

        groupUserRepository.save(groupUser);
        return mapToResponse(groupUser);
    }

    @Override
    public String deleteGroupUser(Long id) {
        groupUserRepository.deleteById(id);
        return "GroupUser deleted successfully!";
    }

    private GroupUserResponse mapToResponse(GroupUser groupUser) {
        GroupUserResponse response = new GroupUserResponse();
        response.setId(groupUser.getId());
        response.setUserId(groupUser.getUser().getId());
        response.setGroupId(groupUser.getGroup().getId());
        response.setJoinedAt(groupUser.getJoinedAt());
        response.setRole(groupUser.getRole());
        response.setStatus(groupUser.getStatus());
        response.setNotes(groupUser.getNotes());

        return response;
    }
}
