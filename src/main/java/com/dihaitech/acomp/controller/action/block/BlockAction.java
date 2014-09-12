package com.dihaitech.acomp.controller.action.block;

import java.util.Date;
import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.Block;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.service.IBlockService;
import com.dihaitech.acomp.service.ITempleteService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;

/**
 * 块Action
 * 
 * @author cg
 *
 * @date 2014-09-02
 */
 @SuppressWarnings("serial")
public class BlockAction extends BaseAction {
	private Block block = new Block();
	private IBlockService blockService;
	
	private ITempleteService templeteService;
	
	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	public IBlockService getBlockService() {
		return blockService;
	}

	public void setBlockService(IBlockService blockService) {
		this.blockService = blockService;
	}
	
	public ITempleteService getTempleteService() {
		return templeteService;
	}

	public void setTempleteService(ITempleteService templeteService) {
		this.templeteService = templeteService;
	}

	/* 
	 * 块查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			
			pageSize = TypeUtil.stringToInt(pageSizeStr);
			if (pageSize <= 0) {
				pageSize = Property.PAGESIZE;
			}

			pageNo = TypeUtil.stringToInt(pageNoStr);
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			}else{
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = blockService.selectBlock(block,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Block> resultList = this.blockService.selectBlock(block,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","blockAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Block json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 块
	 * @return
	 */
	public String add(){
		Templete templete = new Templete();
		templete.setType(4);	//块模板
		List<Templete> templeteList = templeteService.selectTempleteByType(templete);
		this.getRequest().setAttribute("templeteList", templeteList);
		
		return "add";
	}
	
	/**
	 * 保存添加 块
	 * @return
	 */
	public String addSave(){
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		block.setCreateuser(manager.getNickname());
		block.setCreatetime(new Date());
		block.setUpdateuser(manager.getNickname());
		block.setUpdatetime(new Date());
		
		blockService.addSave(block);
		return "addSave";
	}
	
	/**
	 * 修改 块
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			block.setId(id);
		}else{
			return null;
		}
		
		Block blockVO = blockService.selectBlockById(block);
		this.getRequest().setAttribute("block", blockVO);
		return "edit";
	}
	
	/**
	 * 保存修改 块
	 * @return
	 */
	public String editSave(){
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		block.setUpdateuser(manager.getNickname());
		block.setUpdatetime(new Date());
		
		blockService.editSave(block);
		return "editSave";
	}
	
	/**
	 * 删除 块
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		blockService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 块
	 * @return
	 */
	public String deleteByIds(){
		String[] ids = this.getRequest().getParameterValues("id");
		StringBuffer strbuf = new StringBuffer(" where id in(");
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != 0) {
					strbuf.append("," + ids[i]);
				} else {
					strbuf.append(ids[i]);
				}
			}
			strbuf.append(")");
			blockService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}