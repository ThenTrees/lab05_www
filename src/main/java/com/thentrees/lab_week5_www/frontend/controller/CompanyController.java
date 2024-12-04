package com.thentrees.lab_week5_www.frontend.controller;

import com.thentrees.lab_week5_www.backend.dto.response.CandidateResponseDto;
import com.thentrees.lab_week5_www.backend.models.Company;
import com.thentrees.lab_week5_www.backend.models.Job;
import com.thentrees.lab_week5_www.backend.models.JobSkill;
import com.thentrees.lab_week5_www.backend.services.ICompanyService;
import com.thentrees.lab_week5_www.backend.services.IJobService;
import com.thentrees.lab_week5_www.backend.services.IJobSkillService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/companies")
@Slf4j
@RequiredArgsConstructor
public class CompanyController {
    private final ICompanyService companyService;
    private final IJobService jobService;
    private final IJobSkillService jobSkillService;
    /**
     * Show list company
     * candidate has seen site for find company
     * @param ModelAndView
     * @return page list company
     */
    @GetMapping("")
    public ModelAndView showListCompany(ModelAndView mv) {
        log.info("Show list company:::");
        mv.addObject("companies", companyService.getAllCompanies());
        mv.setViewName("/list-company");
        return mv;
    }

    /**
     * Show detail company
     * candidate has seen detail company
     * @param ModelAndView
     * @return page detail company
     */
    @GetMapping("/{id}")
    public ModelAndView showDetailCompany(ModelAndView mv, @PathVariable("id") Long id) {
        log.info("Show detail company:::");

        Company company = companyService.getCompanyById(id);
        // get all job of company
        List<Job> jobList = jobService.getAllJobByCompanyId(id);

        mv.addObject("company", companyService.getCompanyById(id));
        mv.addObject("jobs", jobList);
        mv.setViewName("/company-detail");
        return mv;
    }
}
