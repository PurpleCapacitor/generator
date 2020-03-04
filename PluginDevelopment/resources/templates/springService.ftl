package ${class.typePackage}.service;

import ${class.typePackage}.model.${class.name};
import java.util.List;

public interface ${class.name}Service {
	
	public List<${class.name}> findAll${class.name}s();
	public ${class.name} save${class.name}(${class.name} ${class.name?uncap_first}); 
	<#list properties as property> 
	<#if property.id == true>
	public ${class.name} findOne(${property.type} ${property.name}); 
	public ${class.name} delete${class.name}(${property.type} ${property.name}); 
	</#if>
	
	</#list>
}
