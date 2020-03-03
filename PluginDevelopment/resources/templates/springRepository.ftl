package ${class.typePackage}.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${class.typePackage}.model.${class.name};

@Repository
public interface ${class.name}Repository extends JpaRepository<${class.name}, Long> {

	<#list properties as property>
		<#if property.upper == 1>
			<#if property.association == false> 
	List<${class.name}> findBy${property.name?cap_first}(${property.type} ${property.name});
			<#elseif property.association == true>
	List<${class.name}> findBy${property.type}Id(Long id);
			</#if>
		</#if>
 	</#list>

}