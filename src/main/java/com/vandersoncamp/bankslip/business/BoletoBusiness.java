package com.vandersoncamp.bankslip.business;import com.vandersoncamp.bankslip.business.validate.BoletoStatusValidate;import com.vandersoncamp.bankslip.business.validate.BoletoValidate;import com.vandersoncamp.bankslip.model.Boleto;import com.vandersoncamp.bankslip.service.BoletoService;import javax.enterprise.context.RequestScoped;import javax.inject.Inject;import java.math.BigDecimal;import java.time.LocalDate;import java.time.temporal.ChronoUnit;import java.util.List;import java.util.UUID;@RequestScopedpublic class BoletoBusiness {    @Inject    private BoletoService service;    @Inject    private BoletoValidate boletoValidate;    @Inject    private BoletoStatusValidate boletoStatusValidate;    private static final Integer CEM = 100;    public Boleto find(String id) {        BigDecimal valorAtualizado;        Boleto boleto = service.find(id);        valorAtualizado = calculaValorBoleto(boleto);        boleto.setTotal_in_cents(valorAtualizado);        return boleto;    }    public List<Boleto> findAll(String filterField, String filterData, String order) {        return service.findAll(filterField, filterData, order);    }    public void create(Boleto boleto) {        boleto.setId(generateHashId());        boletoValidate.validate(boleto);        service.create(boleto);    }    public void update(String id, Boleto boleto) {        Boleto boletoSave = service.find(id);        boletoValidate.validate(boletoSave);        boletoStatusValidate.validate(boletoSave, boleto.getStatus());        boletoSave.setStatus(boleto.getStatus());        service.update(boletoSave);    }    private String generateHashId() {        String identificador;        do {            identificador = UUID.randomUUID().toString().substring(0, 36);        } while (service.existsIdentificador(identificador));        return identificador;    }    private BigDecimal calculaValorBoleto(Boleto boleto) {        BigDecimal valorJuros = BigDecimal.ZERO;        if (boleto.getDue_date().toLocalDate().isBefore(LocalDate.now())) {            valorJuros = getValorMulta(boleto);        }        return boleto.getTotal_in_cents().add(valorJuros);    }    private BigDecimal getValorMulta(Boleto boleto) {        BigDecimal valorBoleto = boleto.getTotal_in_cents();        if (getdiferencaDias(boleto.getDue_date().toLocalDate()) > 10) {            return valorBoleto.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(CEM)));        }        return valorBoleto.multiply(BigDecimal.valueOf(0.5).divide(BigDecimal.valueOf(CEM)));    }    private Long getdiferencaDias(LocalDate dataVencimento) {        return ChronoUnit.DAYS.between(dataVencimento, LocalDate.now());    }}