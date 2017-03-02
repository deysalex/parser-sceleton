package server.controller;

/**
 * Created by Deys on 11.06.2015.
 */

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.entity.Vacancy;
import server.form.VacancyForm;
import server.manager.parse.WebSiteParseManager;
import server.service.VacancyService;
import server.translator.VacancyTranslator;
import server.validator.VacancyValidator;

@Controller
@RequestMapping("vacancy")
public class VacancyController {

    private final Logger log = LoggerFactory.getLogger(VacancyController.class);

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyValidator vacancyValidator;

    @Autowired
    private VacancyTranslator vacancyTranslator;

    @Autowired
    private WebSiteParseManager webSiteParseManager;

    @RequestMapping(value = {"/", "/list"})
    public String getList(
        Model model)
    {
        model.addAttribute("list", vacancyService.getAll());

        return "vacancy/list";
    }

    @RequestMapping(value = {"/list/category"}, method = RequestMethod.GET)
    public String getListByCategory(
        Model model,
        @RequestParam("id") Long categoryId)
    {
        model.addAttribute("list", vacancyService.getByCategory(categoryId));

        return "vacancy/list";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String getSearchList(
        Model model,
        @RequestParam("text") String text)
    {
        model.addAttribute("list", vacancyService.getByText(text));

        return "vacancy/list";
    }

    @RequestMapping(value = {"/view"}, method = RequestMethod.GET)
    public String getVacancyById(
        Model model,
        @RequestParam("id") Long vacancyId)
    {
        model.addAttribute("vacancy", vacancyService.getOne(vacancyId));

        return "vacancy/view";
    }

    @RequestMapping(value = {"/new"})
    public String create(
        Model model)
    {
        VacancyForm vacancyForm = new VacancyForm();
        model.addAttribute("vacancyForm", vacancyForm);

        return "vacancy/new";
    }

    @RequestMapping(value = {"/newsubmit"}, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String createSubmit(
        @ModelAttribute("vacancyForm") VacancyForm vacancyForm,
        BindingResult result)
    {
        vacancyValidator.validate(vacancyForm, result);
        if (result.hasErrors()) {
            return "vacancy/new";
        }
        Vacancy vacancy = new Vacancy();
        vacancyTranslator.dataToBusiness(vacancyForm, vacancy);
        vacancyService.create(vacancy);

        return "redirect:/vacancy/list";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String edit(
        Model model,
        @RequestParam("id") Long id)
    {
        Vacancy vacancy = vacancyService.getOne(id);
        if (vacancy == null) {
            return "redirect:/vacancy/new";
        }

        VacancyForm vacancyForm = new VacancyForm();
        vacancyTranslator.businessToData(vacancy, vacancyForm);

        model.addAttribute("vacancyForm", vacancyForm);

        return "vacancy/edit";
    }

    @RequestMapping(value = {"/editsubmit"}, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String editSubmit(
        @ModelAttribute("vacancyForm") VacancyForm vacancyForm,
        BindingResult result)
    {
        vacancyValidator.validate(vacancyForm, result);
        if (result.hasErrors()) {
            return "vacancy/edit";
        }

        Vacancy vacancy = new Vacancy();
        vacancyTranslator.dataToBusiness(vacancyForm, vacancy);
        vacancyService.update(vacancy);

        return "redirect:/vacancy/list";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(
        @RequestParam("id") String id)
    {
        String result = "false";
        String error = "";
        JSONObject jsonObject = new JSONObject();
        try {
            vacancyService.delete(Long.parseLong(id));
            result = "true";
        } catch (Exception e) {
            error = e.getMessage();
            log.error(error);
        }
        jsonObject.put("result", result);
        jsonObject.put("error", error);

        return jsonObject.toJSONString();
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/export/website", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String exportWebSite(
        @RequestParam("url") String url,
        @RequestParam("category") String categoryId,
        @RequestParam("city") String cityId)
    {
        String result = "false";
        String error = "";
        JSONObject jsonObject = new JSONObject();
        try {
            if (!vacancyService.existUrl(url)) {
                Vacancy vacancy = webSiteParseManager.parse(url, categoryId, cityId);
                vacancyService.create(vacancy);
            }
            result = "true";
        } catch (Exception e) {
            error = e.getMessage();
            log.error(error);
        }
        jsonObject.put("result", result);
        jsonObject.put("error", error);

        return jsonObject.toJSONString();
    }

}
