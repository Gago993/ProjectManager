package mk.finki.webprogramiranje.projectmanager.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public ModelAndView showMessage() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("message", "ok");
		return mv;
	}
}