package com.mongolia.website.manager.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.WebPageManagerDao;
import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.manager.interfaces.WebPageManager;
import com.mongolia.website.model.PageChannelRelationValue;
import com.mongolia.website.model.WebPageValue;

@Service("webPageManager")
public class WebPageManagerImpl implements WebPageManager {
	@Autowired
	private WebPageManagerDao webPageManagerDao;

	@Override
	public List<WebPageValue> getPages(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.webPageManagerDao.getPages(params);
	}

	@Override
	public WebPageValue getpageInfo(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.webPageManagerDao.getpageInfo(params);
	}

	@Override
	public void doAddWebPage(WebPageValue webPageValue) throws Exception {
		// TODO Auto-generated method stub
		this.webPageManagerDao.addPage(webPageValue);
	}

	@Override
	public void doDeletePage(String pageid) throws Exception {
		// TODO Auto-generated method stub
		List channels = this.webPageManagerDao.getRelatedChannes(pageid);
		Integer isParent = this.webPageManagerDao.isParentPage(pageid);
		if (channels != null && !channels.isEmpty()) {
			throw new ManagerException("页面已经被使用不能删除!");
		}
		if (isParent != null && isParent.intValue() != 0) {
			throw new ManagerException("页面已经被使用不能删除!");
		}
		// 是否有下级页面

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageid", pageid);
		this.webPageManagerDao.deletePage(params);
	}

	@Override
	public void doModifyWebPage(WebPageValue webPageValue) throws Exception {
		// TODO Auto-generated method stub
		this.webPageManagerDao.modifyPage(webPageValue);
	}

	@Override
	public void doAddChannelToPage(
			PageChannelRelationValue pageChannelRelationValue) throws Exception {
		// TODO Auto-generated method stub
		if (pageChannelRelationValue.getPageid() == null
				|| pageChannelRelationValue.getPageid().equalsIgnoreCase("")) {
			throw new ManagerException("请选择要关联的页面!");
		}
		if (pageChannelRelationValue.getChannelid() == null
				|| pageChannelRelationValue.getChannelid().equalsIgnoreCase("")) {
			throw new ManagerException("请选择要添加的栏目!");
		}
		if (pageChannelRelationValue.getChanneldoccount() == null
				|| pageChannelRelationValue.getChanneldoccount().intValue() == 0) {
			throw new ManagerException("请填写栏目明细个数!");
		}
		if (pageChannelRelationValue.getVariablename() == null
				|| pageChannelRelationValue.getVariablename().equalsIgnoreCase(
						"")) {
			throw new ManagerException("请填写要素名称");
		}
		Integer ischecked = this.webPageManagerDao
				.checkIsRelated(pageChannelRelationValue);
		if (ischecked.intValue() > 0) {
			throw new ManagerException("栏目'"
					+ pageChannelRelationValue.getChnlname() + "'已经在页面'"
					+ pageChannelRelationValue.getPagename() + "'上关联过了!");
		}
		this.webPageManagerDao.addChannelToPage(pageChannelRelationValue);

	}

	@Override
	public List<PageChannelRelationValue> getRelatedChannes(String pageid) throws Exception {
		// TODO Auto-generated method stub
		return this.webPageManagerDao.getRelatedChannes(pageid);
	}

	@Override
	public void doDeleteChannelFromPage(String pageId, String channelId,
			String displayType) throws Exception {
		// TODO Auto-generated method stub
		// deleteChannelFromPage
		this.webPageManagerDao.deleteChannelFromPage(pageId, channelId,
				displayType);

	}

}
