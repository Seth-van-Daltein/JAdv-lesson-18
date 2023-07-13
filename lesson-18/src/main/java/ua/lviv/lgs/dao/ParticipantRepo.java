package ua.lviv.lgs.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.domain.Participant;

@Repository
public class ParticipantRepo {

	private List<Participant> participants = new ArrayList<>();

	@PostConstruct
	public void init() {
		Participant participant1 = new Participant();
		participant1.setId(1);
		participant1.setName("Hooligan1");
		participant1.setEmail("hooligan1@gmail.com");
		participant1.setLevel("L1");
		participant1.setPrimarySkill("Java");

		Participant participant2 = new Participant();
		participant2.setId(2);
		participant2.setName("Hooligan2");
		participant2.setEmail("hooligan2@gmail.com");
		participant2.setLevel("L2");
		participant2.setPrimarySkill("HTML&CSS");

		Participant participant3 = new Participant();
		participant3.setId(3);
		participant3.setName("Hooligan3");
		participant3.setEmail("hooligan3@gmail.com");
		participant3.setLevel("L3");
		participant3.setPrimarySkill("MySQL");

		participants.add(participant1);
		participants.add(participant2);
		participants.add(participant3);
	}

	public List<Participant> findAllParticipants() {
		return participants;
	}

	public Participant findOne(Integer id) {
		return participants.stream().filter(participant -> participant.getId() == id).findFirst().orElse(null);
	}

	public void save(Participant participant) {
		Participant participantToUpdate = null;

		if (participant.getId() != null) {
			participantToUpdate = findOne(participant.getId());
			int participantIndex = participants.indexOf(participantToUpdate);
			participantToUpdate.setName(participant.getName());
			participantToUpdate.setEmail(participant.getEmail());
			participantToUpdate.setLevel(participant.getLevel());
			participantToUpdate.setPrimarySkill(participant.getPrimarySkill());
			participants.set(participantIndex, participantToUpdate);
		} else {
			// save
			participant.setId(participants.size()+1);
			participants.add(participant);
		}
	}

	public void delete(int id) {
		Iterator<Participant> iter = participants.iterator();
		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				iter.remove();
			}
		}
	}

}