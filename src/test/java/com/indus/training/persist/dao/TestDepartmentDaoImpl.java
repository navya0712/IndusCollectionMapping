package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Department;
import com.indus.training.persist.entity.Employee;
import com.indus.training.persist.exceptions.DepartmentDaoException;
import com.indus.training.persist.impl.DepartmentDaoImpl;

import junit.framework.TestCase;

public class TestDepartmentDaoImpl extends TestCase {

	private DepartmentDaoImpl departmentDao = null;

	protected void setUp() throws Exception {
		departmentDao = new DepartmentDaoImpl();
	}

	protected void tearDown() throws Exception {
		departmentDao = null;
	}

	public void testInsertDepartment() {
		try {
			Department department = new Department();
			department.setDepartmentId(1011);
			department.setDepartmentName("HR");

			Employee emp1 = new Employee();
			emp1.setEmployeeId(123456);
			emp1.setEmployeeName("Alice");

			Employee emp2 = new Employee();
			emp2.setEmployeeId(123459);
			emp2.setEmployeeName("Bob");

			department.addEmployee(emp1);
			department.addEmployee(emp2);

			boolean result = departmentDao.insertDepartment(department);
			assertTrue("Failed to insert department with employees", result);
		} catch (DepartmentDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	public void testFetchDepartmentById() {
		try {
			Department fetchedDepartment = departmentDao.fetchDepartmentById(1011);
			assertNotNull(fetchedDepartment);
			assertEquals("HR", fetchedDepartment.getDepartmentName());
		} catch (DepartmentDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	public void testUpdateDepartmentById() {
		try {
			Integer departmentId = 1011;
			Department department = departmentDao.fetchDepartmentById(departmentId);

			// Step 2: Modify the department details
			String newDepartmentName = "Updated Department Name";
			department.setDepartmentName(newDepartmentName);

			// Step 3: Persist the updated department
			departmentDao.updateDepartmentById(departmentId, department);

			// Step 4: Retrieve the department again to verify the update
			Department updatedDepartment = departmentDao.fetchDepartmentById(departmentId);

			// Step 5: Use assertions to verify the update
			assertNotNull(updatedDepartment);
			assertEquals(newDepartmentName, updatedDepartment.getDepartmentName());
		} catch (DepartmentDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	public void testDeleteDepartmentById() {
		try {
			boolean result = departmentDao.deleteDepartmentById(1011);
			assertTrue("Failed to insert department with employees", result);
		} catch (DepartmentDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}

	}

}
