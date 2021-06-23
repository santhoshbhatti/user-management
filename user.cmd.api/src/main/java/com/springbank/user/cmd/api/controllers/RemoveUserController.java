package com.springbank.user.cmd.api.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.springbank.user.core.dto.BaseResponse;

@RestController
@RequestMapping("/api/v1/removeUser")
public class RemoveUserController {
	
	private CommandGateway commandGateway;

	@Autowired
	public RemoveUserController(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}
	@PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponse> removeUser(@PathVariable("id") String id){
		try {
			RemoveUserCommand command = new RemoveUserCommand(id);
			commandGateway.send(command);
		}catch(Exception e) {
			var safeErrorMessage = "Error while processing delete user request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<BaseResponse>(new BaseResponse("user deleted successfully - "+id), HttpStatus.OK);
	}

}
