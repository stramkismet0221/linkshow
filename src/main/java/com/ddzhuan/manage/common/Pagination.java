package com.ddzhuan.manage.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @author likeke
 * @date 2019/06/24
 */
public class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 当前页
	 */
	private Integer page = 1;
	/**
	 * 每页数量
	 */
	private Integer rows = 10;
	/**
	 * 总数
	 */
	private Integer total = 0;
	/**
	 * 总页数
	 */
	private Integer totalPage = 0;
	/**
	 * 分页开始
	 */
	private Integer start = 0;
	/**
	 * 分页结束
	 */
	private Integer end = 0;
	/**
	 * 分页
	 */
	private List<Integer> pageGroup = null;

	public List<Integer> getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(List<Integer> pageGroup) {
		this.pageGroup = pageGroup;
	}

	public Pagination() {}
	
	public Pagination(Integer page, Integer rows) {
		super();
		this.page = page;
		this.rows = rows;
	}
	
	public Integer getPage() {
		if (page <= 0) {
			page = 1;
		} else if (page > getTotalPage() && getTotalPage() > 0) {
			page = totalPage;
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		if (rows <= 0) {
			rows = 10;
		}
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalPage() {
		if (total == 0) {
			totalPage = 0;
		} else {
			if (total % rows == 0) {
				totalPage = (total / rows);
			} else {
				totalPage = (total / rows) + 1;
			}
		}
		return totalPage;
	}

	public Integer getEnd() {
		end = start+rows;
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getStart() {
		start = (getPage() - 1) * getRows();
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<Integer> getPagesGroup() {
		int minusVal=5;
		int displayPage=9;
		int basePage = -1;
		if (getPage() < minusVal) {
			basePage = 0;
		} else {
			basePage = getPage() - minusVal;
		}
		List<Integer> pageGroup = new ArrayList<>();
		int num = getTotalPage() - basePage;
		num = num >= displayPage ? displayPage : num;
		for (int i = 1; i <= displayPage; i++) {
			if ((basePage - (displayPage - num) + i) > 0) {
				pageGroup.add(basePage - (displayPage - num) + i);
			}
		}
		return pageGroup;
	}

}
