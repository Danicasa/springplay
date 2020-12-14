package com.danicasa.microservicios.app.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danicasa.microservicios.app.projects.models.entity.Projects;

public interface ProjectRepository extends JpaRepository<Projects, Integer> {

}
