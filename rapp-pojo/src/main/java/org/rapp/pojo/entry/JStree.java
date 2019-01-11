package org.rapp.pojo.entry;

/**
 * JStree树结构封装类
 *
 * @author 张芳
 *
 */
public class JStree {

	private String id;

	private String text;

	private String parent;

	private Boolean children;

	private String icon;

	public JStree(String id, String text, String parent, Boolean children, String icon) {
		super();
		this.id = id;
		this.text = text;
		this.parent = parent;
		this.children = children;
		this.icon = icon;
	}

	public JStree() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChildren() {
		return children;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
