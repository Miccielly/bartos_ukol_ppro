package cz.uhk.ppro.inzeraty.controllers;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.UlozisteInzeratu;
import cz.uhk.ppro.inzeraty.utils.Kategorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InzeratyController {
  private UlozisteInzeratu ulozisteInzeratu;

  @Autowired
  public void setUlozisteInzeratu(UlozisteInzeratu ulozisteInzeratu) {
    this.ulozisteInzeratu = ulozisteInzeratu;
  }

  public Inzerat getOrFailInzerat(int id) {
    Inzerat inzerat = ulozisteInzeratu.getById(id);

    if (inzerat == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inzerat nebyl nalezan");
    }

    return inzerat;
  }

  @RequestMapping("/inzerat")
  private String getInzerat(@RequestParam(value = "id") Integer id, Model model) {
    model.addAttribute("inzerat", getOrFailInzerat(id));
    return "getInzerat";
  }

  @GetMapping("/add")
  private String addInzerat(Model model) {
    model.addAttribute("errors", new ArrayList<String>());
    model.addAttribute("kategorie", Kategorie.validniKategorie);
    return "addInzerat";
  }

  @PostMapping("/add")
  private String addInzeratPost(@ModelAttribute Inzerat inzerat, Model model) {
    List<String> errors = new ArrayList<>();

    if (!Kategorie.validniKategorie.contains(inzerat.getKategorie())) {
      errors.add("Tato kategorie neexistuje.");
    }

    if (errors.isEmpty()) {
      ulozisteInzeratu.pridej(inzerat);
      return "redirect:/inzerat?id=" + inzerat.getId();
    }

    model.addAttribute("errors", errors);
    return "addInzerat";
  }

  @RequestMapping("/delete")
  public String delete(@RequestParam(value = "id") int id, @RequestParam(value = "heslo", required = false) String heslo, Model model) {
    Inzerat inzerat = getOrFailInzerat(id);
    List<String> errors = new ArrayList<>();

    if (heslo != null && !heslo.strip().isEmpty() && inzerat.getHesloProUpravu().equals(heslo)) {
      ulozisteInzeratu.odstran(inzerat);
      return "redirect:/";
    } else if (heslo != null) {
      errors.add("Chybné heslo");
    }

    model.addAttribute("inzerat", inzerat);
    model.addAttribute("errors", errors);

    return "deleteInzerat";
  }
}
