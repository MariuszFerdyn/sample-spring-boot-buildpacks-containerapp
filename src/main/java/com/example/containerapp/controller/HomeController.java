package com.example.containerapp.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, MMMM d, uuuu HH:mm z")
                    .withZone(ZoneId.systemDefault());

    @GetMapping("/")
    public String home(@RequestParam(name = "name", required = false) String name, Model model) {
        String displayName = (name == null || name.isBlank()) ? "Explorer" : name.trim();

        model.addAttribute("serverTime", FORMATTER.format(Instant.now()));
        model.addAttribute("name", displayName);
        model.addAttribute("features", List.of(
                "Cloud-native auto-configuration",
                "Container-ready build using Buildpacks",
                "Azure Container Apps friendly defaults",
                "Production-ready Actuator endpoints",
                "Thymeleaf-driven responsive UI"
        ));

        return "index";
    }
}
