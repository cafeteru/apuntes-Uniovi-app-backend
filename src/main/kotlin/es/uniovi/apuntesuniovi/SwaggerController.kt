package es.uniovi.apuntesuniovi

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SwaggerController {

  @GetMapping("/")
  fun swagger(): String? {
    return "redirect:/swagger-ui.html";
  }
}