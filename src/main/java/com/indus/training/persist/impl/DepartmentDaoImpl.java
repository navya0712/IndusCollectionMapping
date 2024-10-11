package com.indus.training.persist.impl;

import javax.persistence.EntityManager;

import com.indus.training.persist.dao.IDepartmentDao;
import com.indus.training.persist.entity.Department;
import com.indus.training.persist.entity.Employee;
import com.indus.training.persist.exceptions.DepartmentDaoException;
import com.indus.training.persist.util.EntityManagerUtil;

public class DepartmentDaoImpl implements IDepartmentDao {

	@Override
	public Boolean insertDepartment(Department department) throws DepartmentDaoException {
		if (department == null) {
			throw new NullPointerException("Department object cannot be null");
		}

		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
				em.persist(department);
				em.getTransaction().commit();
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}
		return status;
	}

	@Override
	public Department fetchDepartmentById(Integer departmentId) throws DepartmentDaoException {
		Department department = null;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			department = em.find(Department.class, departmentId);
		} finally {
			closeEntityManager(em);
		}

		return department;
	}

	@Override
	public Boolean updateDepartmentById(Integer departmentId, Department department) throws DepartmentDaoException {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Department deptObj = em.find(Department.class, departmentId);
			if (deptObj != null) {
				deptObj.setDepartmentName(department.getDepartmentName());

				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	@Override
	public Boolean deleteDepartmentById(Integer departmentId) throws DepartmentDaoException {
		boolean status = false;
		EntityManager em = EntityManagerUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			Department department = em.find(Department.class, departmentId);
			if (department != null) {
				em.remove(department);
				em.getTransaction().commit();
				status = true;
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			handleException(em, e);
		} finally {
			closeEntityManager(em);
		}

		return status;
	}

	/**
	 * Rolls back the transaction if an exception occurs.
	 * 
	 * @param em the EntityManager involved in the transaction
	 * @param e  the exception that occurred
	 */
	private void handleException(EntityManager em, Exception e) {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		
		}
	}

	/**
	 * Closes the EntityManager.
	 * 
	 * @param em the EntityManager to be closed
	 */
	private void closeEntityManager(EntityManager em) {
		if (em != null) {
			em.close();
		}
	}

}
