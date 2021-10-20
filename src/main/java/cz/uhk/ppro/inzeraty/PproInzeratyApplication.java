package cz.uhk.ppro.inzeraty;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.PametoveUlozisteInzeratu;
import cz.uhk.ppro.inzeraty.sluzby.UlozisteInzeratu;
import cz.uhk.ppro.inzeraty.utils.Kategorie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class PproInzeratyApplication {
  private UlozisteInzeratu ulozisteInzeratu = new PametoveUlozisteInzeratu();

  public static void main(String[] args) {
    SpringApplication.run(PproInzeratyApplication.class, args);
  }

  @Bean
  @Primary
    // prioritne pouzit tuto definici beany misto definice zpusobene anotaci @Service u tridy SimpleKatalog
  UlozisteInzeratu getUlozisteInzeratu() {
    if (ulozisteInzeratu.getInzeraty().isEmpty()) {
      Inzerat inzetar = new Inzerat();

      inzetar.setText("Testovací inzerát");
      inzetar.setKategorie(Kategorie.NAKUP);
      inzetar.setCena(new BigDecimal(100));
      inzetar.setDatum(new Date());

      ulozisteInzeratu.pridej(inzetar);
    }
    return ulozisteInzeratu;
  }
}
