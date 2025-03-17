package org.travel.insurance.web.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.ui.ModelMap;

import org.travel.insurance.dto.v1.AdapterVersion1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

@Controller
@RequiredArgsConstructor
public class TravelInsuranceControllerV1 {

    private final AdapterVersion1 adapter;

    private final static String URL = "/insurance/travel/web/v1";

    @GetMapping(URL)
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV1());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV1());
        return "travel-calculate-premium";
    }

    @PostMapping(URL)
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequestV1 request,
                              ModelMap modelMap) {
        var response = adapter.calculate(request);
        modelMap.addAttribute("response", response);
        return "travel-calculate-premium";
    }

}
