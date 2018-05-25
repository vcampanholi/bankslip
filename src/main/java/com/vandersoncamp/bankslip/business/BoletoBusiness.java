package com.vandersoncamp.bankslip.business;import com.vandersoncamp.bankslip.business.validate.BoletoValidate;import com.vandersoncamp.bankslip.model.Boleto;import com.vandersoncamp.bankslip.service.BoletoService;import javax.enterprise.context.RequestScoped;import javax.inject.Inject;import java.util.List;@RequestScopedpublic class BoletoBusiness {    @Inject    private BoletoService service;    @Inject    private BoletoValidate boletoValidate;    public Boleto find(Long id) {        return service.find(id);    }    public List<Boleto> findAll(String filterField, String filterData, String order) {        return service.findAll(filterField, filterData, order);    }    public void create(Boleto boleto) {        boletoValidate.validate(boleto);        service.create(boleto);    }    public void update(Boleto boleto) {        boletoValidate.validate(boleto);        service.update(boleto);    }    public void delete(Long id) {        service.delete(id);    }}