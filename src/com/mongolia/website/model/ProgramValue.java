package com.mongolia.website.model;

import java.util.List;

/**
 * ��վ��Ŀ �û�������ҳ�ϵ���ʾ����Ŀ������
 * 
 * @author baowzh
 * 
 */
public class ProgramValue extends BaseBean {
	/**
	 * ��Ŀ����ҳ��ռ�õĿ��������Ϊ��λ
	 */
	private Integer width;
	/**
	 * ��Ŀ����ҳ��ռ�õĸ߶�������Ϊ��λ
	 */
	private Integer height;
	/**
	 * ��Ŀ�����������˵ľ���
	 */
	private Integer top;
	/**
	 * ��Ŀ����������߿�ľ���
	 */
	private Integer left;
	private Integer z_index;
	/**
	 * ��Ŀ�����ı߿�
	 */
	private Integer border;
	/**
	 * ��Ŀ�����ı�����ɫ
	 */
	private String background;
	/**
	 * ��Ŀ�����ı���ͼƬ
	 */
	private String background_img;
	/**
	 * ��Ŀid
	 */
	private String programid;
	/**
	 * ������Ŀ����
	 */
	private String programcomm;
	/**
	 * ��Ŀ����ͼƬ
	 */
	private String programimg;
	/**
	 * css ��ʽ
	 */
	private String style;
	/**
	 * ��Ŀ���� ͼƬ��Ŀ����Ƶ��Ŀ���ı���Ŀ
	 */
	private String programKind;
	/**
	 * ��Ŀ��ϸ�б��ͼƬ
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
