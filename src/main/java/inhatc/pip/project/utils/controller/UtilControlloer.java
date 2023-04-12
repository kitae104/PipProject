package inhatc.pip.project.utils.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilControlloer {
	
	@GetMapping("/") //get방식으로 한다
	public String index() {
		return "<H1>Hello World 2<H1>";
	}
}

//모바일도 할거면 json ->레스트 서비스를 해야함 