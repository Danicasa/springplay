package com.danicasa.microservicios.app.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danicasa.microservicios.app.projects.model.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
