package in.w3villa.nitin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.w3villa.nitin.model.Employee;
import in.w3villa.nitin.model.UserDetails;

public interface IEmployeeService {

	// save the record
	void saveRecord(Employee employee);

	// display the record
	Page<Employee> displayAllRecords(Pageable pageable);

	// delete the record
	void deleteRecord(Integer eid);

	// get the record
	Employee findEmployee(Integer eid);

	// save the Userdetails
	void saveuser(UserDetails userdetail);

}
