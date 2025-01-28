package in.w3villa.nitin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.w3villa.nitin.exception.EmployeeNotFoundException;
import in.w3villa.nitin.model.Employee;
import in.w3villa.nitin.model.UserDetails;
import in.w3villa.nitin.repository.IEmployeeRepository;
import in.w3villa.nitin.repository.IUserDetails;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository repo;

	@Autowired
	private IUserDetails repo1;


	@Override
	public void saveRecord(Employee employee) {
		repo.save(employee);
	}

	@Override
	public Page<Employee> displayAllRecords(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public void deleteRecord(Integer eid) {
		repo.delete(
				repo.findById(eid).orElseThrow(() -> new EmployeeNotFoundException("record not found for deletion")));
	}

	@Override
	public Employee findEmployee(Integer eid) {
		return repo.findById(eid).get();
	}

	@Override
	public void saveuser(UserDetails userdetail) {
		repo1.save(userdetail);

	}





	//	@Override
	//	public UserDetails saveUserRecord(UserDetails userDetails) {
	//		
	//		return repo1.save(userDetails);
	//	}




}
