package mk.finki.webprogramiranje.projectmanager.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public ModelAndView showMessage() {
		return new ModelAndView("index");
	}
}