package com.temptation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.temptation.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temptation.pojo.Terminal;
import org.springframework.web.servlet.ModelAndView;

/**
 * 终端状态
 * @author yanghui
 *
 */
@Controller
@RequestMapping(value="/terminal")
public class TerminalController {
	@Autowired
	private TerminalService terminalService;

	@RequestMapping("/toterminal")
	public ModelAndView toterminal() {
		return new ModelAndView("/html/terminal.html");
	}

	@RequestMapping("/torunstate")
	public ModelAndView torunstate() {
		return new ModelAndView("/html/runstate.html");
	}
	
	@RequestMapping("getTerminal")
	@ResponseBody
	public Object getTerminal(HttpServletRequest request) {
//	当前页
		Integer page=Integer.parseInt(request.getParameter("page"));
//	显示页数
		Integer limit=Integer.parseInt(request.getParameter("limit"));
		Integer pageNo=(page-1)*limit;
		Map<String, Object> terminalMap=new HashMap<String, Object>();
		Map<String, Object> pageMap=new HashMap<String, Object>();
		List<Terminal> talminals=terminalService.queryTerminals(pageMap);
		pageMap.put("pageNo", pageNo);
		pageMap.put("limit", limit);
		List<Terminal> terminals=terminalService.queryTerminals(pageMap);
		terminalMap.put("data", terminals);
		terminalMap.put("code", 0);
		terminalMap.put("msg", "");
		terminalMap.put("count",talminals.size() );
		return terminalMap;
	}


	@RequestMapping("queryTerminalsByDevicestauts")
	@ResponseBody
	public Object queryTerminalsByDevicestauts(Integer stage) {
		return terminalService.queryTerminalsByDevicestauts(stage);
	}

	@RequestMapping("queryInfoById")
	@ResponseBody
	public Object queryInfoById(Integer tid) {
		return terminalService.queryInfoById(tid);
	}

	/**
	 * 修改终端
	 * @return
	 */
	@RequestMapping("updateTerminalById")
	@ResponseBody
	public Object updateTerminalById(Terminal terminal) {
		return terminalService.updateTerminalById(terminal);
	}

	/**
	 * 新增终端
	 * @return
	 */
	@RequestMapping("addTerminal")
	@ResponseBody
	public Object addTerminal(Terminal terminal) {
		Integer gid=terminal.getGid();
		Integer mid=terminal.getMid();
		terminalService.updateGroupIsHT(gid, 1);
		terminalService.updateManagementIsHT(mid, 1);
		terminalService.addTerminal(terminal);
		return 1;
	}

	/**
	 * 删除终端
	 * @return
	 */
	@RequestMapping("deleteTerminalById")
	@ResponseBody
	public Object deleteTerminalById(String[] ids) {
		try {
			for(int i=0;i<ids.length;i++){
				Terminal terminal=terminalService.queryInfoById(Integer.valueOf(ids[i]));
				Integer mid=terminal.getMid();
				Integer gid=terminal.getGid();
				terminalService.updateGroupIsHT(gid, 0);
				terminalService.updateManagementIsHT(mid, 0);
				terminalService.deleteTerminalById(Integer.valueOf(ids[i]));
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	@RequestMapping(value="/queryTerminalsByParentId",method=RequestMethod.POST)
	@ResponseBody
	public Object queryTerminalsByParentId(Long parentId) {
	 List<Terminal> terminals =	terminalService.queryTerminalsByParentId(parentId);
	 Map<String, Object> maps = new HashMap<>();
	 maps.put("data", terminals);
	 maps.put("count", terminals.size());
	 maps.put("code", 0);
	 maps.put("msg", "");
	 return maps;
	}
	
	@RequestMapping(value="/selectTerminalsByParentId",method=RequestMethod.POST)
	@ResponseBody
	public Object selectTerminalsByParentId(Long parentId) {
	 List<Terminal> terminals =	terminalService.selectTerminalsByParentId(parentId);
	 Map<String, Object> maps = new HashMap<>();
	 maps.put("data", terminals);
	 maps.put("count", terminals.size());
	 maps.put("code", 0);
	 maps.put("msg", "");
	 return maps;
	}

}
