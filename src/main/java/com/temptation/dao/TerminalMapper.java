package com.temptation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.temptation.pojo.Terminal;

@Repository
public interface TerminalMapper {
	//终端列表
	List<Terminal> queryTerminals(@Param(value = "map") Map<String, Object> map);

	//运行状态终端列表
	List<Terminal> queryTerminalsByDevicestauts(@Param("stage") Integer stage);

	//根据id查询终端
	Terminal queryInfoById(Integer tid);

	//根据id删除终端
	Integer deleteTerminalById(Integer id);

	//新增终端
	Integer addTerminal(Terminal terminal);

	//根据id修改终端
	Integer updateTerminalById(Terminal terminal);
	
	//根据父id查询所有子终端
	List<Terminal> queryTerminalsByParentId(Long parentId);
	
	//根据父id查询分组子终端
	List<Terminal> selectTerminalsByParentId(Long parentId);
	
	Long queryTerminalIdByMid(Long mid);
	
	void updateManagementIsHT(@Param("mid")Integer mid,@Param("IsHTValue")Integer IsHTValue);
	void updateGroupIsHT(@Param("gid")Integer gid,@Param("IsHTValue")Integer IsHTValue);
}
