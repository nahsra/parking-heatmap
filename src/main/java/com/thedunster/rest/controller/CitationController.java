package com.thedunster.rest.controller;

import com.thedunster.jpa.entities.CitationEntity;
import com.thedunster.jpa.repository.CitationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/citation")
public class CitationController {
    private static final Logger log = LoggerFactory.getLogger(CitationController.class);

    private final int OFFSET_TO_NEXT_HOUR = 59 * 60 * 1000 + 59 * 1000 + 999;

    @Autowired
    CitationRepository citationRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CitationEntity> index(@RequestParam(value = "timestamp", required = false) String timestampParameter) {
        final String method = "index";
        Long timestamp = null;
        try {
            timestamp = Long.valueOf(timestampParameter);
        } catch (NumberFormatException e) {
            log.info("{} invalid number passed: {}", method, timestampParameter);
        }

        List<CitationEntity> citationEntities;
        log.info("{}: Request with timestamp: {}", method, timestamp);
        if (timestamp == null) {
            citationEntities = (List<CitationEntity>) citationRepository.findAll();
        } else {
            Timestamp startTime = new Timestamp(timestamp);
            Timestamp endTime = new Timestamp(timestamp + OFFSET_TO_NEXT_HOUR);
            log.info("{}: Time range search starting from {} until {}", method, startTime, endTime);
            citationEntities = citationRepository.findByRange(startTime, endTime);
        }
        return citationEntities;
    }

}
