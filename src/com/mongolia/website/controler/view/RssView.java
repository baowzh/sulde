package com.mongolia.website.controler.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.mongolia.website.model.RssItem;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;
@Component("rssView")
public class RssView extends AbstractRssFeedView {

	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		@SuppressWarnings("unchecked")
		List<RssItem> rssItems = (List<RssItem>) model.get("rssItemList");
		List<Item> feedItems = new ArrayList<Item>();
		for (RssItem rssItem : rssItems) {
			Item feedItem = new Item();
			feedItem.setTitle(rssItem.getTitle());
			feedItem.setAuthor(rssItem.getAuthor());
			feedItem.setPubDate(rssItem.getDatepublished());
			Description desc = new Description();
			desc.setValue(rssItem.getDescription());
			feedItem.setDescription(desc);
			feedItem.setLink(rssItem.getLink());
			feedItems.add(feedItem);
		}
		return feedItems;
	}

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
			HttpServletRequest request) {
		String feedTitle = (String) model.get("feedTitle");
		String feedDesc = (String) model.get("feedDesc");
		String feedLink = (String) model.get("feedLink");
		feed.setTitle(feedTitle);
		feed.setDescription(feedDesc);
		feed.setLink(feedLink);
	}

}
