package com.deeploma.reco.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deeploma.reco.domain.UserBehaviour;
import com.deeploma.reco.domain.UserMatchCountBehaviour;
import com.deeploma.reco.mongodao.MongoRepository;

@Component
public class AlgorithmControler {
	
	@Autowired 
	private RecommendationStorage matrix;
	
	@Autowired
	MongoRepository repo;
	
	@Scheduled(initialDelay = 3000, fixedRate = 10000)
	public void reloadMatrix() {
		matrix.recalculate(repo.findUsersMatches(), userBehaviours()); // TODO mozda paralelizovati
	}

	private Map<Long, UserMatchCountBehaviour> userBehaviours() {
		List<UserBehaviour>  userBehaviours = repo.findUserBehaviors();
		Map<Long , UserMatchCountBehaviour> result = new HashMap<Long, UserMatchCountBehaviour>();
		userBehaviours.stream().forEach(ub -> {
			UserMatchCountBehaviour temp = result.get(ub.getUserId());
			if (temp == null) {
				temp = new UserMatchCountBehaviour();
				temp.setUserId(ub.getUserId());				
			}
			if (temp.getMatchesCount().get(ub.getMatchId()) == null) {
				temp.getMatchesCount().put(ub.getMatchId(), new AtomicInteger(0));
			}
			temp.getMatchesCount().get(ub.getMatchId()).incrementAndGet();
			result.put(ub.getUserId(), temp);
		});
		return result;
	}

}
