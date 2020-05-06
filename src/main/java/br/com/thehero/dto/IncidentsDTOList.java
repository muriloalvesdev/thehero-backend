package br.com.thehero.dto;

import java.util.List;

public class IncidentsDTOList {

  private List<IncidentsDTO> incidents;

  public IncidentsDTOList(List<IncidentsDTO> incidents) {
    this.incidents = incidents;
  }
  public IncidentsDTOList() {
  }
  
  public List<IncidentsDTO> getIncidents() {
    return incidents;
  }

  public void setIncidents(List<IncidentsDTO> incidents) {
    this.incidents = incidents;
  }

}
