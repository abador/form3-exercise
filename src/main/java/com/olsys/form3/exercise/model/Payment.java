package com.olsys.form3.exercise.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Document(collection = "payments")
@JsonPropertyOrder({ "type", "id", "version", "organisation_id", "attributes"})
public class Payment {

    @JsonCreator
    public Payment(@JsonProperty("type") String type,
                   @JsonProperty("version") String version,
                   @JsonProperty("organisation_id") UUID organisationId,
                   @JsonProperty("attributes") Attributes attributes) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.version = version;
        this.organisationId = organisationId;
        this.attributes = attributes;
    }

    @Id
    @NotNull
    @JsonProperty(access = READ_ONLY)
    private UUID id;

    @NotNull
    private String type;

    @NotNull
    private String version;

    @NotNull
    @Field("organisation_id")
    private UUID organisationId;

    @NotNull
    private Attributes attributes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("organisation_id")
    public UUID getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(UUID organisation) {
        this.organisationId = organisation;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
