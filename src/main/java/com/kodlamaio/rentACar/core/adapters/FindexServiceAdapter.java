package com.kodlamaio.rentACar.core.adapters;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.FindexService;

@Service
public class FindexServiceAdapter implements FindexService {
	HashMap<String, Integer> findexScores;

	@Override
	public int findexScore(String nationality) {
		Random random = new Random();
		findexScores = new HashMap<String, Integer>();
		int findexScore = random.nextInt(1900);
		findexScores.put(nationality, findexScore);
		System.out.println(findexScore);
		return findexScore;
	}

}
