package com.mongolia.website.util;

import java.util.ArrayList;
import java.util.List;

import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.RaceScoreLogValue;
import com.mongolia.website.model.UserValue;

public class PageUtil {
	public static String getPagingLink(PaingModel<DocumentValue> pagingModel,
			int type) {
		String classname = "";
		String currentclass = "";
		if (type == 1) {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		} else {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		}
		String pagetype = "1";
		String processbar = "false";
		if (!"1".equalsIgnoreCase(pagingModel.getPagetype())) {
			pagetype = "0";
			processbar = "true";
		}
		List<PagingIndex> pagse = new ArrayList<PagingIndex>();
		PagingIndex firestPagingIndex = new PagingIndex();
		firestPagingIndex.setPageindex(1);
		firestPagingIndex.setShowstr("<<");

		firestPagingIndex.setLink("javascript:openpage('1','"
				+ pagingModel.getUserid() + "'," + type + "," + pagetype + ","
				+ processbar + ");switchclass('pagefist');");
		firestPagingIndex.setId("firest");
		pagse.add(firestPagingIndex);
		PagingIndex previousPagingIndex = new PagingIndex();
		previousPagingIndex.setPageindex(pagingModel.getPageindex() - 1);
		if (pagingModel.getPageindex() - 1 <= 1) {
			previousPagingIndex.setPageindex(1);
		}
		previousPagingIndex.setShowstr("<");
		previousPagingIndex.setLink("javascript:openpage('"
				+ previousPagingIndex.getPageindex() + "','"
				+ pagingModel.getUserid() + "'," + type + "," + pagetype + ","
				+ processbar + ");switchclass('previous');");
		previousPagingIndex.setId("previous");
		pagse.add(previousPagingIndex);
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		if (pagingModel.getPageindex() == 1) {
			index1 = 1;
			index2 = 2;
			index3 = 3;
		} else if (pagingModel.getPageindex() == pagingModel.getPagecount()
				&& pagingModel.getPagecount() >= 3) {
			index1 = pagingModel.getPagecount() - 2;
			index2 = pagingModel.getPagecount() - 1;
			index3 = pagingModel.getPagecount();
		} else {
			index1 = pagingModel.getPageindex() - 1;
			index2 = pagingModel.getPageindex();
			index3 = pagingModel.getPageindex() + 1;
		}
		PagingIndex previousPagingIndex1 = new PagingIndex();
		previousPagingIndex1.setPageindex(index1);
		previousPagingIndex1.setShowstr(""
				+ previousPagingIndex1.getPageindex());
		previousPagingIndex1.setLink("javascript:openpage('"
				+ previousPagingIndex1.getPageindex() + "','"
				+ pagingModel.getUserid() + "'," + type + "," + pagetype + ","
				+ processbar + ");switchclass('"
				+ previousPagingIndex1.getPageindex() + "');");
		previousPagingIndex1
				.setId("page" + previousPagingIndex1.getPageindex());
		pagse.add(previousPagingIndex1);

		//
		PagingIndex currentpage = new PagingIndex();
		currentpage.setPageindex(index2);
		currentpage.setShowstr("" + currentpage.getPageindex());
		currentpage.setLink("javascript:openpage('"
				+ currentpage.getPageindex() + "','" + pagingModel.getUserid()
				+ "'," + type + "," + pagetype + "," + processbar
				+ ");switchclass('" + currentpage.getPageindex() + "');");
		currentpage.setId("page" + currentpage.getPageindex());
		pagse.add(currentpage);
		currentpage.setCurrent(1);
		//
		//
		PagingIndex nextpage1 = new PagingIndex();
		nextpage1.setPageindex(index3);
		nextpage1.setShowstr("" + nextpage1.getPageindex());
		nextpage1.setLink("javascript:openpage('" + nextpage1.getPageindex()
				+ "','" + pagingModel.getUserid() + "'," + type + ","
				+ pagetype + "," + processbar + ");switchclass('"
				+ nextpage1.getPageindex() + "');");
		nextpage1.setId("page" + nextpage1.getPageindex());
		pagse.add(nextpage1);
		//
		PagingIndex nextPagingIndex = new PagingIndex();
		nextPagingIndex.setShowstr(">");
		nextPagingIndex.setPageindex(pagingModel.getPageindex() + 1);
		if (pagingModel.getPageindex() + 1 >= pagingModel.getPagecount()) {
			nextPagingIndex.setPageindex(pagingModel.getPagecount());
		}
		nextPagingIndex.setLink("javascript:openpage('"
				+ nextPagingIndex.getPageindex() + "','"
				+ pagingModel.getUserid() + "'," + type + "," + pagetype + ","
				+ processbar + ");switchclass('next');");
		nextPagingIndex.setId("next");
		pagse.add(nextPagingIndex);
		PagingIndex lastPagingIndex = new PagingIndex();
		lastPagingIndex.setPageindex(pagingModel.getPagecount());
		lastPagingIndex.setShowstr(">>");
		lastPagingIndex.setLink("javascript:openpage('"
				+ lastPagingIndex.getPageindex() + "','"
				+ pagingModel.getUserid() + "'," + type + "," + pagetype + ","
				+ processbar + ");switchclass('last');");
		lastPagingIndex.setId("last");
		pagse.add(lastPagingIndex);
		String pagestr = "";
		for (PagingIndex pagingIndexi : pagse) {
			if (pagingIndexi.getCurrent() != null
					&& pagingIndexi.getCurrent().intValue() == 1) {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + currentclass
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			} else {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + classname
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			}

		}
		return pagestr;
	}

