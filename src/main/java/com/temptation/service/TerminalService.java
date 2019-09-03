package com.temptation.service;

import java.util.List;
import java.util.Map;

import com.temptation.pojo.Terminal;

public interface TerminalService {
	//终端列表
		List<Terminal> queryTerminals(Map<String, Object> map);

	//运行状态终端列表
	List<Terminal> queryTerminalsByDevicestauts(Integer stage);

	//根据id查询终端
	Terminal queryInfoById(Integer tid);

	//根据id删除终端
	Integer deleteTerminalById(Integer id);

	//新增终端
	Integer addTerminal(Terminal terminal);

	//根据id修改终端
	Integer updateTerminalById(Terminal terminal);
	
	List<Terminal> queryTerminalsByParentId(Long parentId);
	
	List<Terminal> selectTerminalsByParentId(Long parentId);
	
	Long queryTerminalIdByMid(Long mid);
	
	void updateManagementIsHT(Integer mid,Integer IsHTValue);
	
	void updateGroupIsHT(Integer gid,Integer IsHTValue);
}
