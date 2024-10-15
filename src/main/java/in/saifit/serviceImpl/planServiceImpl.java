package in.saifit.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

import in.saifit.entities.Plan;
import in.saifit.entities.PlanCategory;
import in.saifit.repo.PlanRepo;
import in.saifit.repo.planCategoryRepo;
import in.saifit.service.planService;

@Service
public class planServiceImpl implements planService {
    
	@Autowired
	private planCategoryRepo planCategory;
	
	@Autowired
	private PlanRepo planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {

        List<PlanCategory> categories = planCategory.findAll();
        Map<Integer , String> planCategory = new HashMap<>();
        categories.forEach(category ->
              planCategory.put(category.getCategoryId(), category.getCategoryName())
        		);
        
		return planCategory;
	}

	@Override
	public boolean savePlan(Plan plan) {
		
		Plan save = planRepo.save(plan);
		/*if(save.getPlanId() != null) {
			return true;
		}else {
			return false;
		}*/
		//return save.getPlanId() != null ? true : false;
		return save.getPlanId() != null;
	}

	@Override
	public List<Plan> getAllPlan() {
		
		/*List<Plan> list = planRepo.findAll();
		return list;*/
		
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		
		 Optional<Plan> ById = planRepo.findById(planId);
		 if(ById.isPresent()) {
			 return ById.get();
		 }
		  return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		
	    planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		
		
		//problem with this is apply change those attribute are set then those varible are not set automatically set null  
		/*Plan p = new Plan();
		p.setPlanId(planId);
		p.setActiveSW(status);
		planRepo.save(p);*/
		
		Optional<Plan> byId = planRepo.findById(planId);
		if(byId.isPresent()) {
			Plan plan = byId.get();
			plan.setActiveSW(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

}
