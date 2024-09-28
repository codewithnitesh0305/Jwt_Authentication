package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
