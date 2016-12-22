package controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import service.HelloService;
import util.Page;

import com.jfinal.core.Controller;

public class HelloController extends Controller {

	public void addUser() {

		String userName = getPara("userName");
		String password = getPara("password");
		Record user = new Record().set("name", userName).set("password", password);

		HelloService.addUser(user);
		renderText("��ӳɹ�");
	}

	public void deleteUser() {

		String userName = getPara("userName");
		String password = getPara("password");

		HelloService.deleteUser(userName, password);
		renderText("ɾ���ɹ�");
	}
	
	public void findUser() {

		String userName = getPara("userName");

		Record record = HelloService.findUser(userName);
		renderText(""+record.get("password")+"");
	}
	
	public void updateUser() {

		String userName = getPara("userName");
		String password = getPara("password");

		HelloService.updateUser(userName, password);
		renderText("�޸ĳɹ�");
	}
	
	public void IofoList(){

		// ��ȡҳ�����  < pageIndex >ҳ������ֵ
		int pageIndex = getParaToInt("pageIndex");

		// ��ȡ������
		int count = HelloService.getCount();

		Page page = new Page(Page.pageSize, pageIndex, count);
		
		List<Record> list = HelloService.get(page);
		Record pageInfo = new Record();

		pageInfo.set("count", page.getCount()).set("pageIndex", page.getPageIndex()).set("pageCount", page.getPageCount());

		// ���� JSON  <list>��ǰҳ���list;
		// <pageInfo>ҳ����Ϣ: <count>list����,<pageCount>ҳ������,<pageIndex>��ǰҳ������ 
		renderJson(new Record().set("list", list).set("pageInfo", pageInfo));

		}
	
}