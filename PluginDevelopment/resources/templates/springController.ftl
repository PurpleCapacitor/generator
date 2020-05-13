package ftn.upp.sc.controller.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.${class.name}Converter;
import ftn.upp.sc.dto.${class.name}DTO;
import ftn.upp.sc.model.${class.name};
import ftn.upp.sc.service.${class.name}Service;

@RequestMapping(value="/${class.name?uncap_first}")
public class ${class.name}ControllerBase {

	@Autowired
	public ${class.name}Service ${class.name?uncap_first}Service;
	
	@Autowired
	public ${class.name}Converter ${class.name?uncap_first}Converter;
	
	@RequestMapping(value="get${class.name}s", method=RequestMethod.GET)
	public ResponseEntity<List<${class.name}DTO>> get${class.name}s(){
		List<${class.name}> ${class.name?uncap_first}s = ${class.name?uncap_first}Service.findAll();
		List<${class.name}DTO> ${class.name?uncap_first}sDTO = new ArrayList<${class.name}DTO>();
		for(${class.name} ${class.name?uncap_first} : ${class.name?uncap_first}s){
			${class.name?uncap_first}sDTO.add(${class.name?uncap_first}Converter.entityToDto(${class.name?uncap_first}));
		}
		return new ResponseEntity<>(${class.name?uncap_first}sDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<${class.name}DTO> add${class.name}(@RequestBody ${class.name}DTO ${class.name?uncap_first}DTO){
			
		${class.name} new${class.name} = ${class.name?uncap_first}Service.save${class.name}(${class.name?uncap_first}DTO);
		return new ResponseEntity<>(${class.name?uncap_first}Converter.entityToDto(new${class.name}), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	<#list properties as property>
		<#if property.id>
	public ResponseEntity<${class.name}DTO> get${class.name}(@PathVariable ${property.type} id) {
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Service.findOne(id);
		if (${class.name?uncap_first} == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(${class.name?uncap_first}Converter.entityToDto(${class.name?uncap_first}), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<${class.name}DTO> delete(@PathVariable ${property.type} id) {
		${class.name} deleted = ${class.name?uncap_first}Service.delete${class.name}(id);
		return new ResponseEntity<>(${class.name?uncap_first}Converter.entityToDto(deleted), HttpStatus.OK);
	}
		</#if>
	</#list>	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<${class.name}DTO> edit(@RequestBody ${class.name}DTO ${class.name?uncap_first}DTO) {
		${class.name} edited = ${class.name?uncap_first}Service.save${class.name}(${class.name?uncap_first}DTO);
		return new ResponseEntity<>(${class.name?uncap_first}Converter.entityToDto(edited), HttpStatus.OK);
	}
}
