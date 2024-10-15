package in.saifit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.saifit.entities.Plan;

public interface PlanRepo extends JpaRepository<Plan , Integer> {

}
