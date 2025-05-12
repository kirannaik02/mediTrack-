package com.meditrack.service.impl;

import com.meditrack.dto.GroupRequest;
import com.meditrack.dto.GroupResponse;
import com.meditrack.entity.Group;
import com.meditrack.repository.GroupRepo;
import com.meditrack.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;

    @Override
    public GroupResponse createGroup(GroupRequest request) {
        Group group = Group.builder()
                .groupName(request.getGroupName())
                .description(request.getDescription())
                .createdBy(request.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Group saved = groupRepo.save(group);

        return mapToResponse(saved);
    }

    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepo.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        return mapToResponse(group);
    }

    @Override
    public GroupResponse updateGroup(Long id, GroupRequest request) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));

        group.setGroupName(request.getGroupName());
        group.setDescription(request.getDescription());
        group.setCreatedBy(request.getCreatedBy());

        Group updated = groupRepo.save(group);
        return mapToResponse(updated);
    }

    @Override
    public String deleteGroup(Long id) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        groupRepo.delete(group);
        return "Group with ID " + id + " has been deleted successfully.";
    }

    private GroupResponse mapToResponse(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .description(group.getDescription())
                .createdBy(group.getCreatedBy())
                .createdAt(group.getCreatedAt())
                .isDeleted(group.isDeleted())
                .build();
    }
}
