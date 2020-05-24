package ${class.typePackage}.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Table(name="${class.name?lower_case}")
@Entity
${class.visibility} class ${class.name} { 
 
<#list properties as property>
	<#if property.id == true>
	@Id
	<#if property.name == "id">
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	</#if>
	${property.visibility} ${property.type} ${property.name};
	<#else>
	<#if property.upper == 1 >   
		<#if property.association == true>
			<#if property.oppositeUpper == -1 >
	@ManyToOne(fetch=FetchType.${property.fetchType})
			<#else>
	@OneToOne
			</#if>
		<#else>
			<#if property.name == "failedLoginAttempts">
	@JsonIgnore
			<#else>
	@Column(name="${property.name?lower_case}")
			</#if>
		</#if>
		<#if property.name == "failedLoginAttempts">
	private int failedLoginAttempts = 0;
		<#else>
	${property.visibility} ${property.type} ${property.name};
		</#if>
    <#elseif property.upper == -1 > 
    	<#if property.association == true>
    	<#if property.oppositeUpper == -1>
    @ManyToMany(fetch=FetchType.${property.fetchType})
    	<#else>
    @OneToMany(fetch=FetchType.${property.fetchType})    
    	</#if>
	${property.visibility} List<${property.type}> ${property.name} = new ArrayList<${property.type}>();
		</#if>
		
    <#else>   
    	<#list 1..property.upper as i>
  	${property.visibility} ${property.type} ${property.name}${i};
		</#list>  
    </#if> 
    </#if>
        
</#list>

	public ${class.name}() {}

<#list properties as property>
	<#if property.name == "failedLoginAttempts">
	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}


	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}


	public void incrementFailedLoginAttempts() {
		++this.failedLoginAttempts;
	}
	<#else>
	<#if property.upper == 1 >   
  	public ${property.type} get${property.name?cap_first}(){
    	return ${property.name};
  	}
  
  	public void set${property.name?cap_first}(${property.type} ${property.name}){
       	this.${property.name} = ${property.name};	
	}
      
    <#elseif property.upper == -1 >
    	<#if property.association == false>
   	public Set<${property.type}> get${property.name?cap_first}(){
     	return ${property.name};
    }
      
   	public void set${property.name?cap_first}( Set<${property.type}> ${property.name}){
     	this.${property.name} = ${property.name};
   	}
   		<#else>
   	public void add${property.name?cap_first}(${property.type} ${property.type?lower_case}){
		this.${property.name}.add(${property.type?lower_case});
	}
	
	public void remove${property.name?cap_first}(${property.type} ${property.type?lower_case}){
		${property.name}.remove(${property.type?lower_case});
	}
	
	public List<${property.type}> get${property.name?cap_first}(){
     	return ${property.name};
    }
      
   	public void set${property.name?cap_first}( List<${property.type}> ${property.name}){
     	this.${property.name} = ${property.name};
   	}
	
      	</#if>
    <#else>   
    	<#list 1..property.upper as i>
   	public ${property.type} get${property.name?cap_first}${i}(){
   		return ${property.name}${i};
   	}
      
  	public void set${property.name?cap_first}${i}(${property.type} ${property.name}${i}){
     	this.${property.name}${i} = ${property.name}${i};
  	}
            
		</#list>  
    </#if>
    </#if>   
</#list>

}