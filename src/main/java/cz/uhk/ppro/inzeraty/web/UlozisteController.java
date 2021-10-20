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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UlozisteController {
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
	@RequestMapping("/uloziste.do")
	public ModelAndView zobrazit(@ModelAttribute("uloziste") UlozisteController u) {
		ModelAndView model = new ModelAndView("uloziste");
		model.addObject("polozky", this.u.getInzeraty());	

		return model;
	}
}