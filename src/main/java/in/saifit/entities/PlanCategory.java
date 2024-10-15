package in.saifit.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PLAN_CATEGORY")
public class PlanCategory {
	
	 @Id
	 @GeneratedValue
	 @Column(name = "CATEGORY_ID")
	 private Integer CategoryId;
	 
	 @Column(name = "CATEGORY_NAME")
	 private String CategoryName;
	 
	 @Column(name = "ACTIVE_SW")
	 private Character ActiveSW;
	 
	 @Column(name = "CREATED_DATE" , updatable = false)
	 @CreationTimestamp
	 private LocalDate createdDate;
	 
	 @Column(name = "UPDATED_DATE" , insertable = false)
	 @UpdateTimestamp
	 private LocalDate updatedDate;
	 
	 @Column(name = "CREATED_BY")
	 private String createdBy;
	 
	 @Column(name = "UPDATED_BY")
	 private String updateBy;

}
