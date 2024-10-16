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

import in.saifit.constants.AppConstants;
import in.saifit.entities.Plan;
import in.saifit.properties.AppProperties;
import in.saifit.service.PlanService;

@RestController
public class PlanRestController {
	
	private PlanService plSer;
	
	private Map<String , String> messages ;
	
	public PlanRestController(PlanService plSer , AppProperties appProps) {
		
		this.plSer = plSer;
		this.messages = appProps.getMessages();
		
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer , String>> planCategories(){
		
		Map<Integer, String> categories = plSer.getPlanCategories();
		return new ResponseEntity<>(categories , HttpStatus.OK);
		
	}
	@PostMapping("/plan")
	public ResponseEntity<String> SavePlan(@RequestBody Plan plan){
		
		String msg = AppConstants.EMPTY_STR;
	
		boolean savePlan = plSer.savePlan(plan);
	    if(savePlan) {
	    	msg = messages.get(AppConstants.PLAN_SAVE_SUCC);
	    }else {
	    	msg = messages.get(AppConstants.PLAN_NOT_SAVE);
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
		 String msg2 = AppConstants.EMPTY_STR;
		 
		 boolean updatePlan = plSer.updatePlan(plan);
		 if(updatePlan) {
			 msg2 = messages.get(AppConstants.PLAN_UPDATED);
		 }else {
			 msg2 = messages.get(AppConstants.PLAN_NOT_UPDATED);
		 }
		 return new ResponseEntity<>(msg2 , HttpStatus.OK);
	 }
	 
	 @DeleteMapping("plans/{planId}")
	 public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		 String msg1 = AppConstants.EMPTY_STR;
		 
		 boolean planById = plSer.deletePlanById(planId);
		 
		 if(planById) {
			 msg1 = messages.get(AppConstants.PLAN_DELETE_SUCC);
		 }else {
			 msg1 = messages.get(AppConstants.PLAN_NOT_DELETE);
		 }
		 
		 return new ResponseEntity<>(msg1 , HttpStatus.OK);
	 }
	 
	 @PutMapping("/status-change/{planId}/{status}")
	 public ResponseEntity<String> statusChange(@PathVariable Integer planId , @PathVariable String status){
		 String sta = AppConstants.EMPTY_STR;
		 
		 boolean statusChange = plSer.planStatusChange(planId, status);
		 if(statusChange) {
			 sta = messages.get(AppConstants.STATUS_CHANGE);
		 }else {
			 sta = messages.get(AppConstants.STATUS_NOT_CHANGED);
		 }
		 
		 return new ResponseEntity<>(sta , HttpStatus.OK);
	 }
	 
	 

}
