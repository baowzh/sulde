package com.mongolia.website.job;

import javax.annotation.Resource;

import com.mongolia.website.manager.interfaces.AutoResponseManager;

public class SendServiceMessJob {
	@Resource(name = "autoResponseManagerImpl")
	private AutoResponseManager autoResponseManager;

	public void work() {
		autoResponseManager.sendServiceMess();
	}
}
