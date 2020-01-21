package virtualschoolClient.dao;

import java.util.ArrayList;
import java.util.List;

import common.*;
import vo.Curriculum;

public class CurriculumSendMessage {
	public List<Curriculum> findCurriculumBySchSemAnduID(String schSem, String uID, ClientSocket cs) {

		Message msg = new Message();
		msg.set_type(MessageType.FIND_CBSAID);
		String str = schSem + "#" + uID;
		msg.set_data(str);
		cs.sendMessage(msg);
		List<Curriculum> curList = new ArrayList<Curriculum>();
		curList = cs.receiveObject(curList);
		return curList;
	}

	public List<Curriculum> findCurriculumByuID(String uID, ClientSocket cs) {

		Message msg = new Message();
		msg.set_type(MessageType.FIND_CBID);
		String str = uID;
		msg.set_data(str);
		cs.sendMessage(msg);
		List<Curriculum> curList = new ArrayList<Curriculum>();
		curList = cs.receiveObject(curList);
		return curList;

	}

	public boolean insertCurriculum(String uID, String schSem, String course, ClientSocket cs) {

		Message msg = new Message();
		msg.set_type(MessageType.INSERT_CURRICULUM);
		String str = uID + "#" + schSem + "#" + course;
		msg.set_data(str);
		cs.sendMessage(msg);
		Message m = cs.receiveMessage();
		String a = m.get_data();
		if (a.equals("true")) {
			return true;
		} else
			return false;
	}

	public void deleteCurriculum(String uID, String schSem, String course, ClientSocket cs) {

		Message msg = new Message();
		msg.set_type(MessageType.DELETE_CURRICULUM);
		String str = uID + "#" + schSem + "#" + course;
		msg.set_data(str);
		cs.sendMessage(msg);
	}
}