package server.translator;

import org.springframework.stereotype.Service;
import server.entity.ExportCommand;
import server.form.ExportCommandForm;

@Service
public class ExportCommandTranslator
        extends CommonTranslator<ExportCommand, ExportCommandForm> {

    @Override
    public void businessToData(
        ExportCommand src,
        ExportCommandForm data)
    {
        data.setId(String.valueOf(src.getId()));
        data.setType(src.getType());
        data.setCommand(src.getCommand());
        data.setCommandArgs(src.getCommandArgs());
        data.setExternalUrl(src.getExternalUrl());
        data.setRequestUrl(src.getRequestUrl());
    }

    @Override
    public void dataToBusiness(
        ExportCommandForm data,
        ExportCommand src)
    {
        src.setId(Long.parseLong(data.getId()));
        src.setType(data.getType());
        src.setCommand(data.getCommand());
        src.setCommandArgs(data.getCommandArgs());
        src.setExternalUrl(data.getExternalUrl());
        src.setRequestUrl(data.getRequestUrl());
    }

}
