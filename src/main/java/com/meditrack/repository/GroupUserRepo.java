package com.meditrack.repository;

import com.meditrack.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUserRepo extends JpaRepository<GroupUser, Long> {
}
