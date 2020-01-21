package virtualschoolClient.dao;

import java.util.ArrayList;
import java.util.List;

import common.*;
import vo.Courses;
import vo.Curriculum;

public class CoursesSendMessage {
	public List<Courses> findCourseByCourseName(String courseName, ClientSocket cs) {
		Message msg = new Message();
		msg.set_type(MessageType.FIND_CBC);
		String str = courseName;
		msg.set_data(str);
		cs.sendMessage(msg);
		List<Courses> couList = new ArrayList<Courses>();
		couList = cs.receiveObject(couList);
		return couList;
	}

	public List<Courses> findCourseByTeacherName(String teacherName, ClientSocket cs) {
		Message msg = new Message();
		msg.set_type(MessageType.FIND_CBT);
		String str = teacherName;
		msg.set_data(str);
		cs.sendMessage(msg);
		List<Courses> couList = new ArrayList<Courses>();
		couList = cs.receiveObject(couList);
		return couList;
	}

	public Courses findCourseByCourseNameAndSchSem(String courseName, String schSem, ClientSocket cs) {
		Message msg = new Message();
		msg.set_type(MessageType.FIND_CBCAS);
		String str = courseName + "#" + schSem;
		msg.set_data(str);
		cs.sendMessage(msg);
		Courses course = new Courses();
		course = cs.receiveObject(course);
		return course;
	}

	public List<Courses> findCourseBySchSemAndGrade(String schSem, String grade, ClientSocket cs) {

		Message msg = new Message();
		msg.set_type(MessageType.FIND_CBSAD);
		String str = schSem + "#" + grade;
		msg.set_data(str);
		cs.sendMessage(msg);
		List<Courses> couList = new ArrayList<Courses>();
		couList = cs.receiveObject(couList);
		return couList;
	}

	public List<Courses> getCourseListMessage(ClientSocket cs) {

		Message msg = new Message();
		msg.set_type(MessageType.GET_ALLCOURSE);
		String str = null;
		msg.set_data(str);
		cs.sendMessage(msg);
		List<Courses> couList = new ArrayList<Courses>();
		couList = cs.receiveObject(couList);
		return couList;
	}

	public boolean insertCourse(String schSem, String course, String grade_temp, String teacher, String weeks,
			String time, String place, String credit, ClientSocket cs) {

		Message msg = new Message();
		msg.set_type(MessageType.INSERT_COURSE);
		String str = schSem + "#" + course + "#" + grade_temp + "#" + teacher + "#" + weeks + "#" + time + "#" + place
				+ "#" + credit;
		msg.set_data(str);
		cs.sendMessage(msg);
		Message m = cs.receiveMessage();
		String a = m.get_data();
		if (a.equals("true")) {
			return true;
		} else
			return false;
	}
}