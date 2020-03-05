package ${class.typePackage}.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import ftn.upp.sc.dto.common.${class.name}DTO;

public interface ${class.name}Controller {

<#list properties as property>
	<#if property.id == true>
	public ResponseEntity<List<${class.name}DTO>> get${class.name}();
	public ResponseEntity<${class.name}DTO> add${class.name}(${class.name}DTO ${class.name?uncap_first}DTO);
	public ResponseEntity<${class.name}DTO> get${class.name}(${property.type} ${property.name});
	public ResponseEntity<${class.name}DTO> delete(${property.type} ${property.name});
	public ResponseEntity<${class.name}DTO> edit(${class.name}DTO ${class.name?uncap_first}DTO);
	</#if>
</#list>


}