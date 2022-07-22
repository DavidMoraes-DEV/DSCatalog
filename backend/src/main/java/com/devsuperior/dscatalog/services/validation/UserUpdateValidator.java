package com.devsuperior.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;

//Parametriza o tipo da annotation(UserInsertValid) e o tipo da classe que vai receber esse annotation(UserInsertDTO)
public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
	@Autowired
	private HttpServletRequest request; //Guarda as informações da requisição, então a partir dele que conseguimos pegar o código passado na requisição UPDATE
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserUpdateValid ann) { //Coloca-se aqui alguma lógica para quando for inicializar um objeto(Nesse projeto ficará em branco)
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) { //Esse método testa se o objeto (UserInsertDTO) é válido ou não
		
		@SuppressWarnings("unchecked") //Annotation para suprimir o sublinhado amarelo de WARNING
		var uriVars = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); //Pega um mapa com os atributos da URL, ou seja as variáveis da URI
		long userId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		//Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		User user = repository.findByEmail(dto.getEmail());
		if(user != null && userId != user.getId()) { //Teste para ver se o email vindo do UserInsertDTO já existe no banco e caso existir insere a mensagem de erro que o email já existe e(&&) verifica também se o id é o mesmo pois se não for é porque esta tentando atualizar informações de outro usuário
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for (FieldMessage e : list) { //Insere na lista de erros do beanValidation os erros se gerados
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}