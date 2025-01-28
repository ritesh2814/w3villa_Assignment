package in.w3villa.nitin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.w3villa.nitin.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
