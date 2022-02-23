package com.example.rail_e_ticket_api.ticket_car_owner.service;

import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import com.example.rail_e_ticket_api.ticket_car_owner.dto.TicketDto;
import com.example.rail_e_ticket_api.ticket_car_owner.entity.Owner;
import com.example.rail_e_ticket_api.ticket_car_owner.entity.Ticket;
import com.example.rail_e_ticket_api.ticket_car_owner.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.rail_e_ticket_api.constants.ResponseConstants.*;

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
