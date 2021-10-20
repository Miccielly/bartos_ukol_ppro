package cz.uhk.ppro.inzeraty.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.services.UlozisteInzeratu;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class SpravaController {
    private UlozisteInzeratu u = null;
    /**
     * @return ulozisteInzeratu
     */
    public UlozisteInzeratu getUloziste() {
        return u;
    }
    /**
     * @param uloziste uloziste, které má být nastaveno (injektujeme pomoci anotaci)
     */
    @Autowired
    public void setKatalog(UlozisteInzeratu uloziste) {
        this.u = uloziste;
    }

    /**
     * Vlastni akce namapovana na danou URL, naplni Model pro JSP a urci logicke jmeno view
     */
    @RequestMapping("/sprava.do")
    public ModelAndView zobrazit(@ModelAttribute("uloziste") UlozisteController u) {
        ModelAndView model = new ModelAndView("sprava");
        model.addObject("polozky", this.u.getInzeraty());
        //Map<Integer, Inzerat>  = new HashMap<Integer, Inzerat>();

		/*for (Inzerat p : k.getPolozky()) {
			mk.put(p.getId(), p);
		}
		*/
        //model.addObject("mapKosik", mk);

        return model;
    }

    /**
     * Zobrazeni tabulky polozek s tlacitk pro editaci/mazani
     */
    @RequestMapping(params="!akce")
    public ModelAndView show() throws Exception {
        return new ModelAndView("sprava","uloziste",getUloziste());
    }

    /**
     * Smazani polozky
     */
    @RequestMapping(params="akce=remove")
    public String remove(@RequestParam("polId") int id) {
        getUloziste().odstran(id);
        return "redirect:/sprava.do";
    }
}