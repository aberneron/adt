package ca.ulaval.glo4002.adt.Application;

import ca.ulaval.glo4002.adt.Application.Assemblers.DTOAssembler;
import ca.ulaval.glo4002.adt.Application.Assemblers.PatientAssembler;
import ca.ulaval.glo4002.adt.Application.Dtos.PatientDTO;
import ca.ulaval.glo4002.adt.Domain.Patient;
import ca.ulaval.glo4002.adt.Persistence.HibernatePatientRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

public class PatientService {
    private EntityManager entityManager;
    private DTOAssembler patientAssembler;

    public PatientService() {
        initializeServiceLocator();
        initializeEntityManager();
        patientAssembler = new PatientAssembler();
    }

    private void initializeServiceLocator() {
        ServiceLocator.INSTANCE.register(HibernatePatientRepository.class, new HibernatePatientRepository());
        ServiceLocator.INSTANCE.register(EntityManagerFactory.class, Persistence.createEntityManagerFactory("adt"));
    }

    private void initializeEntityManager() {
        EntityManagerFactory entityManagerFactory = ServiceLocator.INSTANCE.resolve(EntityManagerFactory.class);
        this.entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider.setEntityManager(this.entityManager);
    }

    public void fillDatabase() {
        HibernatePatientRepository patientRepository = ServiceLocator.INSTANCE
                .resolve(HibernatePatientRepository.class);

        this.entityManager.getTransaction().begin();

        Patient pierre = new Patient("Pierre");
        patientRepository.persist(pierre);

        Patient marie = new Patient("Marie");
        marie.moveToDepartment("ICU");
        patientRepository.persist(marie);

        this.entityManager.getTransaction().commit();
    }

    public Collection<PatientDTO> retrievePatients() {
        HibernatePatientRepository patientRepository = ServiceLocator.INSTANCE
                .resolve(HibernatePatientRepository.class);

        return this.patientAssembler.writeDTOCollection(patientRepository.findAll());
    }

    public void movePatient(int patientId, String newDepartment) {
        HibernatePatientRepository patientRepository = ServiceLocator.INSTANCE
                .resolve(HibernatePatientRepository.class);

        this.entityManager.getTransaction().begin();
        Patient patient = patientRepository.findById(patientId);
        patient.moveToDepartment(newDepartment);
        patientRepository.persist(patient);
        this.entityManager.getTransaction().commit();
    }

    public void dischargePatient(int patientId) {
        HibernatePatientRepository patientRepository = ServiceLocator.INSTANCE
                .resolve(HibernatePatientRepository.class);

        this.entityManager.getTransaction().begin();
        Patient patient = patientRepository.findById(patientId);
        patient.discharge();
        patientRepository.persist(patient);
        this.entityManager.getTransaction().commit();
    }
}
