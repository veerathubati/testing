package com.revolut.bankaccount.controller;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.revolut.bankaccount.exception.InvalidIdException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Account;
import com.revolut.bankaccount.service.AccountService;
import com.revolut.bankaccount.serviceImpl.AccountServiceImpl;
import com.revolut.bankaccount.utils.ValidationUtils;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountEndpoints {

	private AccountService accountService = AccountServiceImpl.getInstance();
	private Gson gson = new Gson();

	@POST
	@Path("/create/{id}/{amount}")
	public Response create(@PathParam("id") String id, @PathParam("amount") String initialAmount)
			throws InvalidIdException {
		long numericId;
		double numericAmount;

		try {
			numericId = ValidationUtils.validateId(id);
			numericAmount = ValidationUtils.validateInitialAmount(initialAmount);
			accountService.createAccount(numericId, numericAmount);
			return Response.status(Response.Status.OK).build();
		} catch (BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path("/{id}")
	public Response getAccount(@PathParam("id") String id) throws InvalidIdException {
		long numericId;
		Account result;

		try {
			numericId = ValidationUtils.validateId(id);
			result = accountService.getAccount(numericId);
			return Response.status(Response.Status.OK).entity(gson.toJson(result)).build();
		} catch (BadRequestException | NoSuchAccountFoundException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
