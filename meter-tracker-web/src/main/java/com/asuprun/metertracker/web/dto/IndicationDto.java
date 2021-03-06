package com.asuprun.metertracker.web.dto;

import com.asuprun.metertracker.web.domain.Indication;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IndicationDto {

    private long id;

    @NotNull
    @PositiveOrZero(message = "Value must be greater or equals to 0")
    private Double value;
    private Date uploaded;
    private Date created;
    private String meterName;
    private Integer consumption;
    private String originalImageUrl;
    private String indicationImageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public String getOriginalImageUrl() {
        return originalImageUrl;
    }

    public void setOriginalImageUrl(String originalImageUrl) {
        this.originalImageUrl = originalImageUrl;
    }

    public String getIndicationImageUrl() {
        return indicationImageUrl;
    }

    public void setIndicationImageUrl(String indicationImageUrl) {
        this.indicationImageUrl = indicationImageUrl;
    }

    public static IndicationDto toDto(Indication indication) {
        if (indication == null) {
            return null;
        }
        IndicationDto dto = new IndicationDto();
        dto.id = indication.getId();
        dto.value = indication.getValue();
        dto.uploaded = indication.getOriginalImageInfo().getUploadedAt();
        dto.created = indication.getCreatedAt();
        dto.meterName = indication.getMeter().getName();
        dto.consumption = indication.getConsumption();
        dto.indicationImageUrl = indication.getIndicationImageInfo().getUrl();
        dto.originalImageUrl = indication.getOriginalImageInfo().getUrl();
        return dto;
    }

    public static Optional<Indication> fromDto(IndicationDto dto) {
        return Optional.ofNullable(dto).map(d -> {
            Indication indication = new Indication();
            indication.setId(d.id);
            indication.setValue(d.value);
            indication.setConsumption(d.consumption);
            return indication;
        });
    }

    public static List<IndicationDto> toDtos(List<Indication> indications) {
        return indications.stream().map(IndicationDto::toDto).collect(Collectors.toList());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private long id;
        private double value;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withValue(double value) {
            this.value = value;
            return this;
        }

        public IndicationDto build() {
            IndicationDto dto = new IndicationDto();
            dto.setId(id);
            dto.setValue(value);
            return dto;
        }
    }
}
