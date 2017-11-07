package ca.ulaval.glo4002.adt.Application.Dtos;

import ca.ulaval.glo4002.adt.Domain.PatientStatus;

public class PatientDTO {
    private int id;
    private String name;
    private PatientStatus status;
    private String department;

    public PatientDTO(int id, String name, PatientStatus status, String department) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public String getDepartment() {
        return department;
    }
}
