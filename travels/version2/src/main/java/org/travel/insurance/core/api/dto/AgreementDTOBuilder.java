package org.travel.insurance.core.api.dto;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

public class AgreementDTOBuilder {

    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;
    private String country;
    private List<String> selectedRisks;
    private List<PersonDTO> persons;
    private BigDecimal agreementPremium;

    private AgreementDTOBuilder(){}

    public static AgreementDTOBuilder createBuilder(){
        return new AgreementDTOBuilder();
    }

    public AgreementDTO build(){
        var agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(agreementDateFrom);
        agreement.setAgreementDateTo(agreementDateTo);
        agreement.setCountry(country);
        agreement.setSelectedRisks(selectedRisks);
        agreement.setPersons(persons);
        agreement.setAgreementPremium(agreementPremium);

        return agreement;
    }

    public AgreementDTOBuilder withAgreementDateFrom(LocalDate agreementDateFrom) {
        this.agreementDateFrom = agreementDateFrom;
        return this;
    }

    public AgreementDTOBuilder withAgreementDateTo(LocalDate agreementDateTo) {
        this.agreementDateTo = agreementDateTo;
        return this;
    }

    public AgreementDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AgreementDTOBuilder withSelectedRisks(List<String> selectedRisks) {
        this.selectedRisks = selectedRisks;
        return this;
    }

    public AgreementDTOBuilder withPersons(List<PersonDTO> persons) {
        this.persons = persons;
        return this;
    }

    public AgreementDTOBuilder withAgreementPremium(BigDecimal agreementPremium) {
        this.agreementPremium = agreementPremium;
        return this;
    }

}
