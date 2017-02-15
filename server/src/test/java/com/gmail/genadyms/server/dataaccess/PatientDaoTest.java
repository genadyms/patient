package com.gmail.genadyms.server.dataaccess;

import org.junit.Test;

import com.gmail.genadyms.server.datamodel.Ward;
import com.gmail.genadyms.server.datamodel.Patient;

import java.util.Date;

import org.junit.Assert;

public class PatientDaoTest extends WardDaoTest {
    private PatientDao dao = new PatientDao();

    @Test
    public void testSave() {
        Patient patient = saveMockPatient();
        Assert.assertNotNull(patient.getId());
        clearDb(patient);
    }

    private void clearDb(Patient patient) {
        Ward bp = patient.getWard();
        dao.delete(patient);
        clearMockWard(bp);
    }

    @Test
    public void testUpdate() {
        Patient patient = saveMockPatient();
        String changeFirstName = "change-firstname";
        patient.setFirstName(changeFirstName);
        dao.update(patient);
        Assert.assertEquals(changeFirstName, patient.getFirstName());
        clearDb(patient);
    }

    @Test
    public void testUpdateWard() {
        Patient patient = saveMockPatient();
        Ward oldWard = patient.getWard();
        Ward ward = saveMockWard(34, 25);
        Assert.assertNotNull(ward);
        Assert.assertNotEquals(ward.getId(), patient.getWard().getId());
        patient.setWard(ward);
        dao.update(patient);
        Assert.assertEquals(ward.getId(), patient.getWard().getId());
        clearDb(patient);
        clearMockWard(oldWard);
    }

    private Patient saveMockPatient(Integer numberWard, Integer countBeds) {
        Patient p = new Patient();
        p.setFirstName("first-name-" + String.valueOf(Math.random()));
        p.setLastName("last-name-" + String.valueOf(Math.random()));
        p.setAddress(("home-address-" + String.valueOf(Math.random())));
        p.setDiagnosis("diagnosis");
        p.setComingDate(new Date());
        Ward bedPlace = saveMockWard(numberWard, countBeds);
        p.setWard(bedPlace);
        dao.insert(p);
        return p;
    }

    private Patient saveMockPatient() {
        return saveMockPatient(33, 5);
    }

}
