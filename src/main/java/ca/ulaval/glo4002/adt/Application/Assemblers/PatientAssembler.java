package ca.ulaval.glo4002.adt.Application.Assemblers;

import ca.ulaval.glo4002.adt.Application.Dtos.PatientDTO;
import ca.ulaval.glo4002.adt.Domain.Patient;

import java.util.ArrayList;
import java.util.Collection;

public class PatientAssembler implements DTOAssembler<PatientDTO, Patient>{
    @Override
    public PatientDTO writeDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO(
        patient.getId(),
        patient.getName(),
        patient.getStatus(),
        patient.getDepartment());
        return patientDTO;
    }

    @Override
    public Collection<PatientDTO> writeDTOCollection(Collection<Patient> patients) {
        Collection<PatientDTO> patientDTOs = new ArrayList<>();

        for (Patient patient: patients) {
            PatientDTO patientDTO = writeDTO(patient);
            patientDTOs.add(patientDTO);
        }

        return patientDTOs;
    }
}
