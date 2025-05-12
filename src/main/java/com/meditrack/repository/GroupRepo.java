package com.meditrack.repository;

import com.meditrack.entity.Group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Long> {
}
