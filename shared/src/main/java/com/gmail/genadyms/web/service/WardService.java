package com.gmail.genadyms.web.service;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.shared.dto.WardDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created by mgm on 14.2.17.
 */
@RemoteServiceRelativePath("ward")
public interface WardService extends RemoteService {

    List<WardDTO> getFreeWards();

}
