package com.restDemo.apiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class Payload<T> {
    private List<T> data;
    private Cursor cursor;
    private transient String message;

    public List<T> getData() {
        return this.data;
    }

    public Cursor getCursor(){
        return this.cursor;
    }

    public Payload<T> setCursor(Cursor cursor){
        this.cursor=cursor;
        return this;
    }

    public Payload<T> addObject(T object) {
        if (this.getData() == null) {
            this.data = new ArrayList();
        }

        this.getData().add(object);
        return this;
    }

    public Payload<T> addObjects(List<T> objects) {
        if (this.getData() == null) {
            this.data = new ArrayList();
        }

        this.getData().addAll(objects);
        return this;
    }

    public Payload<T> addMessage(String message) {
        this.setMessage(message);
        return this;
    }
}
