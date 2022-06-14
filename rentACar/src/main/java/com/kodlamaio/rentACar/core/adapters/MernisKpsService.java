package com.kodlamaio.rentACar.core.adapters;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserCheckService;
import com.kodlamaio.rentACar.entities.concretes.User;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisKpsService implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(User user) {
		try {
			KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

			boolean isValidUser = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(user.getNationality()),
					user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getBirthDate());
			return isValidUser;
		} catch (Exception e) {

			System.out.println("Giriş bilgileri doğru değil");
		}

		return false;
	}

}
