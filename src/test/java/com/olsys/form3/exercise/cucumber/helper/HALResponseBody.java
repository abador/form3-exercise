package com.olsys.form3.exercise.cucumber.helper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * HALResponseBody extends original API resource with HAL extra fields.
 * It prevents from loading additional message converters and is simple.
 *
 * @param <T>
 */
public class HALResponseBody<T> implements Serializable {

    @JsonProperty("_embedded")
    private T embedded;

    public T getEmbedded() {
        return embedded;
    }

    public void setEmbedded(T embedded) {
        this.embedded = embedded;
    }
}
