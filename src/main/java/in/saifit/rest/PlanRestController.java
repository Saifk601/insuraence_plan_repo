package in.saifit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.saifit.entities.Plan;
import in.saifit.service.planService;

@RestController
public class PlanRestController {
	
	@Autowired
	private planService plSer;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer , String>> planCategories(){
		
		Map<Integer, String> categories = plSer.getPlanCategories();
		return new ResponseEntity<>(categories , HttpStatus.OK);
		
	}
	@PostMapping("/plan")
	public ResponseEntity<String> SavePlan(@RequestBody Plan plan){
		
		String msg = "";
		boolean savePlan = plSer.savePlan(plan);
	    if(savePlan) {
	    	msg = "Plan save successfully";
	    }else {
	    	msg = "Plan are not Saved";
	    }
		return new ResponseEntity<>(msg , HttpStatus.CREATED);
	 }
	
	 @GetMapping("/plans")
     public ResponseEntity<List<Plan>> ViewAllPlan(){
		
		List<Plan> list = plSer.getAllPlan();
		return new ResponseEntity<>(list , HttpStatus.OK);
		
	}
	
	 @PutMapping("/plan/{planId}")
	 public ResponseEntity<Plan> EditPlan(@PathVariable Integer planId){
		 Plan planById = plSer.getPlanById(planId);
		 return new ResponseEntity<>(planById , HttpStatus.OK);
	 }
	 
	 @PutMapping("/plan")
	 public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		 String msg2 = "";
		 boolean updatePlan = plSer.updatePlan(plan);
		 if(updatePlan) {
			 msg2 = "plan  updated";
		 }else {
			 msg2 = "Plan Not Updated";
		 }
		 return new ResponseEntity<>(msg2 , HttpStatus.OK);
	 }
	 
	 @DeleteMapping("plans/{planId}")
	 public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		 String msg1 = "";
		 boolean planById = plSer.deletePlanById(planId);
		 
		 if(planById) {
			 msg1 = "Plan deleted successfully";
		 }else {
			 msg1 = "Plan are not delete";
		 }
		 
		 return new ResponseEntity<>(msg1 , HttpStatus.OK);
	 }
	 
	 @PutMapping("/status-change/{planId}/{status}")
	 public ResponseEntity<String> statusChange(@PathVariable Integer planId , @PathVariable String status){
		 String sta = "";
		 boolean statusChange = plSer.planStatusChange(planId, status);
		 if(statusChange) {
			 sta = "status changed";
		 }else {
			 sta = "status not changed";
		 }
		 
		 return new ResponseEntity<>(sta , HttpStatus.OK);
	 }
	 
	 

}
