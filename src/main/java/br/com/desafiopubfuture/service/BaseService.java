package br.com.desafiopubfuture.service;

import org.modelmapper.ModelMapper;

public abstract class BaseService {
    protected final ModelMapper modelMapper = new ModelMapper();
}
