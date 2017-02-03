package com.gmail.genadyms.server.service;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.shared.PatientDTO;
import com.gmail.genadyms.shared.PatientDetails;
import com.gmail.genadyms.web.PatientsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class PatientsServiceImpl extends RemoteServiceServlet implements PatientsService {

	// private static final String[] contactsFirstNameData = new String[] {
	// "Hollie", "Emerson", "Healy", "Brigitte",
	// "Elba", "Claudio", "Dena", "Christina", "Gail", "Orville", "Rae",
	// "Mildred", "Candice", "Louise", "Emilio",
	// "Geneva", "Heriberto", "Bulrush", "Abigail", "Chad", "Terry", "Bell" };
	//
	// private final String[] contactsLastNameData = new String[] { "Voss",
	// "Milton", "Colette", "Cobb", "Lockhart",
	// "Engle", "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
	// "Carson", "Kelchner", "Hutchinson",
	// "Underwood", "Rush", "Bouchard", "Louis", "Andrews", "English", "Snedden"
	// };
	//
	// private final String[] contactsEmailData = new String[] {
	// "mark@example.com", "hollie@example.com",
	// "boticario@example.com", "emerson@example.com", "healy@example.com",
	// "brigitte@example.com",
	// "elba@example.com", "claudio@example.com", "dena@example.com",
	// "brasilsp@example.com", "parker@example.com",
	// "derbvktqsr@example.com", "qetlyxxogg@example.com",
	// "antenas_sul@example.com", "cblake@example.com",
	// "gailh@example.com", "orville@example.com", "post_master@example.com",
	// "rchilders@example.com",
	// "buster@example.com", "user31065@example.com", "ftsgeolbx@example.com" };

	private final HashMap<String, PatientDTO> patients = new HashMap<String, PatientDTO>();

	public PatientsServiceImpl() {
		initPatients();
	}

	//
	private void initPatients() {
		PatientDao pd = new PatientDao();
		List<Patient> patientsDB = pd.getAll();
		for (Patient patient : patientsDB) {
			PatientDTO patientDto = new PatientDTO(String.valueOf(patient.getId()), patient.getFirstName(),
					patient.getLastName(), patient.getHomeAddress());
			patients.put(patientDto.getId(), patientDto);
		}
	}

	@Override
	public PatientDTO addPatient(PatientDTO contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deletePatient(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PatientDetails> deletePatients(ArrayList<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PatientDetails> getPatientDetails() {
		ArrayList<PatientDetails> contactDetails = new ArrayList();
		Iterator<String> it = patients.keySet().iterator();
		while(it.hasNext()) {
			String id = it.next();
			PatientDTO patient = patients.get(id);
			PatientDetails pdet = new PatientDetails(id, patient.getFullName());
			contactDetails.add(pdet);
		}
		return contactDetails;
	}

	@Override
	public PatientDTO getPatient(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientDTO updatePatient(PatientDTO contact) {
		// TODO Auto-generated method stub
		return null;
	}

}
