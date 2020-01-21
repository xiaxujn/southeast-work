package virtualschoolClient.bz;

import java.util.ArrayList;
import java.util.List;

import vo.Student;

public class PageControllerImpl implements PageController{

	private List<Student> bigList = new ArrayList<Student>();
	private List<Student> smallList = new ArrayList<Student>();
	private static int currentPageIndex = 1;
	private int perPage = 8;
	private int pageCount;
	private int recordCount;
	
	public PageControllerImpl(List<Student> stu, int current) {
		this.bigList = stu;
		this.recordCount = bigList.size();
		if (recordCount % perPage == 0) {
			this.pageCount = recordCount / perPage;
		} else {
			this.pageCount = recordCount / perPage + 1;
		}
		
		PageControllerImpl.currentPageIndex = current;
	}
 
	public void setCurretPageIndex(int page) {
		currentPageIndex = page;
	}
	
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	@Override
	public List<Student> getCurrentPage() {
		// TODO Auto-generated method stub
		return selectedList();
	}

	@Override
	public List<Student> nextPage() {
		// TODO Auto-generated method stub
		if (currentPageIndex < pageCount) {
			currentPageIndex++;
		}
		return selectedList();
	}

	@Override
	public List<Student> previousPage() {
		// TODO Auto-generated method stub
		if (currentPageIndex > 1) {
			currentPageIndex--;
		}
		return selectedList();
	}

	@Override
	public List<Student> selectedList() {
		// TODO Auto-generated method stub
		for (int i = (currentPageIndex - 1) * perPage; i < currentPageIndex * perPage && i < recordCount; i++) {
			smallList.add(bigList.get(i));
		}
		return smallList;
	}
	
}
