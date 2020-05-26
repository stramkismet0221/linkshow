package com.ddzhuan.manage.model;

import java.util.List;
import java.util.Map;

/**
 * 树
 *
 * @author Acer
 */
public class TreeNode {

	private Long id;
	
	private Long pid;
	
	private String text;
	
	private Map<String, Object> attributes;
	
	private List<TreeNode> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
}
