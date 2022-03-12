package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import com.example.rail_e_ticket_api.payload.TicketDto;
import com.example.rail_e_ticket_api.entity.Ticket;
import com.example.rail_e_ticket_api.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.*;

@RequiredArgsConstructor
@Service
public class TicketService implements BaseService<TicketDto> {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse add(TicketDto ticketDto) {
        checkTicket(ticketDto.getNumSeat());
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        ticketRepository.save(ticket);
        return new ApiResponse(SUCCESS,200,ticket);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()){
            return new ApiResponse(SUCCESS,200,optionalTicket.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<Ticket> ticketList = ticketRepository.findAll();
        return new ApiResponse(SUCCESS,200,ticketList);
    }

    @Override
    public ApiResponse updateById(Long id, TicketDto ticketDto) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()){
            Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
            ticket.setId(id);
            ticketRepository.save(ticket);
            return new ApiResponse(SUCCESS,200,ticket);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()){
            ticketRepository.deleteById(optionalTicket.get().getId());
            return new ApiResponse(SUCCESS,200,optionalTicket.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    private void checkTicket(byte numSeat) {
        Optional<Ticket> optionalTicket = ticketRepository.findByNumSeat(numSeat);
        if (optionalTicket.isPresent())
            throw new CustomException(ALREADY_EXIST);
    }
}
