package com.gmail.genadyms.web.service;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.shared.dto.WardDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface WardServiceAsync {

    void getFreeWards(AsyncCallback<List<WardDTO>> callback);

}
