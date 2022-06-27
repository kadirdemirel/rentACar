package com.kodlamaio.rentACar.core.adapters;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserCheckService;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisKpsService implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(IndividualCustomer individualCustomer) {
		try {
			KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

			boolean isValidUser = kpsPublicSoapProxy.TCKimlikNoDogrula(
					Long.parseLong(individualCustomer.getNationality()),
					individualCustomer.getFirstName().toUpperCase(), individualCustomer.getLastName().toUpperCase(),
					individualCustomer.getBirthDate());
			return isValidUser;
		} catch (Exception e) {

			System.out.println("Giriş bilgileri doğru değil");
		}

		return false;
	}

}
