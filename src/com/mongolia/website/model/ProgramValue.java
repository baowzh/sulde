package com.mongolia.website.model;

import java.util.List;

/**
 * 网站栏目 用户定义主页上的显示的栏目的属性
 * 
 * @author baowzh
 * 
 */
public class ProgramValue extends BaseBean {
	/**
	 * 栏目在主页中占用的宽度以像素为单位
	 */
	private Integer width;
	/**
	 * 栏目在主页中占用的高度以像素为单位
	 */
	private Integer height;
	/**
	 * 栏目离主容器顶端的距离
	 */
	private Integer top;
	/**
	 * 栏目离主容器左边框的距离
	 */
	private Integer left;
	private Integer z_index;
	/**
	 * 栏目容器的边框
	 */
	private Integer border;
	/**
	 * 栏目容器的背景颜色
	 */
	private String background;
	/**
	 * 栏目容器的背景图片
	 */
	private String background_img;
	/**
	 * 栏目id
	 */
	private String programid;
	/**
	 * 关于栏目描述
	 */
	private String programcomm;
	/**
	 * 栏目标题图片
	 */
	private String programimg;
	/**
	 * css 样式
	 */
	private String style;
	/**
	 * 栏目类型 图片栏目，视频栏目，文本栏目
	 */
	private String programKind;
	/**
	 * 栏目明细列表的图片
	 */
	private String itemImg;

	private List<ProgramItem> items;

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getZ_index() {
		return z_index;
	}

	public void setZ_index(Integer z_index) {
		this.z_index = z_index;
	}

	public Integer getBorder() {
		return border;
	}

	public void setBorder(Integer border) {
		this.border = border;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getBackground_img() {
		return background_img;
	}

	public void setBackground_img(String background_img) {
		this.background_img = background_img;
	}

	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public String getProgramcomm() {
		return programcomm;
	}

	public void setProgramcomm(String programcomm) {
		this.programcomm = programcomm;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getProgramKind() {
		return programKind;
	}

	public void setProgramKind(String programKind) {
		this.programKind = programKind;
	}

	public String getItemImg() {
		return itemImg;
	}

	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}

	public List<ProgramItem> getItems() {
		return items;
	}

	public void setItems(List<ProgramItem> items) {
		this.items = items;
	}

	public String getProgramimg() {
		return programimg;
	}

	public void setProgramimg(String programimg) {
		this.programimg = programimg;
	}

}
