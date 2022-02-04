package com.fstg.taxeboisson.domaine.tauxTaxeBoissonTrim.add;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.UUID;

import com.fstg.taxeboisson.domaine.converter.MapPojo;
import com.fstg.taxeboisson.domaine.core.AbstractProcessImpl;
import com.fstg.taxeboisson.domaine.core.Result;
import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.domaine.utils.DateUtils;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonInfra;

public class TauxTaxeTrimAddProcessImpl extends AbstractProcessImpl<TauxTaxeTrimAddInput>
		implements TauxTaxeTrimAddProcess {

	private TauxTaxeBoissonInfra tauxTaxeBoissonInfra;
	private DateUtils dateUtils = new DateUtils();
	private MapPojo mapPojo = new MapPojo();

	public TauxTaxeTrimAddProcessImpl(TauxTaxeBoissonInfra tauxTaxeBoissonInfra) {
		this.tauxTaxeBoissonInfra = tauxTaxeBoissonInfra;
	}

	@Override
	public void validate(TauxTaxeTrimAddInput tauxTaxeTrimAddInput, Result result) throws ParseException {
		if (tauxTaxeTrimAddInput.getTarif() == BigDecimal.valueOf(0)) {
			result.addErrorMessage("taxeBoissonTrim.is_null");
		}
		LocalDate dateDebutApplication = dateUtils.dateToLocaleDate(tauxTaxeTrimAddInput.getDateDebutApplication());
		LocalDate dateFinApplication = dateUtils.dateToLocaleDate(tauxTaxeTrimAddInput.getDateFinApplication());
		if (dateUtils.leftGreaterThanRight(dateDebutApplication, dateFinApplication)) {
			result.addErrorMessage("date_not_correct");
		}
	}

	@Override
	public void run(TauxTaxeTrimAddInput tauxTaxeTrimAddInput, Result result) throws ParseException {
		TauxTaxeBoisson tauxTaxeBoisson = new TauxTaxeBoisson();
		String ref = UUID.randomUUID().toString();

		tauxTaxeBoisson.setRef(ref);
		tauxTaxeBoisson.setTarif(tauxTaxeTrimAddInput.getTarif());
		tauxTaxeBoisson.setDateDebutApplication(tauxTaxeTrimAddInput.getDateDebutApplication());
		tauxTaxeBoisson.setDateFinApplication(tauxTaxeTrimAddInput.getDateDebutApplication());

		TauxTaxeBoissonEntity tauxTaxeBoissonEntity = mapPojo
				.tauxTaxeBoissonPojototauxTaxeBoissonEntity(tauxTaxeBoisson);

		tauxTaxeBoissonInfra.save(tauxTaxeBoissonEntity);
		result.addInfoMessage(tauxTaxeBoissonInfra.getMessage("tauxTaxeBoisson.created"));

	}

}
