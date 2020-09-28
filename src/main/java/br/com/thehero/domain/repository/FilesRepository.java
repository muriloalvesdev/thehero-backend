package br.com.thehero.domain.repository;

import br.com.thehero.domain.model.Files;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, UUID> {}
