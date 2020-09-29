package br.com.thehero.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thehero.domain.model.Files;

public interface FilesRepository extends JpaRepository<Files, UUID> {}
