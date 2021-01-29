package br.com.thehero.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.thehero.login.model.Role;
import br.com.thehero.login.model.Role.RoleName;
import br.com.thehero.login.repository.RoleRepository;

@Configuration
public class ApplicationConfig {

  private RoleRepository roleRepository;

  public ApplicationConfig(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Bean
  public void createRoleWhenInitializing() {

    for (RoleName roleName : RoleName.values()) {
      if (!roleRepository.findByName(roleName).isPresent()) {
        roleRepository.saveAndFlush(new Role(roleName));
      }
    }
  }
}
