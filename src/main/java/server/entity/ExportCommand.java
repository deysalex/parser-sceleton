package server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Calendar;

@Entity
@Table(name = "export_command")
public class ExportCommand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "command")
    private String command;

    @Column(name = "command_args")
    private String commandArgs;

    @Column(name = "external_url")
    private String externalUrl;

    @Column(name = "request_url")
    private String requestUrl;

    @Column(name = "state")
    private ExportCommandState state = ExportCommandState.NEW;

    @Column(name = "u_date")
    private Calendar updateDate;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommandArgs() {
        return commandArgs;
    }

    public void setCommandArgs(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExportCommandState getState() {
        return state;
    }

    public void setState(ExportCommandState state) {
        this.state = state;
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }
}
