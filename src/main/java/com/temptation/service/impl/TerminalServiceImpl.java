package com.temptation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.dao.TerminalMapper;
import com.temptation.pojo.Terminal;
import com.temptation.service.TerminalService;
@Service
public class TerminalServiceImpl implements TerminalService{
	@Autowired
	private TerminalMapper terminalMapper;

	@Override
	public List<Terminal> queryTerminals( Map<String, Object> map) {
		// TODO Auto-generated method stub
		return terminalMapper.queryTerminals(map);
	}

	@Override
	public List<Terminal> queryTerminalsByDevicestauts(Integer stage) {
		return terminalMapper.queryTerminalsByDevicestauts(stage);
	}

	@Override
	public Terminal queryInfoById(Integer tid) {
		return terminalMapper.queryInfoById(tid);
	}

	@Override
	public Integer deleteTerminalById(Integer id) {
		return terminalMapper.deleteTerminalById(id);
	}

	@Override
	public Integer addTerminal(Terminal terminal) {
		return terminalMapper.addTerminal(terminal);
	}

	@Override
	public Integer updateTerminalById(Terminal terminal) {
		return terminalMapper.updateTerminalById(terminal);
	}

	@Override
	public List<Terminal> queryTerminalsByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return terminalMapper.queryTerminalsByParentId(parentId);
	}

	@Override
	public List<Terminal> selectTerminalsByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return terminalMapper.selectTerminalsByParentId(parentId);
	}
	
	@Override
	public Long queryTerminalIdByMid(Long mid) {
		// TODO Auto-generated method stub
		return terminalMapper.queryTerminalIdByMid(mid);
	}

	@Override
	public void updateManagementIsHT(Integer mid,Integer IsHTValue) {
		// TODO Auto-generated method stub
		terminalMapper.updateManagementIsHT(mid,IsHTValue);
	}

	@Override
	public void updateGroupIsHT(Integer gid, Integer IsHTValue) {
		terminalMapper.updateGroupIsHT(gid, IsHTValue);
		
	}
}
