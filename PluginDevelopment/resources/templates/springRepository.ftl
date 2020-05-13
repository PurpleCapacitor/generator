package ${class.typePackage}.repository.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${class.typePackage}.model.${class.name};

<#list properties as property>
		<#if property.id>
public interface ${class.name}RepositoryBase extends JpaRepository<${class.name}, ${property.type?cap_first}> {

}		
		</#if>
</#list>
