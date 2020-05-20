package br.com.fiap.mba.persistence.spring.persistence.entrypoints.demo;

import br.com.fiap.mba.persistence.spring.persistence.domain.demo.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demonstracao")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping()
    public void handle() {
        demoService.geraCargaInicialDemonstracao();
    }

}
