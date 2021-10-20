package cz.uhk.ppro.inzeraty.controllers;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.UlozisteInzeratu;
import cz.uhk.ppro.inzeraty.utils.Kategorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

  private UlozisteInzeratu ulozisteInzeratu;

  @Autowired
  public void setUlozisteInzeratu(UlozisteInzeratu ulozisteInzeratu) {
    this.ulozisteInzeratu = ulozisteInzeratu;
  }


  @RequestMapping("/")
  public String indexPost(@RequestParam(value = "vybranaKategorie", required = false) String vybranaKategorie, Model model) {
    List<Inzerat> inzeraty = new ArrayList<>();

    if (Kategorie.validniKategorie.contains(vybranaKategorie)) {
      inzeraty.addAll(ulozisteInzeratu.getInzeratyByKategorie(vybranaKategorie));
    } else {
      inzeraty.addAll(ulozisteInzeratu.getInzeraty());
    }

    System.out.println(vybranaKategorie);

    model.addAttribute("inzeraty", inzeraty);
    model.addAttribute("kategorie", Kategorie.validniKategorie);
    model.addAttribute("vybranaKategorie", vybranaKategorie);

    return "index";
  }
}
