/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.common.bean;

import java.util.List;

/**
 *
 * @author Surachai
 */
public abstract class AbstractBean {
 	protected List queryList;
	public AbstractBean(List queryList){
		this.queryList = queryList;
	}
	/**
	 * @return Returns the queryList.
	 */
	public List getQueryList() {
		return queryList;
	}
	/**
	 * @param queryList The queryList to set.
	 */
	public void setQueryList(List queryList) {
		this.queryList = queryList;
	}       
}
