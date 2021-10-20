package cz.uhk.ppro.inzeraty;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.services.PametoveUlozisteInzeratu;
import cz.uhk.ppro.inzeraty.services.UlozisteInzeratu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class InzeratyApplication implements WebMvcConfigurer {
  private UlozisteInzeratu ulozisteInzeratu = new PametoveUlozisteInzeratu();

  public static void main(String[] args) {
    SpringApplication.run(InzeratyApplication.class, args);
  }

  // view-resolver je konfigurovan v application.properties, ale slo by ho konfigurovat i zde

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // namapovat URL / na view jmenem index (tedy pres view-resolver na /WEB-INF/jsp/index.jsp)
    registry.addViewController("/").setViewName("index");
  }



  @Bean
  @Primary
  UlozisteInzeratu uloziste()
  {
    PametoveUlozisteInzeratu p = new PametoveUlozisteInzeratu();

    p.pridej(new Inzerat(0, "výměna", "Helma jezdecká velikost M", new BigDecimal("100"), "edit", new java.util.Date(101010)));
    p.pridej(new Inzerat(0, "nákup", "Bagetka z polotovaru", new BigDecimal("8"), "edit", new java.util.Date(108010)));
    p.pridej(new Inzerat(0, "prodej", "Aligator Kuřecí Nugetky s dresingem", new BigDecimal("65"), "edit", new java.util.Date(109010)));
    p.pridej(new Inzerat(0, "prodej", "Filtr na bazén Amazon Basics", new BigDecimal("100"), "edit", new java.util.Date(103010)));

    return p;
  }

}
