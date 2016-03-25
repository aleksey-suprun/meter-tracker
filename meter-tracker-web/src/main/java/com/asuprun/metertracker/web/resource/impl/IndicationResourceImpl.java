package com.asuprun.metertracker.web.resource.impl;

import com.asuprun.metertracker.web.dto.DigitDto;
import com.asuprun.metertracker.web.dto.IndicationDto;
import com.asuprun.metertracker.web.resource.IndicationResource;
import com.asuprun.metertracker.web.sevice.IndicationService;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class IndicationResourceImpl implements IndicationResource {

    private static Logger logger = LoggerFactory.getLogger(IndicationResource.class);

    @Autowired
    private IndicationService indicationService;

    @Override
    public List<IndicationDto> get(Long meterId, Boolean unrecognized) {
        return IndicationDto.toDtos(indicationService.findByTypeAndState(meterId, unrecognized));
    }

    @Override
    public Response get(long id) {
        return indicationService.findById(id)
                .map(i -> Response.ok(IndicationDto.toDto(i)).build())
                .orElseThrow(() -> {
                    logger.debug("No indication found for id: {}", id);
                    return new NoSuchElementException("No such indication");
                });
    }

    @Override
    public Response delete(long id) {
        indicationService.delete(id);
        return Response.noContent().build();
    }

    @Override
    public List<DigitDto> digits(long id) {
        return DigitDto.toDtos(indicationService.recognize(id));
    }

    @Override
    public Response digits(long id, List<DigitDto> digits) {
        indicationService.saveDigits(id, DigitDto.fromDtos(digits));
        return Response.noContent().build();
    }

    @Override
    public Response upload(Attachment attachment, Long meterId) throws Exception {
        try (InputStream inputStream = attachment.getDataHandler().getInputStream()) {
            return Response.status(Response.Status.CREATED)
                    .entity(IndicationDto.toDto(indicationService
                            .parseAndSaveIndication(IOUtils.toByteArray(inputStream), meterId)))
                    .build();
        }
    }
}
