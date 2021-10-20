package cz.uhk.ppro.inzeraty.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kategorie {
  public static final String NAKUP = "Nákup";
  public static final String PRODEJ = "Prodej";
  public static final String VYMENA = "Výměna";

  public static final List<String> validniKategorie = new ArrayList(Arrays.asList(
      NAKUP, PRODEJ, VYMENA
  ));
}
