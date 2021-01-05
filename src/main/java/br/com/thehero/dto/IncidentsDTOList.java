package br.com.thehero.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentsDTOList {
  private List<IncidentsDTO> incidents;
}
