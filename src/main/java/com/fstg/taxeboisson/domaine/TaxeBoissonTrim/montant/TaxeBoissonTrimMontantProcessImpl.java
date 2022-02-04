package com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fstg.taxeboisson.domaine.converter.MapPojo;
import com.fstg.taxeboisson.domaine.core.AbstractProcessImpl;
import com.fstg.taxeboisson.domaine.core.Result;
import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonAnnuelle;
import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonTrim;
import com.fstg.taxeboisson.domaine.utils.DateUtils;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonRetardTrimEntity;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonTrimEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonInfra;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonRetardTrimInfra;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonAnnuelleInfra;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonTrimInfra;

import com.fstg.taxeboisson.infrastructure.required.feign.consumer.LocalFeignConsumer;
import com.fstg.taxeboisson.infrastructure.vo.LocaleVo;

public class TaxeBoissonTrimMontantProcessImpl extends AbstractProcessImpl<TaxeBoissonTrimMontantInput>
		implements TaxeBoissonTrimMontantProcess {
	private TaxeBoissonTrimInfra taxeBoissonTrimInfra;
	private TauxTaxeBoissonInfra tauxTaxeBoissonInfra;
	private TauxTaxeBoissonRetardTrimInfra tauxTaxeBoissonRetardTrimInfra;
	private DateUtils dateUtils = new DateUtils();
	MapPojo mapPojo = new MapPojo();
	private TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra;
	@Autowired
	private LocalFeignConsumer localFeign;

	public TaxeBoissonTrimMontantProcessImpl(TaxeBoissonTrimInfra taxeBoissonTrimInfra,
			TauxTaxeBoissonInfra tauxTaxeBoissonInfra, TauxTaxeBoissonRetardTrimInfra tauxTaxeBoissonRetardTrimInfra,
			TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra) {
		this.taxeBoissonTrimInfra = taxeBoissonTrimInfra;
		this.tauxTaxeBoissonInfra = tauxTaxeBoissonInfra;
		this.tauxTaxeBoissonRetardTrimInfra = tauxTaxeBoissonRetardTrimInfra;
		this.taxeBoissonAnnuelleInfra = taxeBoissonAnnuelleInfra;
	}

	@Override
	public void validate(TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput, Result result) {
		if (taxeBoissonTrimAddInput.getNumTrim() == 0 || taxeBoissonTrimAddInput.getYear() == 0
				|| taxeBoissonTrimAddInput.getChiffreAffaire() == null
				|| taxeBoissonTrimAddInput.getDatePaiement() == null || taxeBoissonTrimAddInput.getLocalRef() == null) {
			result.addErrorMessage("taxeBoissonTrim.is_null");
		}
		
		if(localFeign.findByRef(taxeBoissonTrimAddInput.getLocalRef()) == null) {
			result.addErrorMessage("local.notFound");
		}
	}

	@Override
	public void run(TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput, Result result) {
		BigDecimal totaleTaxeTrim;
		BigDecimal tarifTaxe = BigDecimal.valueOf(0);
		BigDecimal tarifTaxeRetardOneMonth = BigDecimal.valueOf(0);
		BigDecimal tarifTaxeRetardMoreThanOneMonth = BigDecimal.valueOf(0);
		BigDecimal mounthsLate = BigDecimal.valueOf(0);
		Boolean isPaymentLateOneOrMoreMounths = false;
		int isPaymentLate = 0;
		TauxTaxeBoisson tauxTaxeBoisson;
		TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrimEntity;
		TaxeBoissonAnnuelle taxeBoissonAnnuelle;
		TaxeBoissonTrimEntity taxeBoissonTrimEntity;

		// get all the "TauxTaxe"
		List<TauxTaxeBoissonEntity> tauxTaxeBoissons = tauxTaxeBoissonInfra.findAll();

		tauxTaxeBoisson = getValidTauxTaxeBoissonEntity(tauxTaxeBoissons, taxeBoissonTrimAddInput);

		LocalDate dateFinTrim = dateUtils.getLocaleDateWithNumTrim(taxeBoissonTrimAddInput.getNumTrim(),
				taxeBoissonTrimAddInput.getYear());
		LocalDate datePaiement = dateUtils.dateToLocaleDate(taxeBoissonTrimAddInput.getDatePaiement());

		isPaymentLate = isPaymentLate(taxeBoissonTrimAddInput, dateFinTrim, datePaiement);
		if (tauxTaxeBoisson != null) {
			tarifTaxe = tauxTaxeBoisson.getTarif();
		}

		tauxTaxeBoissonRetardTrimEntity = getValidTauxTaxeBoissonRetardTrimEntity(dateFinTrim);

		if (tauxTaxeBoissonRetardTrimEntity != null) {
			if (isPaymentLate == 1 || isPaymentLate == 2) {
				tarifTaxeRetardOneMonth = tauxTaxeBoissonRetardTrimEntity.getTarifPremierMoisRetard();
				isPaymentLateOneOrMoreMounths = true;
			}
			if (isPaymentLate == 2) {
				tarifTaxeRetardMoreThanOneMonth = tauxTaxeBoissonRetardTrimEntity.getTarifAutresMoisRetard();
				if (datePaiement.getYear() == dateFinTrim.getYear()) {
					mounthsLate = BigDecimal
							.valueOf(datePaiement.getMonth().getValue() - dateFinTrim.getMonth().getValue());

				} else {

					int initYear = datePaiement.getYear();
					while (initYear != dateFinTrim.getYear()) {
						LocalDate initDate = dateUtils.stringToLocaleDate("01-01-" + datePaiement.getYear());
						if (initYear == datePaiement.getYear()) {
							mounthsLate = mounthsLate.add(BigDecimal
									.valueOf(datePaiement.getMonth().getValue() - initDate.getMonth().getValue()));
						} else {
							mounthsLate = mounthsLate.add(BigDecimal.valueOf(12));
						}
						initYear--;
					}
				}

				isPaymentLateOneOrMoreMounths = true;
			}
		}

		totaleTaxeTrim = calculateTaxeTrim(taxeBoissonTrimAddInput, tarifTaxe, tarifTaxeRetardOneMonth,
				tarifTaxeRetardMoreThanOneMonth, mounthsLate);

		taxeBoissonTrimEntity = initTaxeBoissonTrim(taxeBoissonTrimAddInput, tauxTaxeBoisson,
				isPaymentLateOneOrMoreMounths, mounthsLate, totaleTaxeTrim, taxeBoissonTrimAddInput.getDatePaiement());
		// get the annual tax to set the total tax amount
		taxeBoissonAnnuelle = taxeBoissonAnnuelleInfra.findByLocalRefAndYear(taxeBoissonTrimAddInput.getLocalRef(),
				taxeBoissonTrimAddInput.getYear());

		saveOrUpdateAnnualTaxe(taxeBoissonAnnuelle, taxeBoissonTrimAddInput, totaleTaxeTrim);
		// save the trim tax

		int res = taxeBoissonTrimInfra.save(taxeBoissonTrimEntity);
		if (res == -1) {
			result.addInfoMessage(taxeBoissonTrimInfra.getMessage("taxeBoissonTrim.is_null"));
		} else {
			result.addInfoMessage(taxeBoissonTrimInfra.getMessage("taxeBoissonTrim.taxeBoissonTrim.created"));
		}

	}

	private TauxTaxeBoisson getValidTauxTaxeBoissonEntity(List<TauxTaxeBoissonEntity> tauxTaxeBoissons,
			TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput) {
		TauxTaxeBoisson tauxTaxeBoisson = new TauxTaxeBoisson();
		for (TauxTaxeBoissonEntity tauxTaxeBoissonEntity : tauxTaxeBoissons) {
			// search the tax percentage which is applicable for the specific trimestre
			// transform Date to LocaleDate
			LocalDate dateFinTrim = dateUtils.getLocaleDateWithNumTrim(taxeBoissonTrimAddInput.getNumTrim(),
					taxeBoissonTrimAddInput.getYear());

			LocalDate dateFinApplication = dateUtils.dateToLocaleDate(tauxTaxeBoissonEntity.getDateFinApplication());
			if (dateUtils.leftGreaterThanRight(dateFinApplication, dateFinTrim)) {
				BeanUtils.copyProperties(tauxTaxeBoissonEntity, tauxTaxeBoisson);
				// get the days between the end of trim and payment day
				return tauxTaxeBoisson;
			}
		}
		return null;
	}

	private TauxTaxeBoissonRetardTrimEntity getValidTauxTaxeBoissonRetardTrimEntity(LocalDate dateFinTrim) {
		List<TauxTaxeBoissonRetardTrimEntity> tauxTaxeBoissonRetardTrims = tauxTaxeBoissonRetardTrimInfra.findAll();
		for (TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrim : tauxTaxeBoissonRetardTrims) {
			LocalDate dateFinApplicationRetard = dateUtils
					.dateToLocaleDate(tauxTaxeBoissonRetardTrim.getDateFinApplication());
			if (dateUtils.leftGreaterThanRight(dateFinApplicationRetard, dateFinTrim)) {
				return tauxTaxeBoissonRetardTrim;
			}
		}
		return null;
	}

	private int isPaymentLate(TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput, LocalDate dateFinTrim,
			LocalDate datePaiement) {
		int isPaymentLate = 0;
		// get the days between the end of trim and payment day
		LocalDate nextDate = dateUtils.getLocaleDateWithMounth(taxeBoissonTrimAddInput.getNumTrim() * 3,
				taxeBoissonTrimAddInput.getYear());
		long days = dateUtils.getDaysBetween(dateFinTrim, datePaiement);
		long daysNextMount = dateUtils.getDays(nextDate);
		long daysNextTwoMounths = dateUtils.getDaysOfNextTwoMounths(taxeBoissonTrimAddInput.getNumTrim(),
				taxeBoissonTrimAddInput.getYear());
		// verify if payment is late or not (1 month or more)

		if (days > daysNextTwoMounths) {
			isPaymentLate = 2;
		} else if (days > daysNextMount) {
			isPaymentLate = 1;
		}
		return isPaymentLate;
	}

	private BigDecimal calculateTaxeTrim(TaxeBoissonTrimMontantInput taxeBoissonTrimMontantInput, BigDecimal tarifTaxe,
			BigDecimal tarifTaxeRetardOneMonth, BigDecimal tarifTaxeRetardMoreThanOneMonth, BigDecimal mounthsLate) {
		return taxeBoissonTrimMontantInput.getChiffreAffaire().multiply(tarifTaxe)
				.add(taxeBoissonTrimMontantInput.getChiffreAffaire().multiply(tarifTaxeRetardOneMonth))
				.add(taxeBoissonTrimMontantInput.getChiffreAffaire().multiply(tarifTaxeRetardMoreThanOneMonth)
						.multiply(mounthsLate));
	}

	private TaxeBoissonTrimEntity initTaxeBoissonTrim(TaxeBoissonTrimMontantInput taxeBoissonTrimMontantInput,
			TauxTaxeBoisson tauxTaxeBoisson, boolean isPaymentLate, BigDecimal mounthsLate, BigDecimal totaleTaxeTrim,
			Date datePaiement) {
		TaxeBoissonTrim taxeBoissonTrim = new TaxeBoissonTrim();
		String ref = UUID.randomUUID().toString();
		// prepare "taxeBoissonTrim" to be initialized with the appropriate values to
		// save it
		taxeBoissonTrim.setNumTrim(taxeBoissonTrimMontantInput.getNumTrim());
		taxeBoissonTrim.setYear(taxeBoissonTrimMontantInput.getYear());
        taxeBoissonTrim.setRef(ref);
		taxeBoissonTrim.setTauxTaxeBoisson(tauxTaxeBoisson);
		taxeBoissonTrim.setChiffreAffaire(taxeBoissonTrimMontantInput.getChiffreAffaire());
		taxeBoissonTrim.setLocalRef(taxeBoissonTrimMontantInput.getLocalRef());
		taxeBoissonTrim.setPaymentLate(isPaymentLate);
		taxeBoissonTrim.setNbrMoisRetard(mounthsLate);
		taxeBoissonTrim.setDatePaiement(datePaiement);
		// set the result of total tax
		taxeBoissonTrim.setMontantTotaleTaxeTrim(totaleTaxeTrim);

		return mapPojo.taxeBoissonTrimPojotoTaxeTaxeBoissonTrimEntity(taxeBoissonTrim);
	}

	private void saveOrUpdateAnnualTaxe(TaxeBoissonAnnuelle taxeBoissonAnnuelle,
			TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput, BigDecimal totaleTaxeTrim) {
		if (taxeBoissonAnnuelle != null) {
			taxeBoissonAnnuelle.setMontantTotaleTaxeAnnuelle(
					taxeBoissonAnnuelle.getMontantTotaleTaxeAnnuelle().add(totaleTaxeTrim));
			// update the annual tax
			taxeBoissonAnnuelleInfra.update(taxeBoissonAnnuelle);
		} else {
			taxeBoissonAnnuelle = new TaxeBoissonAnnuelle();
			String ref = UUID.randomUUID().toString();
			taxeBoissonAnnuelle.setRef(ref);
			taxeBoissonAnnuelle.setMontantTotaleTaxeAnnuelle(totaleTaxeTrim);
			taxeBoissonAnnuelle.setYear(taxeBoissonTrimAddInput.getYear());
			taxeBoissonAnnuelle.setLocalRef(taxeBoissonTrimAddInput.getLocalRef());
			taxeBoissonAnnuelleInfra.save(taxeBoissonAnnuelle);
		}
	}

}
