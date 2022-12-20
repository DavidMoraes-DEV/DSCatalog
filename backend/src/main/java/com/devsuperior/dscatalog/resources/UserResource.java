package com.devsuperior.dscatalog.resources;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.components.PasswordResetToken;
import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.services.UserService;
import com.devsuperior.dscatalog.services.exceptions.InvalidPasswordResetTokenException;

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${url.frontend}")
	private String urlFrontend;

	@Value("${spring.mail.username}")
	private String emailFrom;

	@GetMapping
	public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pageable,
			@RequestParam(value = "firstName", defaultValue = "") String firstName) {

		Page<UserDTO> page = service.findAllPaged(firstName.trim(), pageable);
		return ResponseEntity.ok().body(page);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		
		UserDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
		
		UserDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();

		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {

		UserDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/forgot-password")
	public ResponseEntity<PasswordResetToken> forgotPassword(@RequestBody UserUpdateDTO newEmail)
			throws UnsupportedEncodingException {

		PasswordResetToken tokenReset = new PasswordResetToken(RandomString.make(45));
		try {
			service.updateResetPassword(tokenReset.getToken(), newEmail.getEmail());

			String resetPasswordLink = urlFrontend + "/admin/auth/reset-password?token=" + tokenReset.getToken();

			sendEmail(newEmail.getEmail(), resetPasswordLink);
		} catch (UnsupportedEncodingException | MessagingException e) {
			throw new UnsupportedEncodingException("Não foi possível enviar o email, codificação não suportada");
		}

		return ResponseEntity.ok().body(tokenReset);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<UserDTO> resetPassword(@RequestParam(value = "token", defaultValue = "") String token,
			@RequestBody UserUpdateDTO newPassword) {

		User user = service.getPasswordResetToken(token);
		if (user == null) {
			throw new InvalidPasswordResetTokenException("Token para resetar a senha é Inválido.");
		}

		service.updatePassword(user, newPassword.getPassword());

		return ResponseEntity.noContent().build();
	}

	private void sendEmail(String email, String resetPasswordLink)
			throws UnsupportedEncodingException, MessagingException {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(emailFrom, "Suporte DSCatalog");
		helper.setTo(email);

		String subject = "Link para redefir a sua senha";
		String content = "<p> Olá, </p>" + "<p>Você solicitou a redefinição de sua senha no site DSCatalog.</p>"
				+ "<p>Clink no link abaixo para alterar a sua s" + "enha:</p>" + "<p><b><a href=\"" + resetPasswordLink
				+ "\">Mudar minha senha</a><b></p>";

		helper.setSubject(subject);
		helper.setText(content, true);

		mailSender.send(message);
	}

}
