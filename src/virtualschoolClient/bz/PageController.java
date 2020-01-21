package virtualschoolClient.bz;

import java.util.List;

import vo.Student;

public interface PageController {

	public List<Student> getCurrentPage();
	public List<Student> nextPage();
	public List<Student> previousPage();
	public List<Student> selectedList();
}
