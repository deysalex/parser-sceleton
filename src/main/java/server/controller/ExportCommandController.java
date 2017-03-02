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
import server.entity.ExportCommand;
import server.form.ExportCommandForm;
import server.manager.ParseManager;
import server.service.ExportCommandService;
import server.translator.ExportCommandTranslator;
import server.validator.ExportCommandValidator;

@Controller
@RequestMapping("export/command")
public class ExportCommandController {

    private final Logger log = LoggerFactory.getLogger(ExportCommandController.class);

    @Autowired
    private ExportCommandService exportCommandService;

    @Autowired
    private ExportCommandValidator exportCommandValidator;

    @Autowired
    private ExportCommandTranslator exportCommandTranslator;

    @Autowired
    private ParseManager parseManager;

    @RequestMapping(value = {"", "/", "/list"})
    public String getList(
        Model model)
    {
        model.addAttribute("list", exportCommandService.getAll());

        return "export/command/list";
    }

    @RequestMapping(value = {"/view"}, method = RequestMethod.GET)
    public String getExportCommandById(
        Model model,
        @RequestParam("id") Long exportCommandId)
    {
        model.addAttribute("exportCommand", exportCommandService.getOne(exportCommandId));

        return "export/command/view";
    }

    @RequestMapping(value = {"/new"})
    public String createExportCommand(
        Model model)
    {
        ExportCommandForm exportCommandForm = new ExportCommandForm();
        model.addAttribute("exportCommandForm", exportCommandForm);

        return "export/command/new";
    }

    @RequestMapping(value = {"/newsubmit"}, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String createExportCommandSubmit(
        Model model,
        @ModelAttribute("exportCommandForm") ExportCommandForm exportCommandForm,
        BindingResult result)
    {
        exportCommandValidator.validate(exportCommandForm, result);
        if (result.hasErrors()) {
            return "export/command/new";
        }
        ExportCommand exportCommand = new ExportCommand();
        exportCommandTranslator.dataToBusiness(exportCommandForm, exportCommand);
        exportCommandService.create(exportCommand);

        return "redirect:/export/command/list";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String editExportCommand(
        Model model,
        @RequestParam("id") Long id)
    {
        ExportCommand exportCommand = exportCommandService.getOne(id);
        if (exportCommand == null) {
            return "redirect:/export/command/new";
        }

        ExportCommandForm exportCommandForm = new ExportCommandForm();
        exportCommandTranslator.businessToData(exportCommand, exportCommandForm);

        model.addAttribute("exportCommandForm", exportCommandForm);

        return "export/command/edit";
    }

    @RequestMapping(value = {"/editsubmit"}, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String editExportCommandSubmit(
        Model model,
        @ModelAttribute("exportCommandForm") ExportCommandForm exportCommandForm,
        BindingResult result)
    {
        exportCommandValidator.validate(exportCommandForm, result);
        if (result.hasErrors()) {
            return "export/command/edit";
        }

        ExportCommand exportCommand = new ExportCommand();
        exportCommandTranslator.dataToBusiness(exportCommandForm, exportCommand);
        exportCommandService.update(exportCommand);

        return "redirect:/export/command/list";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteExportCommand(
        @RequestParam("id") String id)
    {
        String result = "false";
        String error = "";
        JSONObject jsonObject = new JSONObject();
        try {
            exportCommandService.delete(Long.parseLong(id));
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
    @RequestMapping(value = "/parse", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String parseExportCommand(
        @RequestParam("id") String id)
    {
        String result = "false";
        String error = "";
        JSONObject jsonObject = new JSONObject();
        try {
            parseManager.parse(exportCommandService.getOne(Long.parseLong(id)));
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
