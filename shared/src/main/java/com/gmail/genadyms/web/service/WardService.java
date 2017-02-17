package com.gmail.genadyms.web.service;

import com.gmail.genadyms.shared.dto.WardDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Set;

@RemoteServiceRelativePath("ward")
public interface WardService extends RemoteService {

    Set<WardDTO> getFreeWards();

}
