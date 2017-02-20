package com.gmail.genadyms.server.service;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.dataaccess.WardDao;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.server.datamodel.Ward;
import com.gmail.genadyms.shared.dto.WardDTO;
import com.gmail.genadyms.web.service.WardService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WardServiceImpl extends RemoteServiceServlet implements WardService {

    private final WardDao daoWard;

    public WardServiceImpl() {
        daoWard = new WardDao();
    }

    @Override
    public Set<WardDTO> getFreeWards() {
        Set<WardDTO> outputWards = new HashSet();
        List<Ward> freeWardsDao = daoWard.findFreeWards();
        for (Ward crnt : freeWardsDao) {
            WardDTO dtoWard = new WardDTO();
            dtoWard.setNumberWard(crnt.getNumber());
            outputWards.add(dtoWard);
        }
        return outputWards;
    }
}
