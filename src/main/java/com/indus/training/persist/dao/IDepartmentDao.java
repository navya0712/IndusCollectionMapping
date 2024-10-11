package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Department;
import com.indus.training.persist.exceptions.DepartmentDaoException;

/**
 * Interface for CRUD operations related to Department entities. Provides
 * methods for adding, retrieving, updating, and deleting departments.
 */
public interface IDepartmentDao {

	/**
	 * Adds a new department.
	 * 
	 * @param deptObj The department to be added.
	 * @return True if the department was successfully added, false otherwise.
	 * @throws DepartmentDaoException If there is an issue with the operation.
	 */
	public Boolean insertDepartment(Department department) throws DepartmentDaoException;

	/**
	 * Retrieves a department by its ID.
	 * 
	 * @param deptId The ID of the department to retrieve.
	 * @return The department with the specified ID, or null if not found.
	 * @throws DepartmentDaoException If there is an issue with the operation.
	 */
	public Department fetchDepartmentById(Integer departmentId) throws DepartmentDaoException;

	/**
	 * Updates the name of a department identified by its ID.
	 * 
	 * @param deptId   The ID of the department to update.
	 * @param deptName The new name of the department.
	 * @return True if the update was successful, false otherwise.
	 * @throws DepartmentDaoException If there is an issue with the operation.
	 */
	public Boolean updateDepartmentById(Integer departmentId, Department department) throws DepartmentDaoException;


	/**
	 * Deletes a department by its ID.
	 * 
	 * @param deptId The ID of the department to delete.
	 * @return True if the deletion was successful, false otherwise.
	 * @throws DepartmentDaoException If there is an issue with the operation.
	 */
	public Boolean deleteDepartmentById(Integer departmentId) throws DepartmentDaoException;
}
