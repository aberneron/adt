package ca.ulaval.glo4002.adt.Persistence;

import ca.ulaval.glo4002.adt.Domain.Patient;
import ca.ulaval.glo4002.adt.Application.EntityManagerProvider;

import java.util.Collection;

import javax.persistence.EntityManager;

public class HibernatePatientRepository {

	public void persist(Patient patient) {
		EntityManager em = EntityManagerProvider.getEntityManager();
		em.persist(patient);
	}

	@SuppressWarnings("unchecked")
	public Collection<Patient> findAll() {
		EntityManager em = EntityManagerProvider.getEntityManager();
		return (Collection<Patient>) em.createQuery("from Patient").getResultList();
	}

	public Patient findById(int patientId) {
		EntityManager em = EntityManagerProvider.getEntityManager();
		return em.find(Patient.class, patientId);
	}

}
