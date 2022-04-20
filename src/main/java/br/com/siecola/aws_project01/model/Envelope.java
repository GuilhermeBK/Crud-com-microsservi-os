package br.com.siecola.aws_project01.model;

public class Envelope {

    private Enum eventType;
    private String data;

    public Enum getEventType() {
        return eventType;
    }

    public void setEventType(Enum eventType) {
        this.eventType = eventType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
