package server.entity;

public enum ExportCommandState
{
    NEW(0),
    ERROR(1),
    PROGRESS(2),
    OK(3);

    private int value;

    ExportCommandState (int value) {
        this.value = value;
    }
}