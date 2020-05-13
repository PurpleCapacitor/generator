package ${class.typePackage}.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.${class.name}Converter;
import ftn.upp.sc.dto.${class.name}DTO;
import ftn.upp.sc.model.${class.name};
import ftn.upp.sc.repository.${class.name}Repository;

public class ${class.name}ServiceBase {
	
	@Autowired
	public ${class.name}Repository ${class.name?uncap_first}Repository;
	
	@Autowired
	public ${class.name}Converter ${class.name?uncap_first}Converter;
	
	public List<${class.name}> findAll() {
		return ${class.name?uncap_first}Repository.findAll();
	}
	
	<#list properties as property>
		<#if property.id>
	public ${class.name} findOne(${property.type} id) {
		Optional<${class.name}> ${class.name?uncap_first} = ${class.name?uncap_first}Repository.findById(id);
		return ${class.name?uncap_first}.get();
	}
	
	public ${class.name} save${class.name}(${class.name}DTO dto) {
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Converter.DtoToEntity(dto);
		return ${class.name?uncap_first}Repository.save(${class.name?uncap_first});
	}

	public ${class.name} delete${class.name}(${property.type} id) {
		${class.name} ${class.name?uncap_first} = this.findOne(id);
		if(${class.name?uncap_first} == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		${class.name?uncap_first}Repository.delete(${class.name?uncap_first});
		return ${class.name?uncap_first};
	}
		</#if>
	</#list>
}
