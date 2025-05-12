package com.meditrack.controller;

import com.meditrack.dto.GroupUserRequest;
import com.meditrack.dto.GroupUserResponse;
import com.meditrack.service.GroupUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-users")
@RequiredArgsConstructor
public class GroupUserController {

    private final GroupUserService groupUserService;

    @PostMapping
    public ResponseEntity<GroupUserResponse> create(@RequestBody GroupUserRequest request) {
        return ResponseEntity.ok(groupUserService.createGroupUser(request));
    }

    @GetMapping
    public ResponseEntity<List<GroupUserResponse>> getAll() {
        return ResponseEntity.ok(groupUserService.getAllGroupUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupUserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(groupUserService.getGroupUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupUserResponse> update(@PathVariable Long id, @RequestBody GroupUserRequest request) {
        return ResponseEntity.ok(groupUserService.updateGroupUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(groupUserService.deleteGroupUser(id));
    }
}
