package grupo3mtech.ao.rh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RHController {

    @GetMapping("/info")
    public String getInfo() {
        return "Informações do Microsserviço RH";
    }
}
