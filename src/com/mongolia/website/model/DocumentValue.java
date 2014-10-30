package com.mongolia.website.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DocumentValue {
	private String docid;
	private String docchannel;
	private String docchannelname;
	private String docversion;
	private Integer doctype;
	private String doctitle;
	private Double docsource;
	private Double docsecurity;
	private Integer docstatus;
	private Integer dockind;
	private byte[] doccontent;
	private byte[] dochtmlcon;
	private String docabstract;
	private String dockeywords;
	private String docrelwords;
	private String docpeople;
	private String docplace;
	private String docauthor;
	private String doceditor;
	private String docauditor;
	private Double docoutupid;
	private Date docvalid;
	private String docpuburl;
	private Date docpubtime;
	private Date docreltime;
	private String docRelTimeStr;
	private String cruser;
	private Date crtime;
	private Double docwordscount;
	private Double docpro;
	private Double rightdefined;
	private String titlecolor;
	private String templateid;
	private Double schedule;
	private String docno;
	private Integer docflag;
	private String editor;
	private String attribute;
	private Double hitscount;
	private byte[] docpubhtmlcon;
	private String subdoctitle;
	private String attachpic;
	private String doclink;
	private String docfilename;
	private String docfromversion;
	private Date opertime;
	private String operuser;
	private String flowoperationmark;
	private String flowpreoperationmark;
	private String flowoperationmaskenum;
	private String docsourcename;
	private String doclinkto;
	private String docmirrorto;
	private boolean ispageimg;
	private Date publishdate;
	private String pagenum;
	private String pagename;
	private String pdffilename;
	private String pageimagefilename;
	private String map;
	private String yinti;
	private String htmlstr;
	private String userid;
	private Integer sharecount=new Integer(0);
	private Integer markcount=new Integer(0);
	private Integer readcount=new Integer(0);
	private Integer commentCount=new Integer(0);;
	private Integer self = new Integer(0);
	private Integer ireadcount;
	private String chnaname;
	private String compiler;
	private String oldid;
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocchannel() {
		return docchannel;
	}

	public void setDocchannel(String docchannel) {
		this.docchannel = docchannel;
	}

	public String getDocversion() {
		return docversion;
	}

	public void setDocversion(String docversion) {
		this.docversion = docversion;
	}

	public Integer getDoctype() {
		return doctype;
	}

	public void setDoctype(Integer doctype) {
		this.doctype = doctype;
	}

	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public Double getDocsource() {
		return docsource;
	}

	public void setDocsource(Double docsource) {
		this.docsource = docsource;
	}

	public Double getDocsecurity() {
		return docsecurity;
	}

	public void setDocsecurity(Double docsecurity) {
		this.docsecurity = docsecurity;
	}

	public Integer getDocstatus() {
		return docstatus;
	}

	public void setDocstatus(Integer docstatus) {
		this.docstatus = docstatus;
	}

	public Integer getDockind() {
		return dockind;
	}

	public void setDockind(Integer dockind) {
		this.dockind = dockind;
	}

	public byte[] getDoccontent() {
		return doccontent;
	}

	public void setDoccontent(byte[] doccontent) {
		this.doccontent = doccontent;
	}

	public byte[] getDochtmlcon() {
		return dochtmlcon;
	}

	public void setDochtmlcon(byte[] dochtmlcon) {
		this.dochtmlcon = dochtmlcon;
	}

	public String getDocabstract() {
		return docabstract;
	}

	public void setDocabstract(String docabstract) {
		this.docabstract = docabstract;
	}

	public String getDockeywords() {
		return dockeywords;
	}

	public void setDockeywords(String dockeywords) {
		this.dockeywords = dockeywords;
	}

	public String getDocrelwords() {
		return docrelwords;
	}

	public void setDocrelwords(String docrelwords) {
		this.docrelwords = docrelwords;
	}

	public String getDocpeople() {
		return docpeople;
	}

	public void setDocpeople(String docpeople) {
		this.docpeople = docpeople;
	}

	public String getDocplace() {
		return docplace;
	}

	public void setDocplace(String docplace) {
		this.docplace = docplace;
	}

	public String getDocauthor() {
		return docauthor;
	}

	public void setDocauthor(String docauthor) {
		this.docauthor = docauthor;
	}

	public String getDoceditor() {
		return doceditor;
	}

	public void setDoceditor(String doceditor) {
		this.doceditor = doceditor;
	}

	public String getDocauditor() {
		return docauditor;
	}

	public void setDocauditor(String docauditor) {
		this.docauditor = docauditor;
	}

	public Double getDocoutupid() {
		return docoutupid;
	}

	public void setDocoutupid(Double docoutupid) {
		this.docoutupid = docoutupid;
	}

	public Date getDocvalid() {
		return docvalid;
	}

	public void setDocvalid(Date docvalid) {
		this.docvalid = docvalid;
	}

	public String getDocpuburl() {
		return docpuburl;
	}

	public void setDocpuburl(String docpuburl) {
		this.docpuburl = docpuburl;
	}

	public Date getDocpubtime() {
		return docpubtime;
	}

	public void setDocpubtime(Date docpubtime) {
		this.docpubtime = docpubtime;
	}

	public Date getDocreltime() {
		return docreltime;
	}

	public void setDocreltime(Date docreltime) {
		this.docreltime = docreltime;
	}

	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	public Date getCrtime() {
		return crtime;
	}

	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	public Double getDocwordscount() {
		return docwordscount;
	}

	public void setDocwordscount(Double docwordscount) {
		this.docwordscount = docwordscount;
	}

	public Double getDocpro() {
		return docpro;
	}

	public void setDocpro(Double docpro) {
		this.docpro = docpro;
	}

	public Double getRightdefined() {
		return rightdefined;
	}

	public void setRightdefined(Double rightdefined) {
		this.rightdefined = rightdefined;
	}

	public String getTitlecolor() {
		return titlecolor;
	}

	public void setTitlecolor(String titlecolor) {
		this.titlecolor = titlecolor;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public Double getSchedule() {
		return schedule;
	}

	public void setSchedule(Double schedule) {
		this.schedule = schedule;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public Integer getDocflag() {
		return docflag;
	}

	public void setDocflag(Integer docflag) {
		this.docflag = docflag;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Double getHitscount() {
		return hitscount;
	}

	public void setHitscount(Double hitscount) {
		this.hitscount = hitscount;
	}

	public byte[] getDocpubhtmlcon() {
		return docpubhtmlcon;
	}

	public void setDocpubhtmlcon(byte[] docpubhtmlcon) {
		this.docpubhtmlcon = docpubhtmlcon;
	}

	public String getSubdoctitle() {
		return subdoctitle;
	}

	public void setSubdoctitle(String subdoctitle) {
		this.subdoctitle = subdoctitle;
	}

	public String getAttachpic() {
		return attachpic;
	}

	public void setAttachpic(String attachpic) {
		this.attachpic = attachpic;
	}

	public String getDoclink() {
		return doclink;
	}

	public void setDoclink(String doclink) {
		this.doclink = doclink;
	}

	public String getDocfilename() {
		return docfilename;
	}

	public void setDocfilename(String docfilename) {
		this.docfilename = docfilename;
	}

	public String getDocfromversion() {
		return docfromversion;
	}

	public void setDocfromversion(String docfromversion) {
		this.docfromversion = docfromversion;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public String getOperuser() {
		return operuser;
	}

	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}

	public String getFlowoperationmark() {
		return flowoperationmark;
	}

	public void setFlowoperationmark(String flowoperationmark) {
		this.flowoperationmark = flowoperationmark;
	}

	public String getFlowpreoperationmark() {
		return flowpreoperationmark;
	}

	public void setFlowpreoperationmark(String flowpreoperationmark) {
		this.flowpreoperationmark = flowpreoperationmark;
	}

	public String getFlowoperationmaskenum() {
		return flowoperationmaskenum;
	}

	public void setFlowoperationmaskenum(String flowoperationmaskenum) {
		this.flowoperationmaskenum = flowoperationmaskenum;
	}

	public String getDocsourcename() {
		return docsourcename;
	}

	public void setDocsourcename(String docsourcename) {
		this.docsourcename = docsourcename;
	}

	public String getDoclinkto() {
		return doclinkto;
	}

	public void setDoclinkto(String doclinkto) {
		this.doclinkto = doclinkto;
	}

	public String getDocmirrorto() {
		return docmirrorto;
	}

	public void setDocmirrorto(String docmirrorto) {
		this.docmirrorto = docmirrorto;
	}

	public boolean isIspageimg() {
		return ispageimg;
	}

	public void setIspageimg(boolean ispageimg) {
		this.ispageimg = ispageimg;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	public String getPagenum() {
		return pagenum;
	}

	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getPdffilename() {
		return pdffilename;
	}

	public void setPdffilename(String pdffilename) {
		this.pdffilename = pdffilename;
	}

	public String getPageimagefilename() {
		return pageimagefilename;
	}

	public void setPageimagefilename(String pageimagefilename) {
		this.pageimagefilename = pageimagefilename;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getYinti() {
		return yinti;
	}

	public void setYinti(String yinti) {
		this.yinti = yinti;
	}

	public String getHtmlstr() {
		return htmlstr;
	}

	public void setHtmlstr(String htmlstr) {
		this.htmlstr = htmlstr;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getSharecount() {
		return sharecount;
	}

	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}

	public Integer getMarkcount() {
		return markcount;
	}

	public void setMarkcount(Integer markcount) {
		this.markcount = markcount;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getDocRelTimeStr() {
		if (this.docreltime != null) {
			return this.formater.format(this.docreltime);
		} else {
			if (docRelTimeStr != null) {
				return docRelTimeStr;
			} else {
				return "";
			}

		}
	}

	public void setDocRelTimeStr(String docRelTimeStr) {
		this.docRelTimeStr = docRelTimeStr;
	}

	public Integer getSelf() {
		return self;
	}

	public void setSelf(Integer self) {
		this.self = self;
	}

	public Integer getIreadcount() {
		return ireadcount;
	}

	public void setIreadcount(Integer ireadcount) {
		this.ireadcount = ireadcount;
	}

	public String getChnaname() {
		return chnaname;
	}

	public void setChnaname(String chnaname) {
		this.chnaname = chnaname;
	}

	public String getCompiler() {
		return compiler;
	}

	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}

	public String getOldid() {
		return oldid;
	}

	public void setOldid(String oldid) {
		this.oldid = oldid;
	}

	public String getDocchannelname() {
		return docchannelname;
	}

	public void setDocchannelname(String docchannelname) {
		this.docchannelname = docchannelname;
	}
	
	
}
