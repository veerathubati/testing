package com.revolut.bankaccount.controller;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.InvalidIdException;
import com.revolut.bankaccount.exception.InvalidTransferException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Transaction;
import com.revolut.bankaccount.service.TransactionService;
import com.revolut.bankaccount.serviceImpl.TransactionServiceImpl;
import com.revolut.bankaccount.utils.ValidationUtils;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionEndpoints {

	private TransactionService transactionService = TransactionServiceImpl.getInstance();

	@POST
	@Path("/transfer/{transmitter}/{receiver}/{amount}")
	public Response transfer(@PathParam("transmitter") String transmitterId, @PathParam("receiver") String receiverId,
			@PathParam("amount") String amount) throws InvalidIdException, InvalidTransferException {

		long transmitterInfoId;
		long receiverInfoId;
		double money;

		try {
			transmitterInfoId = ValidationUtils.validateId(transmitterId);
			receiverInfoId = ValidationUtils.validateId(receiverId);
			money = ValidationUtils.validateTransferMoney(amount);
			Transaction transaction = new Transaction(transmitterInfoId, receiverInfoId, money);
			transactionService.transferMoney(transaction);
			return Response.ok().build();
		} catch (NoSuchAccountFoundException | BadRequestException | InsufficientFundsException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
