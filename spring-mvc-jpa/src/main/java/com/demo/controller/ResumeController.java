package com.demo.controller;

import com.demo.pojo.Resume;
import com.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    private final String PAGE = "";

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Resume resume, Model model) {
        Resume insert = resumeService.save(resume);
        model.addAttribute("data", insert);
        return PAGE;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestBody Resume resume, Model model) {
        Resume update = resumeService.update(resume);
        model.addAttribute("data", update);
        return PAGE;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, Model model) {
        Integer delete = resumeService.delete(id);
        model.addAttribute("data", delete);
        return PAGE;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Resume> all = resumeService.findAll();
        model.addAttribute("data", all);
        return PAGE;
    }

}
