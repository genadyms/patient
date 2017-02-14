package com.gmail.genadyms.server.service;

import com.gmail.genadyms.server.dataaccess.WardDao;
import com.gmail.genadyms.server.datamodel.Ward;
import com.gmail.genadyms.shared.dto.WardDTO;
import com.gmail.genadyms.web.service.WardService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;


public class WardServiceImpl extends RemoteServiceServlet implements WardService {

   private final WardDao wardDao;

    public WardServiceImpl() {

        wardDao = new WardDao();
    }

    @Override
    public List<WardDTO> getFreeWards() {
        List<Ward> wardsDao = wardDao.getAll();
        System.out.println("________________________"+wardsDao.size());
        List<WardDTO> result = new ArrayList<>(wardsDao.size());
        for(Ward wardDao : wardsDao) {
            WardDTO wardDTO = new WardDTO();
            wardDTO.setId(wardDao.getId());
            wardDTO.setNumberWard(wardDao.getNumber());
            result.add(wardDTO);
        }
        System.out.println("________________________+DTO___"+result.size());
        return result;
    }
}