	public static String getPagingImgLink(
			PaingModel<DocumentValue> pagingModel, int type) {
		String classname = "";
		String currentclass = "";
		if (type == 1) {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		} else {
			classname = "sharespanstyle";
			currentclass = "cursharespanstyle";
		}
		String pagetype = "1";
		String processbar = "false";
		if (!"1".equalsIgnoreCase(pagingModel.getPagetype())) {
			pagetype = "0";
			processbar = "true";
		}
		List<PagingIndex> pagse = new ArrayList<PagingIndex>();
		PagingIndex firestPagingIndex = new PagingIndex();
		firestPagingIndex.setPageindex(1);
		firestPagingIndex.setShowstr("<<");

		firestPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex=1");
		firestPagingIndex.setId("firest");
		pagse.add(firestPagingIndex);
		PagingIndex previousPagingIndex = new PagingIndex();
		previousPagingIndex.setPageindex(pagingModel.getPageindex() - 1);
		if (pagingModel.getPageindex() - 1 <= 1) {
			previousPagingIndex.setPageindex(1);
		}
		previousPagingIndex.setShowstr("<");
		previousPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ previousPagingIndex.getPageindex() + "");
		previousPagingIndex.setId("previous");
		pagse.add(previousPagingIndex);
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		if (pagingModel.getPageindex() == 1 && pagingModel.getPagecount() >= 3) {
			index1 = 1;
			index2 = 2;
			index3 = 3;
		} else if (pagingModel.getPageindex() == pagingModel.getPagecount()
				&& pagingModel.getPagecount() >= 3) {
			index1 = pagingModel.getPagecount() - 2;
			index2 = pagingModel.getPagecount() - 1;
			index3 = pagingModel.getPagecount();
		} else if (pagingModel.getPageindex() == 1
				&& pagingModel.getPagecount() < 3) {
			index1 = 1;
			index2 = pagingModel.getPagecount() < 2 ? 1 : 2;
			index3 = pagingModel.getPagecount() < 3 ? 2 : 3;
		} else {
			index1 = pagingModel.getPageindex() - 1;
			index2 = pagingModel.getPageindex();
			index3 = pagingModel.getPageindex() + 1;
		}
		PagingIndex previousPagingIndex1 = new PagingIndex();
		previousPagingIndex1.setPageindex(index1);
		previousPagingIndex1.setShowstr(""
				+ previousPagingIndex1.getPageindex());
		previousPagingIndex1.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex=" + index1 + "");
		previousPagingIndex1
				.setId("page" + previousPagingIndex1.getPageindex());
		if (pagingModel.getPagecount() == 1) {
			previousPagingIndex1.setCurrent(1);
		}
		pagse.add(previousPagingIndex1);
		//
		PagingIndex currentpage = new PagingIndex();
		currentpage.setPageindex(index2);
		currentpage.setShowstr("" + currentpage.getPageindex());
		currentpage.setLink("getimglist.do?userid=" + pagingModel.getUserid()
				+ "&imggroupid=" + pagingModel.getImggroupid() + "&pageindex="
				+ index2 + "");
		currentpage.setId("page" + currentpage.getPageindex());
		if (pagingModel.getPagecount() >= 2) {
			pagse.add(currentpage);
			currentpage.setCurrent(1);
		}
		//
		PagingIndex nextpage1 = new PagingIndex();
		nextpage1.setPageindex(index3);
		nextpage1.setShowstr("" + nextpage1.getPageindex());
		nextpage1.setLink("getimglist.do?userid=" + pagingModel.getUserid()
				+ "&imggroupid=" + pagingModel.getImggroupid() + "&pageindex="
				+ index3 + "");
		nextpage1.setId("page" + nextpage1.getPageindex());
		if (pagingModel.getPagecount() >= 3) {
			pagse.add(nextpage1);
		}
		//
		PagingIndex nextPagingIndex = new PagingIndex();
		nextPagingIndex.setShowstr(">");
		nextPagingIndex.setPageindex(pagingModel.getPageindex() + 1);
		if (pagingModel.getPageindex() + 1 >= pagingModel.getPagecount()) {
			nextPagingIndex.setPageindex(pagingModel.getPagecount());
		}
		nextPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ nextPagingIndex.getPageindex() + "");
		nextPagingIndex.setId("next");
		pagse.add(nextPagingIndex);
		PagingIndex lastPagingIndex = new PagingIndex();
		lastPagingIndex.setPageindex(pagingModel.getPagecount());
		lastPagingIndex.setShowstr(">>");
		lastPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ lastPagingIndex.getPageindex() + "");
		lastPagingIndex.setId("last");
		pagse.add(lastPagingIndex);
		String pagestr = "";
		for (PagingIndex pagingIndexi : pagse) {
			if (pagingIndexi.getCurrent() != null
					&& pagingIndexi.getCurrent().intValue() == 1) {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + currentclass
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			} else {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + classname
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			}

		}
		return pagestr;
	}

	public static String getPagingFriendLink(
			PaingModel<FriendValue> pagingModel, int type) {
		String classname = "";
		String currentclass = "";
		if (type == 1) {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		} else {
			classname = "sharespanstyle";
			currentclass = "cursharespanstyle";
		}
		String pagetype = "1";
		String processbar = "false";
		if (!"1".equalsIgnoreCase(pagingModel.getPagetype())) {
			pagetype = "0";
			processbar = "true";
		}
		List<PagingIndex> pagse = new ArrayList<PagingIndex>();
		PagingIndex firestPagingIndex = new PagingIndex();
		firestPagingIndex.setPageindex(1);
		firestPagingIndex.setShowstr("<<");

		firestPagingIndex.setLink("friendlist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex=1");
		firestPagingIndex.setId("firest");
		pagse.add(firestPagingIndex);
		PagingIndex previousPagingIndex = new PagingIndex();
		previousPagingIndex.setPageindex(pagingModel.getPageindex() - 1);
		if (pagingModel.getPageindex() - 1 <= 1) {
			previousPagingIndex.setPageindex(1);
		}
		previousPagingIndex.setShowstr("<");
		previousPagingIndex.setLink("friendlist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ previousPagingIndex.getPageindex() + "");
		previousPagingIndex.setId("previous");
		pagse.add(previousPagingIndex);
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		if (pagingModel.getPageindex() == 1 && pagingModel.getPagecount() >= 3) {
			index1 = 1;
			index2 = 2;
			index3 = 3;
		} else if (pagingModel.getPageindex() == pagingModel.getPagecount()
				&& pagingModel.getPagecount() >= 3) {
			index1 = pagingModel.getPagecount() - 2;
			index2 = pagingModel.getPagecount() - 1;
			index3 = pagingModel.getPagecount();
		} else if (pagingModel.getPageindex() == 1
				&& pagingModel.getPagecount() < 3) {
			index1 = 1;
			index2 = pagingModel.getPagecount() < 2 ? 1 : 2;
			index3 = pagingModel.getPagecount() < 3 ? 2 : 3;
		} else {
			index1 = pagingModel.getPageindex() - 1;
			index2 = pagingModel.getPageindex();
			index3 = pagingModel.getPageindex() + 1;
		}
		PagingIndex previousPagingIndex1 = new PagingIndex();
		previousPagingIndex1.setPageindex(index1);
		previousPagingIndex1.setShowstr(""
				+ previousPagingIndex1.getPageindex());
		previousPagingIndex1.setLink("friendlist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex=" + index1 + "");
		previousPagingIndex1
				.setId("page" + previousPagingIndex1.getPageindex());
		if (pagingModel.getPagecount() == 1) {
			previousPagingIndex1.setCurrent(1);
		}
		pagse.add(previousPagingIndex1);
		//
		PagingIndex currentpage = new PagingIndex();
		currentpage.setPageindex(index2);
		currentpage.setShowstr("" + currentpage.getPageindex());
		currentpage.setLink("friendlist.do?userid=" + pagingModel.getUserid()
				+ "&imggroupid=" + pagingModel.getImggroupid() + "&pageindex="
				+ index2 + "");
		currentpage.setId("page" + currentpage.getPageindex());
		if (pagingModel.getPagecount() >= 2) {
			pagse.add(currentpage);
			currentpage.setCurrent(1);
		}
		//
		PagingIndex nextpage1 = new PagingIndex();
		nextpage1.setPageindex(index3);
		nextpage1.setShowstr("" + nextpage1.getPageindex());
		nextpage1.setLink("friendlist.do?userid=" + pagingModel.getUserid()
				+ "&imggroupid=" + pagingModel.getImggroupid() + "&pageindex="
				+ index3 + "");
		nextpage1.setId("page" + nextpage1.getPageindex());
		if (pagingModel.getPagecount() >= 3) {
			pagse.add(nextpage1);
		}
		//
		PagingIndex nextPagingIndex = new PagingIndex();
		nextPagingIndex.setShowstr(">");
		nextPagingIndex.setPageindex(pagingModel.getPageindex() + 1);
		if (pagingModel.getPageindex() + 1 >= pagingModel.getPagecount()) {
			nextPagingIndex.setPageindex(pagingModel.getPagecount());
		}
		nextPagingIndex.setLink("friendlist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ nextPagingIndex.getPageindex() + "");
		nextPagingIndex.setId("next");
		pagse.add(nextPagingIndex);
		PagingIndex lastPagingIndex = new PagingIndex();
		lastPagingIndex.setPageindex(pagingModel.getPagecount());
		lastPagingIndex.setShowstr(">>");
		lastPagingIndex.setLink("friendlist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ lastPagingIndex.getPageindex() + "");
		lastPagingIndex.setId("last");
		pagse.add(lastPagingIndex);
		String pagestr = "";
		for (PagingIndex pagingIndexi : pagse) {
			if (pagingIndexi.getCurrent() != null
					&& pagingIndexi.getCurrent().intValue() == 1) {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + currentclass
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			} else {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + classname
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			}

		}
		return pagestr;
	}

	public static String getPagingUserLink(PaingModel<UserValue> pagingModel,
			int type) {
		String classname = "";
		String currentclass = "";
		if (type == 1) {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		} else {
			classname = "sharespanstyle";
			currentclass = "cursharespanstyle";
		}
		String pagetype = "1";
		String processbar = "false";
		if (!"1".equalsIgnoreCase(pagingModel.getPagetype())) {
			pagetype = "0";
			processbar = "true";
		}
		List<PagingIndex> pagse = new ArrayList<PagingIndex>();
		PagingIndex firestPagingIndex = new PagingIndex();
		firestPagingIndex.setPageindex(1);
		firestPagingIndex.setShowstr("<<");

		firestPagingIndex.setLink("phonebloglist.do?pageindex=1");
		firestPagingIndex.setId("firest");
		pagse.add(firestPagingIndex);
		PagingIndex previousPagingIndex = new PagingIndex();
		previousPagingIndex.setPageindex(pagingModel.getPageindex() - 1);
		if (pagingModel.getPageindex() - 1 <= 1) {
			previousPagingIndex.setPageindex(1);
		}
		previousPagingIndex.setShowstr("<");
		previousPagingIndex.setLink("phonebloglist.do?pageindex="
				+ previousPagingIndex.getPageindex() + "");
		previousPagingIndex.setId("previous");
		pagse.add(previousPagingIndex);
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		if (pagingModel.getPageindex() == 1 && pagingModel.getPagecount() >= 3) {
			index1 = 1;
			index2 = 2;
			index3 = 3;
		} else if (pagingModel.getPageindex() == pagingModel.getPagecount()
				&& pagingModel.getPagecount() >= 3) {
			index1 = pagingModel.getPagecount() - 2;
			index2 = pagingModel.getPagecount() - 1;
			index3 = pagingModel.getPagecount();
		} else if (pagingModel.getPageindex() == 1
				&& pagingModel.getPagecount() < 3) {
			index1 = 1;
			index2 = pagingModel.getPagecount() < 2 ? 1 : 2;
			index3 = pagingModel.getPagecount() < 3 ? 2 : 3;
		} else {
			index1 = pagingModel.getPageindex() - 1;
			index2 = pagingModel.getPageindex();
			index3 = pagingModel.getPageindex() + 1;
		}
		PagingIndex previousPagingIndex1 = new PagingIndex();
		previousPagingIndex1.setPageindex(index1);
		previousPagingIndex1.setShowstr(""
				+ previousPagingIndex1.getPageindex());
		previousPagingIndex1.setLink("phonebloglist.do?pageindex=" + index1
				+ "");
		previousPagingIndex1
				.setId("page" + previousPagingIndex1.getPageindex());
		if (pagingModel.getPagecount() == 1) {
			previousPagingIndex1.setCurrent(1);
		}
		pagse.add(previousPagingIndex1);
		//
		PagingIndex currentpage = new PagingIndex();
		currentpage.setPageindex(index2);
		currentpage.setShowstr("" + currentpage.getPageindex());
		currentpage.setLink("phonebloglist.do?pageindex=" + index2 + "");
		currentpage.setId("page" + currentpage.getPageindex());
		if (pagingModel.getPagecount() >= 2) {
			pagse.add(currentpage);
			currentpage.setCurrent(1);
		}
		//
		PagingIndex nextpage1 = new PagingIndex();
		nextpage1.setPageindex(index3);
		nextpage1.setShowstr("" + nextpage1.getPageindex());
		nextpage1.setLink("phonebloglist.do?pageindex=" + index3 + "");
		nextpage1.setId("page" + nextpage1.getPageindex());
		if (pagingModel.getPagecount() >= 3) {
			pagse.add(nextpage1);
		}
		//
		PagingIndex nextPagingIndex = new PagingIndex();
		nextPagingIndex.setShowstr(">");
		nextPagingIndex.setPageindex(pagingModel.getPageindex() + 1);
		if (pagingModel.getPageindex() + 1 >= pagingModel.getPagecount()) {
			nextPagingIndex.setPageindex(pagingModel.getPagecount());
		}
		nextPagingIndex.setLink("phonebloglist.do?pageindex="
				+ nextPagingIndex.getPageindex() + "");
		nextPagingIndex.setId("next");
		pagse.add(nextPagingIndex);
		PagingIndex lastPagingIndex = new PagingIndex();
		lastPagingIndex.setPageindex(pagingModel.getPagecount());
		lastPagingIndex.setShowstr(">>");
		lastPagingIndex.setLink("phonebloglist.do?pageindex="
				+ lastPagingIndex.getPageindex() + "");
		lastPagingIndex.setId("last");
		pagse.add(lastPagingIndex);
		String pagestr = "";
		for (PagingIndex pagingIndexi : pagse) {
			if (pagingIndexi.getCurrent() != null
					&& pagingIndexi.getCurrent().intValue() == 1) {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + currentclass
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			} else {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + classname
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			}

		}
		return pagestr;
	}

	public static String getPagingAlbumLink(
			PaingModel<ImgGrpupValue> pagingModel, int type) {
		String classname = "";
		String currentclass = "";
		if (type == 1) {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		} else {
			classname = "sharespanstyle";
			currentclass = "cursharespanstyle";
		}
		String pagetype = "1";
		String processbar = "false";
		if (!"1".equalsIgnoreCase(pagingModel.getPagetype())) {
			pagetype = "0";
			processbar = "true";
		}
		List<PagingIndex> pagse = new ArrayList<PagingIndex>();
		PagingIndex firestPagingIndex = new PagingIndex();
		firestPagingIndex.setPageindex(1);
		firestPagingIndex.setShowstr("<<");

		firestPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex=1");
		firestPagingIndex.setId("firest");
		pagse.add(firestPagingIndex);
		PagingIndex previousPagingIndex = new PagingIndex();
		previousPagingIndex.setPageindex(pagingModel.getPageindex() - 1);
		if (pagingModel.getPageindex() - 1 <= 1) {
			previousPagingIndex.setPageindex(1);
		}
		previousPagingIndex.setShowstr("<");
		previousPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ previousPagingIndex.getPageindex() + "");
		previousPagingIndex.setId("previous");
		pagse.add(previousPagingIndex);
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		if (pagingModel.getPageindex() == 1 && pagingModel.getPagecount() >= 3) {
			index1 = 1;
			index2 = 2;
			index3 = 3;
		} else if (pagingModel.getPageindex() == pagingModel.getPagecount()
				&& pagingModel.getPagecount() >= 3) {
			index1 = pagingModel.getPagecount() - 2;
			index2 = pagingModel.getPagecount() - 1;
			index3 = pagingModel.getPagecount();
		} else if (pagingModel.getPageindex() == 1
				&& pagingModel.getPagecount() < 3) {
			index1 = 1;
			index2 = pagingModel.getPagecount() < 2 ? 1 : 2;
			index3 = pagingModel.getPagecount() < 3 ? 2 : 3;
		} else {
			index1 = pagingModel.getPageindex() - 1;
			index2 = pagingModel.getPageindex();
			index3 = pagingModel.getPageindex() + 1;
		}
		PagingIndex previousPagingIndex1 = new PagingIndex();
		previousPagingIndex1.setPageindex(index1);
		previousPagingIndex1.setShowstr(""
				+ previousPagingIndex1.getPageindex());
		previousPagingIndex1.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex=" + index1 + "");
		previousPagingIndex1
				.setId("page" + previousPagingIndex1.getPageindex());
		if (pagingModel.getPagecount() == 1) {
			previousPagingIndex1.setCurrent(1);
		}
		pagse.add(previousPagingIndex1);
		//
		PagingIndex currentpage = new PagingIndex();
		currentpage.setPageindex(index2);
		currentpage.setShowstr("" + currentpage.getPageindex());
		currentpage.setLink("getimglist.do?userid=" + pagingModel.getUserid()
				+ "&imggroupid=" + pagingModel.getImggroupid() + "&pageindex="
				+ index2 + "");
		currentpage.setId("page" + currentpage.getPageindex());
		if (pagingModel.getPagecount() >= 2) {
			pagse.add(currentpage);
			currentpage.setCurrent(1);
		}
		//
		PagingIndex nextpage1 = new PagingIndex();
		nextpage1.setPageindex(index3);
		nextpage1.setShowstr("" + nextpage1.getPageindex());
		nextpage1.setLink("getimglist.do?userid=" + pagingModel.getUserid()
				+ "&imggroupid=" + pagingModel.getImggroupid() + "&pageindex="
				+ index3 + "");
		nextpage1.setId("page" + nextpage1.getPageindex());
		if (pagingModel.getPagecount() >= 3) {
			pagse.add(nextpage1);
		}
		//
		PagingIndex nextPagingIndex = new PagingIndex();
		nextPagingIndex.setShowstr(">");
		nextPagingIndex.setPageindex(pagingModel.getPageindex() + 1);
		if (pagingModel.getPageindex() + 1 >= pagingModel.getPagecount()) {
			nextPagingIndex.setPageindex(pagingModel.getPagecount());
		}
		nextPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ nextPagingIndex.getPageindex() + "");
		nextPagingIndex.setId("next");
		pagse.add(nextPagingIndex);
		PagingIndex lastPagingIndex = new PagingIndex();
		lastPagingIndex.setPageindex(pagingModel.getPagecount());
		lastPagingIndex.setShowstr(">>");
		lastPagingIndex.setLink("getimglist.do?userid="
				+ pagingModel.getUserid() + "&imggroupid="
				+ pagingModel.getImggroupid() + "&pageindex="
				+ lastPagingIndex.getPageindex() + "");
		lastPagingIndex.setId("last");
		pagse.add(lastPagingIndex);
		String pagestr = "";
		for (PagingIndex pagingIndexi : pagse) {
			if (pagingIndexi.getCurrent() != null
					&& pagingIndexi.getCurrent().intValue() == 1) {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + currentclass
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			} else {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + classname
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			}

		}
		return pagestr;
	}

	public static String getPagingLinkForRaceScore(
			PaingModel<RaceScoreLogValue> pagingModel, int type) {
		String classname = "";
		String currentclass = "";
		if (type == 1) {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		} else {
			classname = "spanstyle";
			currentclass = "curspanstyle";
		}
		String pagetype = "1";
		String processbar = "false";
		if (!"1".equalsIgnoreCase(pagingModel.getPagetype())) {
			pagetype = "0";
			processbar = "true";
		}
		List<PagingIndex> pagse = new ArrayList<PagingIndex>();
		PagingIndex firestPagingIndex = new PagingIndex();
		firestPagingIndex.setPageindex(1);
		firestPagingIndex.setShowstr("<<");

		firestPagingIndex.setLink("javascript:openpage('1','"
				+ pagingModel.getDocchannel() + "','"
				+ pagingModel.getImggroupid() + "',"
				+ pagingModel.getDocstatus() + ");");
		firestPagingIndex.setId("firest");
		pagse.add(firestPagingIndex);
		PagingIndex previousPagingIndex = new PagingIndex();
		previousPagingIndex.setPageindex(pagingModel.getPageindex() - 1);
		if (pagingModel.getPageindex() - 1 <= 1) {
			previousPagingIndex.setPageindex(1);
		}
		previousPagingIndex.setShowstr("<");
		previousPagingIndex.setLink("javascript:openpage('"
				+ previousPagingIndex.getPageindex() + "','"
				+ pagingModel.getDocchannel() + "','"
				+ pagingModel.getImggroupid() + "',"
				+ pagingModel.getDocstatus() + ");");
		previousPagingIndex.setId("previous");
		pagse.add(previousPagingIndex);
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		if (pagingModel.getPageindex() == 1) {
			index1 = 1;
			index2 = 2;
			index3 = 3;
		} else if (pagingModel.getPageindex() == pagingModel.getPagecount()
				&& pagingModel.getPagecount() >= 3) {
			index1 = pagingModel.getPagecount() - 2;
			index2 = pagingModel.getPagecount() - 1;
			index3 = pagingModel.getPagecount();
		} else {
			index1 = pagingModel.getPageindex() - 1;
			index2 = pagingModel.getPageindex();
			index3 = pagingModel.getPageindex() + 1;
		}
		if(pagingModel.getPagecount()==1){
			index2 = 1;
			index3 = 1;
		}
		PagingIndex previousPagingIndex1 = new PagingIndex();
		previousPagingIndex1.setPageindex(index1);
		previousPagingIndex1.setShowstr(""
				+ previousPagingIndex1.getPageindex());
		previousPagingIndex1.setLink("javascript:openpage('"
				+ previousPagingIndex1.getPageindex() + "','"
				+ pagingModel.getDocchannel() + "','"
				+ pagingModel.getImggroupid() + "',"
				+ pagingModel.getDocstatus() + ");");
		previousPagingIndex1
				.setId("page" + previousPagingIndex1.getPageindex());
		if(pagingModel.getPageindex().intValue()==1){
			previousPagingIndex1.setCurrent(1);
		}
		if(pagingModel.getPagecount()!=1){
			pagse.add(previousPagingIndex1);	
		}
		

		//
		PagingIndex currentpage = new PagingIndex();
		if(pagingModel.getPageindex().intValue()==1){
			previousPagingIndex1.setCurrent(1);
			currentpage.setPageindex(index2);
			currentpage.setCurrent(0);
		}else{
		currentpage.setPageindex(index2);	
		currentpage.setCurrent(1);
		}
		currentpage.setShowstr("" + currentpage.getPageindex());
		currentpage.setLink("javascript:openpage('"
				+ currentpage.getPageindex() + "','"
				+ pagingModel.getDocchannel() + "','"
				+ pagingModel.getImggroupid() + "',"
				+ pagingModel.getDocstatus() + ");");
		currentpage.setId("page" + currentpage.getPageindex());
		pagse.add(currentpage);
		
		//
		//
		PagingIndex nextpage1 = new PagingIndex();
		nextpage1.setPageindex(index3);
		nextpage1.setShowstr("" + nextpage1.getPageindex());
		nextpage1.setLink("javascript:openpage('" + nextpage1.getPageindex()
				+ "','" + pagingModel.getDocchannel() + "','"
				+ pagingModel.getImggroupid() + "',"
				+ pagingModel.getDocstatus() + ");");
		nextpage1.setId("page" + nextpage1.getPageindex());
		if(pagingModel.getPagecount()!=1){
			pagse.add(nextpage1);
		}
		//
		PagingIndex nextPagingIndex = new PagingIndex();
		nextPagingIndex.setShowstr(">");
		nextPagingIndex.setPageindex(pagingModel.getPageindex() + 1);
		if (pagingModel.getPageindex() + 1 >= pagingModel.getPagecount()) {
			nextPagingIndex.setPageindex(pagingModel.getPagecount());
		}
		nextPagingIndex.setLink("javascript:openpage('"
				+ nextPagingIndex.getPageindex() + "','"
				+ pagingModel.getDocchannel() + "','"
				+ pagingModel.getImggroupid() + "',"
				+ pagingModel.getDocstatus() + ");");
		nextPagingIndex.setId("next");
		pagse.add(nextPagingIndex);
		PagingIndex lastPagingIndex = new PagingIndex();
		lastPagingIndex.setPageindex(pagingModel.getPagecount());
		lastPagingIndex.setShowstr(">>");
		lastPagingIndex.setLink("javascript:openpage('"
				+ lastPagingIndex.getPageindex() + "','"
				+ pagingModel.getDocchannel() + "','"
				+ pagingModel.getImggroupid() + "',"
				+ pagingModel.getDocstatus() + ");switchclass('last');");
		lastPagingIndex.setId("last");
		pagse.add(lastPagingIndex);
		String pagestr = "";
		for (PagingIndex pagingIndexi : pagse) {
			if (pagingIndexi.getCurrent() != null
					&& pagingIndexi.getCurrent().intValue() == 1) {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + currentclass
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			} else {
				pagestr = pagestr + "<a class=\"first disabled\"  " + "id=\""
						+ pagingIndexi.getId() + "\"  href=\""
						+ pagingIndexi.getLink() + "\"><span id=\"page"
						+ pagingIndexi.getId() + "\" class=\"" + classname
						+ "\">" + pagingIndexi.getShowstr() + "</span></a>";
			}

		}
		return pagestr;
	}
}
