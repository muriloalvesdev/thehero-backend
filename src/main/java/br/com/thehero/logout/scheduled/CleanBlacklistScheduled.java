package br.com.thehero.logout.scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import br.com.thehero.login.config.jwt.JwtBlacklist;

@Component
@EnableScheduling
public class CleanBlacklistScheduled {

  private static final String TIME_ZONE = "America/Sao_Paulo";

  private JwtBlacklist jwtBlacklist;

  public CleanBlacklistScheduled(JwtBlacklist jwtBlacklist) {
    this.jwtBlacklist = jwtBlacklist;
  }

  @Scheduled(cron = "0 0/5 * * * *", zone = TIME_ZONE)
  public void cleanBlacklist() {
    jwtBlacklist.cleanBlacklist();
  }

}
