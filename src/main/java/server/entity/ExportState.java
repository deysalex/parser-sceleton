package server.entity;

public enum ExportState
{
    NEW(0),
    ERROR(1),
    EXPORTED(2);

    private int value;

    ExportState(int value) {
        this.value = value;
    }
}