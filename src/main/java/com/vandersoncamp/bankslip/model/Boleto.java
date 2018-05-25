package com.vandersoncamp.bankslip.model;import javax.persistence.*;import javax.validation.constraints.NotNull;import javax.validation.constraints.Size;import java.math.BigDecimal;import java.time.LocalDate;@Entity@Table(name = "boletos")public class Boleto implements EntityID {    @Id    @NotNull(message = "{Boleto.id.NotNull}")    @Size(max = 36, message = "{Boleto.id.Size}")    @Column(name = "ID", length = 36)    private String id;    @NotNull(message = "{Boleto.due_date.NotNull}")    @Column(name = "DUE_DATE", columnDefinition = "DATE", nullable = false)    private LocalDate due_date;    @NotNull(message = "{Boleto.total_in_cents.NotNull}")    @Column(name = "TOTAL_IN_CENTS", nullable = false)    private BigDecimal total_in_cents;    @NotNull(message = "{Boleto.customer.NotNull}")    @Size(max = 255, message = "{Boleto.customer.Size}")    @Column(name = "CUSTOMER", length = 255, nullable = false)    private String customer;    @NotNull(message = "{Boleto.status.NotNull}")    @Column(name = "STATUS",length = 25, nullable = false)    @Enumerated(EnumType.STRING)    private Status status;    @Override    public String getId() {        return id;    }    public void setId(String id) {        this.id = id;    }    public LocalDate getDue_date() {        return due_date;    }    public void setDue_date(LocalDate due_date) {        this.due_date = due_date;    }    public BigDecimal getTotal_in_cents() {        return total_in_cents;    }    public void setTotal_in_cents(BigDecimal total_in_cents) {        this.total_in_cents = total_in_cents;    }    public String getCustomer() {        return customer;    }    public void setCustomer(String customer) {        this.customer = customer;    }    public Status getStatus() {        return status;    }    public void setStatus(Status status) {        this.status = status;    }}